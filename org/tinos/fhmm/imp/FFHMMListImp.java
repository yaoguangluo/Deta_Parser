package org.tinos.fhmm.imp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import org.tinos.fhmm.FFHMMList;
import org.tinos.obj.FFHMMNode;
import org.tinos.utils.imp.UtilsImp;
import org.tinos.zabbi.DataString;
public class FFHMMListImp implements FFHMMList{
	public String euclid;
	public LinkedHashMap <String, Integer> words;
	public LinkedHashMap <String, FFHMMNode> linkedHashMap;
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap <Integer, LinkedHashMap> linkedHashMapRoot;
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap<Integer, LinkedHashMap> getRoot() {
		return this.linkedHashMapRoot;
	}
	
	public LinkedHashMap<String, FFHMMNode> getFMap() {
		return this.linkedHashMap;
	}
	
	@SuppressWarnings({DataString.RAW_TYPES})
	public void index() throws IOException {
		words = new LinkedHashMap <String, Integer>();
		euclid = DataString.EMPTY_STRING;
		linkedHashMap = new LinkedHashMap <String, FFHMMNode>();
		linkedHashMapRoot = new LinkedHashMap <Integer, LinkedHashMap>();
		InputStream in = getClass().getResourceAsStream(DataString.WORDS_SOURSE_LINK);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, DataString.GBK_STRING));  
		String ctempString = null; 
		while ((ctempString = cReader.readLine()) != null) {  
			if(!ctempString.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				words.put(ctempString, DataString.INT_ONE);
				for(int i = DataString.INT_ZERO; i < ctempString.length(); i++) {
					if(linkedHashMap.containsKey(DataString.EMPTY_STRING + ctempString.charAt(i))) {
						FFHMMNode fFHMMNode = linkedHashMap.get(DataString.EMPTY_STRING + ctempString.charAt(i));
						if(fFHMMNode.next != null) {
							if(i + DataString.INT_ONE < ctempString.length()) {
								if(!fFHMMNode.next.containsKey(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE))) {
									fFHMMNode.next.put(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
									linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fFHMMNode);
								}
							}
						}else {
							LinkedHashMap<String, Integer>  temp = new  LinkedHashMap<String, Integer> ();
							if(i + DataString.INT_ONE < ctempString.length()) {
								temp.put(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
							} 
							fFHMMNode.next = temp;
							linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fFHMMNode);
						}
					}else {
						FFHMMNode fFHMMNode = new FFHMMNode();
						fFHMMNode.vb = DataString.EMPTY_STRING + ctempString.charAt(i);
						if(i + DataString.INT_ONE < ctempString.length()) {
							fFHMMNode.next = new LinkedHashMap<String, Integer>();
							fFHMMNode.next.put(DataString.EMPTY_STRING + ctempString.charAt(i + DataString.INT_ONE), DataString.INT_ONE);
						}
						linkedHashMap.put(DataString.EMPTY_STRING + ctempString.charAt(i), fFHMMNode);
					}
				}
			}
		}
		cReader.close();
		linkedHashMapRoot = new UtilsImp().FOGLD(linkedHashMap);	
		InputStream ojld = getClass().getResourceAsStream(DataString.OGLD_SOURSE_LINK);
		BufferedReader cReaderojld = new BufferedReader(new InputStreamReader(ojld, DataString.GBK_STRING));  
		String ctempStringojld  = null; 
		while ((ctempStringojld = cReaderojld.readLine()) != null) {  
			if(!ctempStringojld.replace(DataString.SPACE_STRING, DataString.EMPTY_STRING).equals(DataString.EMPTY_STRING)) {
				euclid += ctempStringojld;
			}
		}
		cReaderojld.close();
	}

	@Override
	public String getEuclid() {
		return this.euclid;
	}

	@Override
	public LinkedHashMap<String, Integer> getWords() {
		return this.words;
	}
}