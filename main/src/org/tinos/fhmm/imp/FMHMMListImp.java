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
	private Map<String, FMHMMNode> linkedHashMap;
	@SuppressWarnings(StableData.RAW_TYPES)
	private Map<Integer, Map> linkedHashMapRoot;
	@SuppressWarnings(StableData.RAW_TYPES)
	public Map<Integer, Map> getRoot() {
		return this.linkedHashMapRoot;
	}

	public void index() throws IOException {
		words = new ConcurrentHashMap<>();
		linkedHashMap = new ConcurrentHashMap<>();
		linkedHashMapRoot = new ConcurrentHashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_CN);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
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

	public Map<String, FMHMMNode> loopLoadForest(String cInputString) {
		Here:
			for (int i = StableData.INT_ZERO; i < cInputString.length(); i++) {
				if (linkedHashMap.containsKey(StableData.EMPTY_STRING + cInputString.charAt(i))) {
					FMHMMNode fHHMMNode = linkedHashMap.get(StableData.EMPTY_STRING + cInputString.charAt(i));
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
					linkedHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i), fHHMMNode);
				}
			}
	return linkedHashMap;
	}

	public Map<String, FMHMMNode> doNeroPostCognitive(FMHMMNode fFHMMNode, String cInputString, int i) {
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
			linkedHashMap.put(StableData.EMPTY_STRING + cInputString.charAt(i), fFHMMNode);
		}
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

	public Map<String, String> getWords() {
		return this.words;
	}

	public Map<String, FMHMMNode> getMap() {
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
}