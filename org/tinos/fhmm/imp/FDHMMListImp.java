package org.tinos.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.tinos.fhmm.FDHMMList;
import org.tinos.obj.FDHMMNode;
import org.tinos.utils.imp.UtilsImp;
import org.tinos.zabbi.DataString;
public class FDHMMListImp implements FDHMMList{
	public LinkedHashMap <String,FDHMMNode> linkedHashMap;
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap <Integer,LinkedHashMap> linkedHashMapRoot;
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap<Integer, LinkedHashMap> getRoot() {
		return this.linkedHashMapRoot;
	}
	
	public LinkedHashMap<String, FDHMMNode> getMap() {
		return this.linkedHashMap;
	}
	
	@SuppressWarnings({DataString.RAW_TYPES})
	public void index() throws IOException {
		linkedHashMap = new LinkedHashMap <String,FDHMMNode>();
		linkedHashMapRoot = new LinkedHashMap <Integer,LinkedHashMap>();
		InputStream in = getClass().getResourceAsStream(DataString.WORDS_SOURSE_LINK);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, DataString.GBK_STRING));  
		String ctempString = null; 
		while ((ctempString = cReader.readLine()) != null) {  
			if(!ctempString.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				for(int i = DataString.INT_ZERO; i < ctempString.length(); i++) {
					//you mei you wo
					if(linkedHashMap.containsKey(DataString.EMPTY_STRING + ctempString.charAt(i))) {
						FDHMMNode fDHMMNode = linkedHashMap.get("" + ctempString.charAt(i));
						if(fDHMMNode.next != null) {
							List<String> temp = fDHMMNode.next;
							int find = DataString.INT_ZERO;
							for(int j = DataString.INT_ZERO; j < temp.size(); j++) {
								//you mei you chi
								if(i+1 < ctempString.length()) {
									if(temp.get(j).equalsIgnoreCase(DataString.EMPTY_STRING + ctempString.charAt(i+1))){
										find = DataString.INT_ONE;
									}
								}	 
							}
							if(find == 0) {
								if(i+1 < ctempString.length()) {
									temp.add(DataString.EMPTY_STRING + ctempString.charAt(i+DataString.INT_ONE));
									fDHMMNode.next = temp;
									linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fDHMMNode);
								}
							}
						}else {
							List<String> temp = new ArrayList<String>();
							if(i + DataString.INT_ONE < ctempString.length()) {
								temp.add(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE));
							} 
							fDHMMNode.next = temp;
							linkedHashMap.put("" + ctempString.charAt(i), fDHMMNode);
						}
					}else {
						FDHMMNode fDHMMNode = new FDHMMNode();
						fDHMMNode.vb = DataString.EMPTY_STRING + ctempString.charAt(i);
						if(i + DataString.INT_ONE < ctempString.length()) {
							fDHMMNode.next = new ArrayList<String> ();
							fDHMMNode.next.add(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE));
						}
						linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fDHMMNode);
					}
				}
			}
		}
		cReader.close();
		InputStream out = getClass().getResourceAsStream(DataString.WORDS_SOURSE_LINK);
		BufferedReader cReaderout = new BufferedReader(new InputStreamReader(out, DataString.GBK_STRING));  
		String ctempStringout  = null; 
		while ((ctempStringout = cReaderout.readLine()) != null) {  
			if(!ctempStringout.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				for(int i = ctempStringout.length() - DataString.INT_ONE; i > DataString.INT_ZERO; i--) {
					//you mei you wo
					if(linkedHashMap.containsKey(DataString.EMPTY_STRING + ctempStringout.charAt(i))) {
						FDHMMNode fDHMMNode = linkedHashMap.get(DataString.EMPTY_STRING + ctempStringout.charAt(i));
						if(fDHMMNode.prev != null) {
							List<String> temp = fDHMMNode.prev;
							int find = DataString.INT_ZERO;
							for(int j = DataString.INT_ZERO; j < temp.size(); j++) {
								//you mei you chi
								if(i - DataString.INT_ONE >= DataString.INT_ZERO) {
									if(temp.get(j).equalsIgnoreCase(DataString.EMPTY_STRING + ctempStringout.charAt(i-DataString.INT_ONE))){
										find = DataString.INT_ONE;
									}
								}	 
							}
							if(find == DataString.INT_ZERO) {
								if(i-DataString.INT_ONE >= DataString.INT_ZERO) {
									temp.add(DataString.EMPTY_STRING + ctempStringout.charAt(i - DataString.INT_ONE));
									fDHMMNode.prev = temp;
									linkedHashMap.put(DataString.EMPTY_STRING + ctempStringout.charAt(i), fDHMMNode);
								}
							}
						}else {
							List<String> temp = new ArrayList<String>();
							if(i - DataString.INT_ONE >= DataString.INT_ZERO ) {
								temp.add(DataString.EMPTY_STRING + ctempStringout.charAt(i - DataString.INT_ONE));
							} 
							fDHMMNode.prev = temp;
							linkedHashMap.put(DataString.EMPTY_STRING + ctempStringout.charAt(i), fDHMMNode);
						}
					}else {
						FDHMMNode fDHMMNode = new FDHMMNode();
						fDHMMNode.vb = DataString.EMPTY_STRING + ctempStringout.charAt(i);
						if(i - DataString.INT_ONE >= DataString.INT_ZERO) {
							fDHMMNode.prev = new ArrayList<String> ();
							fDHMMNode.prev.add(DataString.EMPTY_STRING + ctempStringout.charAt(i - DataString.INT_ONE));
						}
						linkedHashMap.put(DataString.EMPTY_STRING + ctempStringout.charAt(i), fDHMMNode);
					}
				}
			}
		}
		cReaderout.close();
		linkedHashMapRoot = new UtilsImp().OGLD(linkedHashMap);	
	}
}