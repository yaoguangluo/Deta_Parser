package org.tinos.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import org.tinos.fhmm.FHHMMList;
import org.tinos.obj.FHHMMNode;
import org.tinos.utils.imp.UtilsImp;
import org.tinos.zabbi.DataString;
public class FHHMMListImp implements FHHMMList{
	private String euclid;
	private Map <String, String> words;
	private Map <String, FHHMMNode> linkedHashMap;
	@SuppressWarnings(DataString.RAW_TYPES)
	private Map <Integer, Map> linkedHashMapRoot;
	public Map<String, FHHMMNode> getLinkedHashMap() {
		return linkedHashMap;
	}

	public void setLinkedHashMap(Map<String, FHHMMNode> linkedHashMap) {
		this.linkedHashMap = linkedHashMap;
	}

	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot() {
		return linkedHashMapRoot;
	}

	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot) {
		this.linkedHashMapRoot = linkedHashMapRoot;
	}

	public void setEuclid(String euclid) {
		this.euclid = euclid;
	}

	public void setWords(Map<String, String> words) {
		this.words = words;
	}

	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot() {
		return this.linkedHashMapRoot;
	}
	
	public Map<String, FHHMMNode> getFMap() {
		return this.linkedHashMap;
	}
	
	public void index() throws IOException {
		words = new LinkedHashMap <>();
		euclid = DataString.EMPTY_STRING;
		linkedHashMap = new LinkedHashMap <>();
		linkedHashMapRoot = new LinkedHashMap <>();
		InputStream in = getClass().getResourceAsStream(DataString.WORDS_SOURSE_LINK);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, DataString.GBK_STRING));  
		String cInputString = null; 
		while ((cInputString = cReader.readLine()) != null) {  
			if(!cInputString.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.
					EMPTY_STRING)&&cInputString.split(DataString.SLASH_STRING).length > DataString.INT_ONE) {
					words.put(cInputString.split(DataString.SLASH_STRING)[DataString.INT_ZERO], cInputString.
							split(DataString.SLASH_STRING)[DataString.INT_ONE]);	
					linkedHashMap = loopLoadForest(cInputString);		 
			}
		}
		cReader.close();
		linkedHashMapRoot = new UtilsImp().hashEuclid(linkedHashMap);	
		InputStream ojld = getClass().getResourceAsStream(DataString.OGLD_SOURSE_LINK);
		BufferedReader cReaderojld = new BufferedReader(new InputStreamReader(ojld, DataString.GBK_STRING));  
		String cInputStringojld  = null; 
		while ((cInputStringojld = cReaderojld.readLine()) != null) {  
			if(!cInputStringojld.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				 StringBuilder stringBuilder = new StringBuilder();
				 stringBuilder.append(euclid);
				 stringBuilder.append(cInputStringojld);
				 euclid = stringBuilder.toString();
			}
		}
		cReaderojld.close();
	}

	public Map<String, FHHMMNode> loopLoadForest(String cInputString) {
		for(int i = DataString.INT_ZERO; i < cInputString.length(); i++) {
			if(linkedHashMap.containsKey(DataString.EMPTY_STRING + cInputString.charAt(i))) {
				FHHMMNode fFHMMNode = linkedHashMap.get(DataString.EMPTY_STRING + cInputString.charAt(i));
				linkedHashMap = doNeroPostCognitive(fFHMMNode,cInputString,i);
			}else {
				FHHMMNode fFHMMNode = new FHHMMNode();
				fFHMMNode.setVb(DataString.EMPTY_STRING + cInputString.charAt(i));
				if(i + DataString.INT_ONE < cInputString.length()) {
					Map<String, Integer> next = new LinkedHashMap<>();
					next.put(DataString.EMPTY_STRING + cInputString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
					fFHMMNode.setNext(next);
				}
				linkedHashMap.put(DataString.EMPTY_STRING + cInputString.charAt(i), fFHMMNode);
			}
		}
		return linkedHashMap;
	}

	public Map<String, FHHMMNode> doNeroPostCognitive(FHHMMNode fFHMMNode, String cInputString, int i) {
		if(fFHMMNode.getNext() != null) {
			if(i + DataString.INT_ONE < cInputString.length()) {
				linkedHashMap=doCheckAndRunNeroPostFix(fFHMMNode,cInputString,i);
			}
		}else {
			LinkedHashMap<String, Integer>  innerLinkedHashMap = new  LinkedHashMap<> ();
			if(i + DataString.INT_ONE < cInputString.length()) {
				innerLinkedHashMap.put(DataString.EMPTY_STRING + cInputString.charAt(i + DataString.INT_ONE), 
						DataString.INT_ONE);
			} 
			fFHMMNode.setNext(innerLinkedHashMap);
			linkedHashMap.put(DataString.EMPTY_STRING + cInputString.charAt(i), fFHMMNode);
		}
		return linkedHashMap;
	}

	public Map<String, FHHMMNode> doCheckAndRunNeroPostFix(FHHMMNode fFHMMNode, String cInputString, int i) {
		if(!fFHMMNode.getNext().containsKey(DataString.EMPTY_STRING + cInputString.charAt(i + DataString.INT_ONE))) {
			Map<String, Integer> innerMap = fFHMMNode.getNext();
			innerMap.put(DataString.EMPTY_STRING + cInputString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
			fFHMMNode.setNext(innerMap);
			linkedHashMap.put(DataString.EMPTY_STRING + cInputString.charAt(i), fFHMMNode);
		}
		return linkedHashMap;
	}
	
	public String getEuclid() {
		return this.euclid;
	}

	public Map<String, String> getWords() {
		return this.words;
	}
}