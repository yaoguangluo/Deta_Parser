package org.tinos.utils.imp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
import org.tinos.utils.Utils;
import org.tinos.zabbi.DataString;
public class UtilsImp implements Utils{
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public LinkedHashMap <Integer,LinkedHashMap> OGLD(LinkedHashMap<String, FDHMMNode> linkedHashMap) {
		LinkedHashMap <Integer,LinkedHashMap> linkedHashMapRoot = new LinkedHashMap <Integer,LinkedHashMap>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String key_value = iter.next();
			int range = ((int)(key_value.charAt(DataString.INT_ZERO))/DataString.INT_SIXTY_FOUR);
			int rangehHigh = range/DataString.INT_SIXTEEN;
			if(linkedHashMapRoot.containsKey(rangehHigh)) {
				LinkedHashMap <Integer,LinkedHashMap> root = linkedHashMapRoot.get(rangehHigh);
				if(root.containsKey(range)) {
					LinkedHashMap <String, FDHMMNode> temp = root.get(range); 
					temp.put(key_value,linkedHashMap.get(key_value));
					root.put(range, temp);
					linkedHashMapRoot.put(rangehHigh, root);
				}else {
					LinkedHashMap <String, FDHMMNode> temp = new LinkedHashMap <String, FDHMMNode>();
					temp.put(key_value,linkedHashMap.get(key_value));
					root.put(range, temp);
					linkedHashMapRoot.put(rangehHigh, root);
				}
			}else {
					LinkedHashMap <String, FDHMMNode> temp = new LinkedHashMap <String, FDHMMNode>();
					temp.put(key_value, linkedHashMap.get(key_value));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <Integer,LinkedHashMap>();
					root.put(range, temp);
					linkedHashMapRoot.put(rangehHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
}
