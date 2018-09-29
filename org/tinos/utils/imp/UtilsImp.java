package org.tinos.utils.imp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.utils.Utils;
import org.tinos.zabbi.DataString;
public class UtilsImp implements Utils{
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map <Integer, Map> euclid(Map<String, FDHMMNode> linkedHashMap) {
		Map <Integer, Map> linkedHashMapRoot = new LinkedHashMap<>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(linkedHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,Map> root = linkedHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					Map <String, FDHMMNode> temp = root.get(range); 
					temp.put(keyValue,linkedHashMap.get(keyValue));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FDHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue,linkedHashMap.get(keyValue));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FDHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue, linkedHashMap.get(keyValue));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <>();
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map<Integer, Map> fEuclid(Map<String, FFHMMNode> linkedHashMap) {
		Map <Integer, Map> linkedHashMapRoot = new LinkedHashMap <>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(linkedHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,LinkedHashMap> root = linkedHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					LinkedHashMap <String, FFHMMNode> temp = root.get(range); 
					temp.put(keyValue, linkedHashMap.get(keyValue));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FFHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue,linkedHashMap.get(keyValue));
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FFHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue, linkedHashMap.get(keyValue));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <>();
					root.put(range, temp);
					linkedHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
}
