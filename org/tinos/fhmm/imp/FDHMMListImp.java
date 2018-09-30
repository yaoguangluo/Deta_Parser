package org.tinos.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.tinos.fhmm.FDHMMList;
import org.tinos.obj.FDHMMNode;
import org.tinos.utils.imp.UtilsImp;
import org.tinos.zabbi.DataString;
public class FDHMMListImp implements FDHMMList{
	private String euclid;
	private Map <String, Integer> words;
	private Map <String, FDHMMNode> linkedHashMap;
	@SuppressWarnings(DataString.RAW_TYPES)
	private Map<Integer, Map> linkedHashMapRoot;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot() {
		return this.linkedHashMapRoot;
	}
	
	public Map<String, FDHMMNode> getMap() {
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
		linkedHashMapRoot = new UtilsImp().euclid(linkedHashMap);	
		InputStream ojld = getClass().getResourceAsStream(DataString.OGLD_SOURSE_LINK);
		BufferedReader cReaderojld = new BufferedReader(new InputStreamReader(ojld, DataString.GBK_STRING));  
		String ctempStringojld = null; 
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

	public Map<String, FDHMMNode> loopLoadForest(String ctempString) {
		for(int i = DataString.INT_ZERO; i < ctempString.length(); i++) {
			if(linkedHashMap.containsKey(DataString.EMPTY_STRING + ctempString.charAt(i))) {
				FDHMMNode fDHMMNode = linkedHashMap.get(DataString.EMPTY_STRING + ctempString.charAt(i));
				linkedHashMap = doNeroPostCognitive(fDHMMNode,ctempString,i);
			}else {
				FDHMMNode fDHMMNode = new FDHMMNode();
				fDHMMNode.setVb(DataString.EMPTY_STRING + ctempString.charAt(i));
				if(i + DataString.INT_ONE < ctempString.length()) {
					List<String> next = new CopyOnWriteArrayList<> ();
					next.add(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE));
					fDHMMNode.setNext(next);
				}
				linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fDHMMNode);
			}
		}
		return linkedHashMap;
	}

	public Map<String, FDHMMNode> doNeroPostCognitive(FDHMMNode fDHMMNode, String ctempString, int i) {
		if(fDHMMNode.getNext() != null) {
			List<String> temp = fDHMMNode.getNext();
			int find = DataString.INT_ZERO;
			for(int j = DataString.INT_ZERO; j < temp.size(); j++) {
				if(i + DataString.INT_ONE < ctempString.length()) {
					find = docheckNeroPostFix(temp, j, ctempString, i, find);
				}	 
			}
			if(find == DataString.INT_ZERO) {
				linkedHashMap = doRunNeroPostFIX(i, ctempString, fDHMMNode, temp);
			}
		}else {
			List<String> temp = new CopyOnWriteArrayList<>();
			if(i + DataString.INT_ONE < ctempString.length()) {
				temp.add(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE));
			} 
			fDHMMNode.setNext(temp);
			linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fDHMMNode);
		}
		return linkedHashMap;
	}

	public Map<String, FDHMMNode> doRunNeroPostFIX(int i, String ctempString, FDHMMNode fDHMMNode, List<String> temp) {
		if(i + DataString.INT_ONE < ctempString.length()) {
			temp.add(DataString.EMPTY_STRING + ctempString.charAt(i+DataString.INT_ONE));
			fDHMMNode.setNext(temp);
			linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fDHMMNode);
		}
		return linkedHashMap;
	}

	public int docheckNeroPostFix(List<String> temp, int j, String ctempString, int i, int find) {
		if(temp.get(j).equalsIgnoreCase(DataString.EMPTY_STRING + 
				ctempString.charAt(i + DataString.INT_ONE))){
			find = DataString.INT_ONE;
		}
		return find;
	}

	public String getEuclid() {
		return this.euclid;
	}

	public Map<String, Integer> getWords() {
		return this.words;
	}
	
	public Map<String, FDHMMNode> getLinkedHashMap() {
		return linkedHashMap;
	}

	public void setLinkedHashMap(Map<String, FDHMMNode> linkedHashMap) {
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
	
}