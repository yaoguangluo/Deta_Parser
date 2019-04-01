package org.tinos.ortho.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.tinos.ortho.fhmm.FHMMList;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.obj.FMHMMPOS;
import org.tinos.view.stable.StableData;
import org.tinos.view.stable.StableMaps;
//I will build a collection class for managing this maps. at the next version.
@SuppressWarnings("unchecked")
public class FMHMMListOneTimeImp implements FHMMList {
	private Map<String, String> posCnToCn;
	private Map<String, String> posEnToEn;
	private Map<String, String> posEnToCn;
	private Map<String, String> enToCn;
	private Map<String, String> cnToEn;
	private Map<String, String> fullEnToCn;
	private Map<String, String> fullCnToEn;
	private Map<String, String> fullCnToFn;
	private Map<String, String> fullCnToKo;
	private Map<String, String> fullCnToJp;
	private Map<String, String> fullCnToSp;
	private Map<String, String> fullCnToAb;
	private Map<String, String> fullCnToGm;
	private Map<String, String> fullCnToRs;
	private Map<String, String> fullCnToPy;
	private Map<String, String> fullPositive;
	private Map<String, String> fullNegative;
	private List<String> listEn;
	private List<String> listCn;
	private List<String> listFn;
	private List<String> listKo;
	private List<String> listJp;
	private List<String> listSp;
	private List<String> listGm;
	private List<String> listRs;
	private List<String> listAb;
	private List<String> listPy;
	public Map<Long, FMHMMPOS> POSHashMap;
	public Map<Long, FMHMMNode> linkedHashMap;
	public Map<Long, FMHMMNode> getMap() {
		return this.linkedHashMap;
	}
	
	public Map<Long, FMHMMNode>[] getMaps() {
		int segment= this.linkedHashMap.size();
		int perRatio= segment/ StableData.INT_SIX;
		Map<Long, FMHMMNode>[] maps= new HashMap[StableData.INT_SIX];
		Iterator<Long> iterator= this.linkedHashMap.keySet().iterator();
		maps[StableData.INT_ZERO]= new HashMap<>();
		int index= StableData.INT_ZERO;
		int count= StableData.INT_ONE;
		while(iterator.hasNext()) {
			if(StableData.INT_ZERO== count++ % perRatio) {
				if(index< StableData.INT_FIVE) {
					index++;
					maps[index]= new HashMap<>();
				}
			}
			Long key= iterator.next();
			maps[index].put(key, this.linkedHashMap.get(key));
		}
		return maps;
	}

