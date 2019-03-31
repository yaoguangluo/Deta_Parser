package org.tinos.ortho.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.tinos.ortho.fhmm.FMHMMList;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
import org.tinos.engine.euclid.imp.EuclidControllerImp;
public class FMHMMListImp implements FMHMMList {
	private Map<String, String> words;
	private Map<Long, FMHMMNode> linkedHashMap;
	@SuppressWarnings(StableData.RAW_TYPES)
	private Map<Integer, Map> linkedHashMapRoot;
	@SuppressWarnings(StableData.RAW_TYPES)
	public Map<Integer, Map> getRoot() {
		return this.linkedHashMapRoot;
	}

	public void index() throws IOException {
		words= new ConcurrentHashMap<>();
		linkedHashMap= new ConcurrentHashMap<>();
		linkedHashMapRoot= new ConcurrentHashMap<>();
		InputStream inputStream= getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_CN);
		BufferedReader cReader= new BufferedReader(new InputStreamReader(inputStream, StableData.UTF8_STRING));
		String cInputString;
		Here:
			while ((cInputString = cReader.readLine()) != null) {
				if(!(!cInputString.replace(StableData.SPACE_STRING, StableData.EMPTY_STRING).equals(StableData.EMPTY_STRING)
						&& cInputString.split(StableData.NLP_SYMBO_SLASH).length > StableData.INT_ONE )) {
					continue Here;
				}
				words.put(cInputString.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ZERO], cInputString
						.split(StableData.NLP_SYMBO_SLASH)[StableData.INT_ONE]);
				linkedHashMap = loopLoadForest(cInputString);
			}
		cReader.close();
		linkedHashMapRoot = new EuclidControllerImp().mCogsEuclid(linkedHashMap);
	}

	public Map<Long, FMHMMNode> loopLoadForest(String cInputString) {
		Here:
			for (int i = StableData.INT_ZERO; i < cInputString.length(); i++) {
				if (linkedHashMap.containsKey(Long.valueOf(cInputString.charAt(i)))) {
					FMHMMNode fHHMMNode = linkedHashMap.get(Long.valueOf(cInputString.charAt(i)));
					linkedHashMap = doNeroPostCognitive(fHHMMNode, cInputString, i);
					continue Here;
				} else {
					FMHMMNode fHHMMNode = new FMHMMNode();
					fHHMMNode.setVb(StableData.EMPTY_STRING + cInputString.charAt(i));
					if (i + StableData.INT_ONE < cInputString.length()) {
						Map<String, Integer> next = new ConcurrentHashMap<>();
						next.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE), StableData.INT_ONE);
						fHHMMNode.setNext(next);
					}
					linkedHashMap.put(Long.valueOf(cInputString.charAt(i)), fHHMMNode);
				}
			}
	return linkedHashMap;
	}

	public Map<Long, FMHMMNode> doNeroPostCognitive(FMHMMNode fFHMMNode, String cInputString, int i) {
		if (fFHMMNode.getNext() != null) {
			if (i + StableData.INT_ONE < cInputString.length()) {
				linkedHashMap = doCheckAndRunNeroPostFix(fFHMMNode, cInputString, i);
			}
		} else {
			ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
			if (i + StableData.INT_ONE < cInputString.length()) {
				concurrentHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i + StableData.INT_ONE),
						StableData.INT_ONE);
			}
			fFHMMNode.setNext(concurrentHashMap);
			linkedHashMap.put(Long.valueOf(cInputString.charAt(i)), fFHMMNode);
		}
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

	public Map<String, String> getWords() {
		return this.words;
	}

	public Map<Long, FMHMMNode> getMap() {
		return this.linkedHashMap;
	}

	public Map<String, String> getPosEnToEn() {
		return null;
	}

	public Map<String, String> getEnToCn() {
		return null;
	}

	public Map<String, String> getCnToEn() {
		return null;
	}

	public void indexEnToCn() throws IOException {
	}

	public void indexCnToEn() throws IOException {
	}

	public Map<String, String> getPosEnToCn() {
		return null;
	}

	public Map<String, String> getPosCnToCn() {
		return null;
	}

	public void indexPosEnToCn() throws IOException {
	}

	public void indexPosEnToEn() throws IOException {
	}

	public void indexPosCnToEn() throws IOException {
	}

	public Map<String, String> getPosCnToEn() {
		return null;
	}

	public void indexFullEnToCn() throws IOException {
	}

	public void indexFullCnToEn() throws IOException {	
	}

	public Map<String, String> getFullEnToCn() {
		return null;
	}

	public Map<String, String> getFullCnToEn() {
		return null;
	}

	@Override
	public List<String> englishStringToWordsList(String string) {
		return null;
	}

	@Override
	public void indexFullCnToJp() throws IOException {
		
	}

	@Override
	public void indexFullCnToRs() throws IOException {
		
	}

	@Override
	public void indexFullCnToAb() throws IOException {
		
	}

	@Override
	public void indexFullCnToFn() throws IOException {
		
	}

	@Override
	public void indexFullCnToGm() throws IOException {
		
	}

	@Override
	public void indexFullCnToKo() throws IOException {
		
	}

	@Override
	public void indexFullCnToSp() throws IOException {
		
	}

	@Override
	public void indexFullCnToPy() throws IOException {
		
	}

	@Override
	public Map<String, String> getFullCnToJp() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToRs() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToAb() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToFn() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToGm() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToKo() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToSp() {
		return null;
	}

	@Override
	public Map<String, String> getFullCnToPy() {
		return null;
	}

	@Override
	public void indexFullNegative() throws IOException {
		
	}

	@Override
	public void indexFullPositive() throws IOException {
		
	}

	@Override
	public Map<String, String> getFullNegative() {
		return null;
	}

	@Override
	public Map<String, String> getFullPositive() {
		return null;
	}

	@Override
	public Map<Long, FMHMMNode>[] getMaps() {
		return null;
	}

	@Override
	public Map<Long, Map<String, String>> getWordsForests() {
		return null;
	}

	@Override
	public Map<String, String> getPosFuCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosDongCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosLiangCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosLianCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosBaDongCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosXianDingCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosMingCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosDaiCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosJieCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosXingRongCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosZhuCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosWeiCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosShengLueCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosQingTaiCi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getPosXingWeiCi() {
		// TODO Auto-generated method stub
		return null;
	}
}