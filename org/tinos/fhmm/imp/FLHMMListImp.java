package org.tinos.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import org.tinos.fhmm.FLHMMList;
import org.tinos.obj.FLHMMNode;
import org.tinos.utils.imp.UtilsImp;
import org.tinos.zabbi.DataString;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
public class FLHMMListImp implements FLHMMList{
	private String euclid;
	private Map <String, String> words;
	private Map <String, FLHMMNode> linkedHashMap;
	@SuppressWarnings(DataString.RAW_TYPES)
	private Map<Integer, Map> linkedHashMapRoot;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot() {
		return this.linkedHashMapRoot;
	}
	
	public Map<String, FLHMMNode> getForestMaps() {
		return this.linkedHashMap;
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public void index() throws IOException {
		words = new ConcurrentHashMap <String, String>();
		euclid = DataString.EMPTY_STRING;
		linkedHashMap = new ConcurrentHashMap <String, FLHMMNode>();
		linkedHashMapRoot = new ConcurrentHashMap <Integer, Map>();
		InputStream in = getClass().getResourceAsStream(DataString.WORDS_SOURSE_LINK);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, DataString.GBK_STRING));  
		String cTempString = null; 
		while ((cTempString = cReader.readLine()) != null) {  		
			if(!cTempString.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.
					EMPTY_STRING)&&cTempString.split(DataString.SLASH_STRING).length > DataString.INT_ONE) {
					words.put(cTempString.split(DataString.SLASH_STRING)[DataString.INT_ZERO], cTempString.
							split(DataString.SLASH_STRING)[DataString.INT_ONE]);	
					linkedHashMap = loopLoadForest(cTempString);		 
			}
		}
		cReader.close();
		linkedHashMapRoot = new UtilsImp().linerEuclid(linkedHashMap);	
		InputStream ojld = getClass().getResourceAsStream(DataString.OGLD_SOURSE_LINK);
		BufferedReader cReaderojld = new BufferedReader(new InputStreamReader(ojld, DataString.GBK_STRING));  
		String cTempStringojld = null; 
		while ((cTempStringojld = cReaderojld.readLine()) != null) {  
			if(!cTempStringojld.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				 StringBuilder bld = new StringBuilder();
				 bld.append(euclid);
				 bld.append(cTempStringojld);
				 euclid = bld.toString();
			}
		}
		cReaderojld.close();
	}

	public Map<String, FLHMMNode> loopLoadForest(String cTempString) {
		for(int i = DataString.INT_ZERO; i < cTempString.length(); i++) {
			if(linkedHashMap.containsKey(DataString.EMPTY_STRING + cTempString.charAt(i))) {
				FLHMMNode fDHMMNode = linkedHashMap.get(DataString.EMPTY_STRING + cTempString.charAt(i));
				linkedHashMap = doNeroPostCognitive(fDHMMNode,cTempString,i);
			}else {
				FLHMMNode fDHMMNode = new FLHMMNode();
				fDHMMNode.setVb(DataString.EMPTY_STRING + cTempString.charAt(i));
				if(i + DataString.INT_ONE < cTempString.length()) {
					List<String> next = new CopyOnWriteArrayList<String> ();
					next.add(DataString.EMPTY_STRING + cTempString.charAt(i + DataString.INT_ONE));
					fDHMMNode.setNext(next);
				}
				linkedHashMap.put(DataString.EMPTY_STRING + cTempString.charAt(i), fDHMMNode);
			}
		}
		return linkedHashMap;
	}

	public Map<String, FLHMMNode> doNeroPostCognitive(FLHMMNode fDHMMNode, String cTempString, int Positon) {
		if(fDHMMNode.getNext() != null) {
			List<String> temp = fDHMMNode.getNext();
			int find = DataString.INT_ZERO;
			for(int j = DataString.INT_ZERO; j < temp.size(); j++) {
				if(Positon + DataString.INT_ONE < cTempString.length()) {
					find = docheckNeroPostFix(temp, j, cTempString, Positon, find);
				}	 
			}
			if(find == DataString.INT_ZERO) {
				linkedHashMap = doRunNeroPostFIX(Positon, cTempString, fDHMMNode, temp);
			}
		}else {
			List<String> temp = new CopyOnWriteArrayList<String>();
			if(Positon + DataString.INT_ONE < cTempString.length()) {
				temp.add(DataString.EMPTY_STRING + cTempString.charAt(Positon + DataString.INT_ONE));
			} 
			fDHMMNode.setNext(temp);
			linkedHashMap.put(DataString.EMPTY_STRING + cTempString.charAt(Positon), fDHMMNode);
		}
		return linkedHashMap;
	}

	public Map<String, FLHMMNode> doRunNeroPostFIX(int i, String cTempString, FLHMMNode fDHMMNode, List<String> temp) {
		if(i + DataString.INT_ONE < cTempString.length()) {
			temp.add(DataString.EMPTY_STRING + cTempString.charAt(i+DataString.INT_ONE));
			fDHMMNode.setNext(temp);
			linkedHashMap.put(DataString.EMPTY_STRING + cTempString.charAt(i), fDHMMNode);
		}
		return linkedHashMap;
	}

	public int docheckNeroPostFix(List<String> temp, int j, String cTempString, int i, int find) {
		if(temp.get(j).equalsIgnoreCase(DataString.EMPTY_STRING + 
				cTempString.charAt(i + DataString.INT_ONE))){
			find = DataString.INT_ONE;
		}
		return find;
	}

	public String getEuclid() {
		return this.euclid;
	}

	public Map<String, String> getWords() {
		return this.words;
	}
	
	public Map<String, FLHMMNode> getLinkedHashMap() {
		return linkedHashMap;
	}

	public void setLinkedHashMap(Map<String, FLHMMNode> linkedHashMap) {
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
	
}