	public void index() throws IOException {
		posCnToCn= new HashMap<>();
		linkedHashMap= new HashMap<>();
		listCn= new CopyOnWriteArrayList<>();
		InputStream inputStream= getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_CN);
		BufferedReader cReader= new BufferedReader(new InputStreamReader(inputStream, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
				listCn.add(cInputString);
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				if(!StableMaps.fuCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_FU)) {
					StableMaps.fuCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.dongCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_DONG)) {
					StableMaps.dongCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.liangCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_LIANG)) {
					StableMaps.liangCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.lianCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_LIAN)) {
					StableMaps.lianCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.baDongCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_BA_DONG)) {
					StableMaps.baDongCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.xianDingCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_XIAN_DING)) {
					StableMaps.xianDingCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.mingCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_MING)) {
					StableMaps.mingCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.daiCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_DAI)) {
					StableMaps.daiCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.jieCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_JIE)) {
					StableMaps.jieCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.xingRongCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_XING_RONG)) {
					StableMaps.xingRongCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.zhuCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_ZHU)) {
					StableMaps.zhuCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.weiCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_WEI)) {
					StableMaps.weiCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.shengLueCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_SHENG_LUE)) {
					StableMaps.shengLueCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.qingTaiCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_QING_TAI)) {
					StableMaps.qingTaiCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				if(!StableMaps.xingWeiCi.containsKey(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO])
						&& cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].contains(StableData.NLP_CI_XING_WEI)) {
					StableMaps.xingWeiCi.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
							.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				}
				posCnToCn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				linkedHashMap = loopLoadForest(cInputString);
			}
		cReader.close();
	}

	public void indexFullEnToCn() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listee= listEn.iterator();
		fullEnToCn= new HashMap<>();
		while(listcc.hasNext()&&listee.hasNext()) {
			fullEnToCn.put(listee.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase()
					, listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]);
		}
	}

	public void indexFullCnToEn() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listee= listEn.iterator();
		fullCnToEn = new HashMap<>();
		while(listcc.hasNext()&&listee.hasNext()) {
			fullCnToEn.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listee.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToFn() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listfn= listFn.iterator();
		fullCnToFn = new HashMap<>();
		while(listcc.hasNext()&&listfn.hasNext()) {
			fullCnToFn.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listfn.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToKo() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listko= listKo.iterator();
		fullCnToKo = new HashMap<>();
		while(listcc.hasNext()&&listko.hasNext()) {
			fullCnToKo.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listko.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToJp() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listjp= listJp.iterator();
		fullCnToJp = new HashMap<>();
		while(listcc.hasNext()&&listjp.hasNext()) {
			fullCnToJp.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listjp.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToGm() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listgm= listEn.iterator();
		fullCnToGm = new HashMap<>();
		while(listcc.hasNext()&&listgm.hasNext()) {
			fullCnToGm.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listgm.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToSp() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listsp= listSp.iterator();
		fullCnToSp = new HashMap<>();
		while(listcc.hasNext()&&listsp.hasNext()) {
			fullCnToSp.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listsp.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToRs() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listrs= listRs.iterator();
		fullCnToRs = new HashMap<>();
		while(listcc.hasNext()&&listrs.hasNext()) {
			fullCnToRs.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listrs.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToAb() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listab= listAb.iterator();
		fullCnToAb = new HashMap<>();
		while(listcc.hasNext()&&listab.hasNext()) {
			fullCnToAb.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listab.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}
	
	public void indexFullCnToPy() throws IOException {	
		Iterator<String> listcc= listCn.iterator();
		Iterator<String> listpy= listPy.iterator();
		fullCnToPy = new HashMap<>();
		while(listcc.hasNext()&&listpy.hasNext()) {
			fullCnToPy.put(listcc.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO]
					, listpy.next().split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase());
		}
	}

	public Map<String, String> getFullEnToCn() {
		return this.fullEnToCn;
	}

	public Map<String, String> getFullCnToEn() {
		return this.fullCnToEn;
	}

	public void indexPosEnToCn() throws IOException {
		posEnToCn= new HashMap<>();
		InputStream in= getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_EN_TO_CN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while (null!= (cInputString= cReader.readLine())) {
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				posEnToCn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase(), cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
			}
		cReader.close();
	}
	
	public void indexFn() throws IOException {
		listFn= new CopyOnWriteArrayList<>();
		InputStream in= getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_FN);
		BufferedReader cReader= new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString= cReader.readLine())) {
				listFn.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexKo() throws IOException {
		listKo = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_KO);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listKo.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexJp() throws IOException {
		listJp = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_JP);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listJp.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexGm() throws IOException {
		listGm = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_GM);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listGm.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexSp() throws IOException {
		listSp = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_SP);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listSp.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexAb() throws IOException {
		listAb = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_AB);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listAb.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexRs() throws IOException {
		listRs = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_RS);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listRs.add(cInputString);
			}
		cReader.close();
	}
	
	public void indexPy() throws IOException {
		listPy = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_PY);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString = cReader.readLine())) {
				listPy.add(cInputString);
			}
		cReader.close();
	}


	public void indexPosEnToEn() throws IOException {
		posEnToEn = new HashMap<>();
		listEn = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_EN_TO_EN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while (null!= (cInputString = cReader.readLine())) {
				listEn.add(cInputString);
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				posEnToEn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase(), cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].toLowerCase());
			}
		cReader.close();
	}

	public void indexEnToCn() throws IOException {
		enToCn = new HashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_EN_TO_CN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while (null!= (cInputString = cReader.readLine())) {
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				enToCn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase(), cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
			}
		cReader.close();
	}

	public void indexCnToEn() throws IOException {
		cnToEn = new HashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_CN_TO_EN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while (null!= (cInputString = cReader.readLine())) {
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				cnToEn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].toLowerCase());
			}
		cReader.close();
	}

	public Map<Long, FMHMMNode> loopLoadForest(String cInputString) {
		Here:
			for (int i = StableData.INT_ZERO; i < cInputString.length(); i++) {
				if (linkedHashMap.containsKey(Long.valueOf(cInputString.charAt(i)))) {
					FMHMMNode fHHMMNode = linkedHashMap.get(Long.valueOf(cInputString.charAt(i)));
					linkedHashMap = doNeroPostCognitive(fHHMMNode, cInputString, i);
					continue Here;
				} 
				FMHMMNode fHHMMNode = new FMHMMNode();
				fHHMMNode.setVb(StableData.EMPTY_STRING + cInputString.charAt(i));
				if (i + StableData.INT_ONE < cInputString.length()) {
					Map<String, Integer> next = new HashMap<>();
					next.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE), StableData.INT_ONE);
					fHHMMNode.setNext(next);
				}
				linkedHashMap.put(Long.valueOf(cInputString.charAt(i)), fHHMMNode);
			}
	return linkedHashMap;
	}

	public Map<Long, FMHMMNode> doNeroPostCognitive(FMHMMNode fFHMMNode, String cInputString, int i) {
		if (null!= fFHMMNode.getNext()) {
			if (i + StableData.INT_ONE < cInputString.length()) {
				linkedHashMap = doCheckAndRunNeroPostFix(fFHMMNode, cInputString, i);
			}
			return linkedHashMap;
		}
		HashMap<String, Integer> HashMap = new HashMap<>();
		if (i + StableData.INT_ONE < cInputString.length()) {
			HashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE)
			,StableData.INT_ONE);
		}
		fFHMMNode.setNext(HashMap);
		linkedHashMap.put(Long.valueOf(cInputString.charAt(i)), fFHMMNode);
		return linkedHashMap;
	}

	public Map<Long, FMHMMNode> doCheckAndRunNeroPostFix(FMHMMNode fFHMMNode, String cInputString, int i) {
		if (!fFHMMNode.getNext().containsKey(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE))) {
			Map<String, Integer> map = fFHMMNode.getNext();
			map.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE), StableData.INT_ONE);
			fFHMMNode.setNext(map);
			linkedHashMap.put(Long.valueOf(cInputString.charAt(i)), fFHMMNode);
		}
		return linkedHashMap;
	}

	public Map<String, String> getPosCnToCn() {
		return this.posCnToCn;
	}

	public Map<String, String> getEnToCn() {
		return enToCn;
	}

	public Map<String, String> getCnToEn() {
		return cnToEn;
	}

	public Map<String, String> getPosEnToCn() {
		return this.posEnToCn;
	}

	public Map<String, String> getPosEnToEn() {
		return this.posEnToEn;
	}

	public List<String> englishStringToWordsList(String string) {
		List<String> list= new LinkedList<>();
		string= string.replaceAll(StableData.NLP_SPASE_REP, StableData.SPACE_STRING);
		StringBuilder sb= new StringBuilder();
		for(int i= 0; i< string.length(); i++) {
			if((string.charAt(i)> StableData.INT_SIXTY_FOUR && string.charAt(i)<= StableData.INT_NINTY)
					||(string.charAt(i)>= StableData.INT_NINTY_SEVEN && string.charAt(i)<= StableData.INT_ONE_TWO_TWO)) {
				sb.append(string.charAt(i));
			}else {
				list.add(sb.toString().toLowerCase());
				sb.delete(StableData.INT_ZERO, sb.length());
				list.add(String.valueOf(string.charAt(i)));
			}
		}
		return list;
	}

	public Map<String, String> getFullCnToJp() {
		return this.fullCnToJp;
	}

	public Map<String, String> getFullCnToRs() {
		return this.fullCnToRs;
	}

	public Map<String, String> getFullCnToAb() {
		return this.fullCnToAb;
	}

	public Map<String, String> getFullCnToFn() {
		return this.fullCnToFn;
	}

	public Map<String, String> getFullCnToGm() {
		return this.fullCnToGm;
	}

	public Map<String, String> getFullCnToKo() {
		return this.fullCnToKo;
	}

	public Map<String, String> getFullCnToSp() {
		return this.fullCnToSp;
	}

	public Map<String, String> getFullCnToPy() {
		return this.fullCnToPy;
	}

	public void indexFullNegative() throws IOException {
		fullNegative= new HashMap<>();
		InputStream in= getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_NEGATIVE);
		BufferedReader cReader= new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString= cReader.readLine())) {
				if(!fullNegative.containsKey(cInputString)) {
					fullNegative.put(cInputString, StableData.EMPTY_STRING);
				}
			}
		cReader.close();
	}
	
	public void indexFullPositive() throws IOException {
		fullPositive= new HashMap<>();
		InputStream in= getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_POSITIVE);
		BufferedReader cReader= new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
			while (null!= (cInputString= cReader.readLine())) {
				if(!fullPositive.containsKey(cInputString)) {
					fullPositive.put(cInputString, StableData.EMPTY_STRING);
				}
			}
		cReader.close();
		
	}

	public Map<String, String> getFullNegative() {
		return this.fullNegative;
	}

	public Map<String, String> getFullPositive() {
		return this.fullPositive;
	}

	@Override
	public Map<Long, Map<String, String>> getWordsForests() {
		Map<Long, Map<String, String>> output= new HashMap<>();
		Iterator<String> WordTree= posCnToCn.keySet().iterator();
		while(WordTree.hasNext()){
			String treeName= WordTree.next();
			 Map<String, String> treeLeafs;
			if(output.containsKey(Long.valueOf(treeName.charAt(StableData.INT_ZERO)))){
				treeLeafs= output.get(Long.valueOf(treeName.charAt(StableData.INT_ZERO)));
			}else {
				treeLeafs= new HashMap<>();
			}
			 treeLeafs.put(treeName, posCnToCn.get(treeName)); 
			 output.put(Long.valueOf(treeName.charAt(StableData.INT_ZERO)), treeLeafs);
		}
		return output;	
	}
}