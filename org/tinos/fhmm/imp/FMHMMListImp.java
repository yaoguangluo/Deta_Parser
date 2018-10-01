package org.tinos.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.tinos.fhmm.FMHMMList;
import org.tinos.obj.FHHMMNode;
import org.tinos.utils.imp.UtilsImp;
import org.tinos.zabbi.DataString;
public class FMHMMListImp implements FMHMMList{
	private String euclid;
	private Map <String, Integer> words;
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

	public void setWords(Map<String, Integer> words) {
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
		words = new ConcurrentHashMap <>();
		euclid = DataString.EMPTY_STRING;
		linkedHashMap = new ConcurrentHashMap <>();
		linkedHashMapRoot = new ConcurrentHashMap <>();
		InputStream in = getClass().getResourceAsStream(DataString.WORDS_SOURSE_LINK);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, DataString.GBK_STRING));  
		String ctempString = null; 
		while ((ctempString = cReader.readLine()) != null) {  
			if(!ctempString.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.
					EMPTY_STRING)) {
				words.put(ctempString, DataString.INT_ONE);
				linkedHashMap = loopLoadForest(ctempString);
				 
			}
		}
		cReader.close();
		linkedHashMapRoot = new UtilsImp().mcogsEuclid(linkedHashMap);	
		InputStream ojld = getClass().getResourceAsStream(DataString.OGLD_SOURSE_LINK);
		BufferedReader cReaderojld = new BufferedReader(new InputStreamReader(ojld, DataString.GBK_STRING));  
		String ctempStringojld  = null; 
		while ((ctempStringojld = cReaderojld.readLine()) != null) {  
			if(!ctempStringojld.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				 StringBuilder bld = new StringBuilder();
				 bld.append(euclid);
				 bld.append(ctempStringojld);
				 euclid = bld.toString();
			}
		}
		cReaderojld.close();
	}

	public Map<String, FHHMMNode> loopLoadForest(String ctempString) {
		for(int i = DataString.INT_ZERO; i < ctempString.length(); i++) {
			if(linkedHashMap.containsKey(DataString.EMPTY_STRING + ctempString.charAt(i))) {
				FHHMMNode fHHMMNode = linkedHashMap.get(DataString.EMPTY_STRING + ctempString.charAt(i));
				linkedHashMap = doNeroPostCognitive(fHHMMNode,ctempString,i);
			}else {
				FHHMMNode fHHMMNode = new FHHMMNode();
				fHHMMNode.setVb(DataString.EMPTY_STRING + ctempString.charAt(i));
				if(i + DataString.INT_ONE < ctempString.length()) {
					Map<String, Integer> next = new ConcurrentHashMap<>();
					next.put(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
					fHHMMNode.setNext(next);
				}
				linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fHHMMNode);
			}
		}
		return linkedHashMap;
	}

	public Map<String, FHHMMNode> doNeroPostCognitive(FHHMMNode fFHMMNode, String ctempString, int i) {
		if(fFHMMNode.getNext() != null) {
			if(i + DataString.INT_ONE < ctempString.length()) {
				linkedHashMap=doCheckAndRunNeroPostFix(fFHMMNode,ctempString,i);
			}
		}else {
			ConcurrentHashMap<String, Integer>  temp = new  ConcurrentHashMap<> ();
			if(i + DataString.INT_ONE < ctempString.length()) {
				temp.put(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE), 
						DataString.INT_ONE);
			} 
			fFHMMNode.setNext(temp);
			linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fFHMMNode);
		}
		return linkedHashMap;
	}

	public Map<String, FHHMMNode> doCheckAndRunNeroPostFix(FHHMMNode fFHMMNode, String ctempString, int i) {
		if(!fFHMMNode.getNext().containsKey(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE))) {
			Map<String, Integer> temp = fFHMMNode.getNext();
			temp.put(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
			fFHMMNode.setNext(temp);
			linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fFHMMNode);
		}
		return linkedHashMap;
	}
	
	@Override
	public String getEuclid() {
		return this.euclid;
	}

	@Override
	public Map<String, Integer> getWords() {
		return this.words;
	}
}