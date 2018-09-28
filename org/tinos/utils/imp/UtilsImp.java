package org.tinos.utils.imp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.utils.Utils;
import org.tinos.zabbi.DataString;
public class UtilsImp implements Utils{
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public LinkedHashMap <Integer,LinkedHashMap> OGLD(LinkedHashMap<String, FDHMMNode> linkedHashMap) {
		LinkedHashMap <Integer,LinkedHashMap> linkedHashMapRoot = new LinkedHashMap <Integer,LinkedHashMap>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String key_value = iter.next();
			int range = ((int)(key_value.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(linkedHashMapRoot.containsKey(rangeHigh)) {
				LinkedHashMap <Integer,LinkedHashMap> root = linkedHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					LinkedHashMap <String, FDHMMNode> temp = root.get(range); 
					temp.put(key_value,linkedHashMap.get(key_value));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FDHMMNode> temp = new LinkedHashMap <String, FDHMMNode>();
					temp.put(key_value,linkedHashMap.get(key_value));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FDHMMNode> temp = new LinkedHashMap <String, FDHMMNode>();
					temp.put(key_value, linkedHashMap.get(key_value));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <Integer,LinkedHashMap>();
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public LinkedHashMap<Integer, LinkedHashMap> FOGLD(LinkedHashMap<String, FFHMMNode> linkedHashMap) {
		LinkedHashMap <Integer, LinkedHashMap> linkedHashMapRoot = new LinkedHashMap <Integer, LinkedHashMap>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String key_value = iter.next();
			int range = ((int)(key_value.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(linkedHashMapRoot.containsKey(rangeHigh)) {
				LinkedHashMap <Integer,LinkedHashMap> root = linkedHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					LinkedHashMap <String, FFHMMNode> temp = root.get(range); 
					temp.put(key_value, linkedHashMap.get(key_value));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FFHMMNode> temp = new LinkedHashMap <String, FFHMMNode>();
					temp.put(key_value,linkedHashMap.get(key_value));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FFHMMNode> temp = new LinkedHashMap <String, FFHMMNode>();
					temp.put(key_value, linkedHashMap.get(key_value));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <Integer,LinkedHashMap>();
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
}
