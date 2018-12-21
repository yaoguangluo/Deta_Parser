package org.tinos.ortho.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.tinos.ortho.fhmm.FHMMList;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
public class FMHMMListOneTimeImp implements FHMMList {
	private Map<String, String> posCnToCn;
	private Map<String, String> posEnToEn;
	private Map<String, String> posEnToCn;
	private Map<String, String> enToCn;
	private Map<String, String> cnToEn;
	private Map<String, String> fullEnToCn;
	private Map<String, String> fullCnToEn;
	private List<String> listEn;
	private List<String> listCn;
	public Map<String, FMHMMNode> linkedHashMap;
	public Map<String, FMHMMNode> getMap() {
		return this.linkedHashMap;
	}

	public void index() throws IOException {
		posCnToCn = new ConcurrentHashMap<>();
		linkedHashMap = new ConcurrentHashMap<>();
		listCn = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_CN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
				listCn.add(cInputString);
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				posCnToCn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				linkedHashMap = loopLoadForest(cInputString);
			}
		cReader.close();
	}

	public void indexFullEnToCn() throws IOException {	
		Iterator<String> listcc = listCn.iterator();
		Iterator<String> listee = listEn.iterator();
		fullEnToCn = new ConcurrentHashMap<>();
		while(listcc.hasNext()&&listee.hasNext()) {
			fullEnToCn.put(listee.next().split("/")[0].toLowerCase(), listcc.next().split("/")[0]);
		}
	}

	public void indexFullCnToEn() throws IOException {	
		Iterator<String> listcc = listCn.iterator();
		Iterator<String> listee = listEn.iterator();
		fullCnToEn = new ConcurrentHashMap<>();
		while(listcc.hasNext()&&listee.hasNext()) {
			fullCnToEn.put(listcc.next().split("/")[0], listee.next().split("/")[0].toLowerCase());
		}
	}

	@Override
	public Map<String, String> getFullEnToCn() {
		return this.fullEnToCn;
	}

	@Override
	public Map<String, String> getFullCnToEn() {
		return this.fullCnToEn;
	}

	public void indexPosEnToCn() throws IOException {
		posEnToCn = new ConcurrentHashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_EN_TO_CN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				posEnToCn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO].toLowerCase(), cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
			}
		cReader.close();
	}

	public void indexPosEnToEn() throws IOException {
		posEnToEn = new ConcurrentHashMap<>();
		listEn = new CopyOnWriteArrayList<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_EN_TO_EN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
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
		enToCn = new ConcurrentHashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_EN_TO_CN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
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
		cnToEn = new ConcurrentHashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_CN_TO_EN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				cnToEn.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE].toLowerCase());
			}
		cReader.close();
	}

	public Map<String, FMHMMNode> loopLoadForest(String cInputString) {
		Here:
			for (int i = StableData.INT_ZERO; i < cInputString.length(); i++) {
				if (linkedHashMap.containsKey(StableData.EMPTY_STRING + cInputString.charAt(i))) {
					FMHMMNode fHHMMNode = linkedHashMap.get(StableData.EMPTY_STRING + cInputString.charAt(i));
					linkedHashMap = doNeroPostCognitive(fHHMMNode, cInputString, i);
					continue Here;
				} 
				FMHMMNode fHHMMNode = new FMHMMNode();
				fHHMMNode.setVb(StableData.EMPTY_STRING + cInputString.charAt(i));
				if (i + StableData.INT_ONE < cInputString.length()) {
					Map<String, Integer> next = new ConcurrentHashMap<>();
					next.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE), StableData.INT_ONE);
					fHHMMNode.setNext(next);
				}
				linkedHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i), fHHMMNode);
			}
	return linkedHashMap;
	}

	public Map<String, FMHMMNode> doNeroPostCognitive(FMHMMNode fFHMMNode, String cInputString, int i) {
		if (fFHMMNode.getNext() != null) {
			if (i + StableData.INT_ONE < cInputString.length()) {
				linkedHashMap = doCheckAndRunNeroPostFix(fFHMMNode, cInputString, i);
			}
			return linkedHashMap;
		}
		ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
		if (i + StableData.INT_ONE < cInputString.length()) {
			concurrentHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE)
			,StableData.INT_ONE);
		}
		fFHMMNode.setNext(concurrentHashMap);
		linkedHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i), fFHMMNode);
		return linkedHashMap;
	}

	public Map<String, FMHMMNode> doCheckAndRunNeroPostFix(FMHMMNode fFHMMNode, String cInputString, int i) {
		if (!fFHMMNode.getNext().containsKey(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE))) {
			Map<String, Integer> map = fFHMMNode.getNext();
			map.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE), StableData.INT_ONE);
			fFHMMNode.setNext(map);
			linkedHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i), fFHMMNode);
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

	@Override
	public List<String> englishStringToWordsList(String string) {
		List<String> list = new LinkedList<>();
		string = string.replaceAll(StableData.NLP_SPASE_REP, StableData.SPACE_STRING);
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<string.length();i++) {
			if((string.charAt(i) > StableData.INT_SIXTY_FOUR && string.charAt(i) <= StableData.INT_NINTY)
					||(string.charAt(i) >= StableData.INT_NINTY_SEVEN && string.charAt(i) <= StableData.INT_ONE_TWO_TWO)) {
				sb.append(string.charAt(i));
			}else {
				list.add(sb.toString().toLowerCase());
				sb.delete(StableData.INT_ZERO, sb.length());
				list.add(String.valueOf(string.charAt(i)));
			}
		}
		return list;
	}
}