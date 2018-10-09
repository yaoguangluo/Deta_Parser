package org.tinos.utils.imp;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.obj.FHHMMNode;
import org.tinos.utils.Utils;
import org.tinos.zabbi.DataString;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
public class UtilsImp implements Utils{
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map <Integer, Map> linerEuclid(Map<String, FLHMMNode> linkedHashMap) {
		Map <Integer, Map> linkedHashMapRoot = new LinkedHashMap<>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(linkedHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,Map> root = linkedHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					Map <String, FLHMMNode> innerMap = root.get(range); 
					innerMap.put(keyValue,linkedHashMap.get(keyValue));
					root.put(range, innerMap);
					linkedHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FLHMMNode> innerLinkedHashMap = new LinkedHashMap <>();
					innerLinkedHashMap.put(keyValue,linkedHashMap.get(keyValue));
					root.put(range, innerLinkedHashMap);
					linkedHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FLHMMNode> innerLinkedHashMap = new LinkedHashMap <>();
					innerLinkedHashMap.put(keyValue, linkedHashMap.get(keyValue));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <>();
					root.put(range, innerLinkedHashMap);
					linkedHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map<Integer, Map> hashEuclid(Map<String, FHHMMNode> linkedHashMap) {
		Map <Integer, Map> linkedHashMapRoot = new LinkedHashMap <>();
		Iterator<String> iter = linkedHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			char charOfkeyValue = keyValue.charAt(DataString.INT_ZERO);
			Integer charOfkeyValueToInteger =Integer.valueOf(charOfkeyValue);
			int range = (charOfkeyValueToInteger.intValue() >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(linkedHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,LinkedHashMap> root = linkedHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					LinkedHashMap <String, FHHMMNode> innerLinkedHashMap = root.get(range); 
					innerLinkedHashMap.put(keyValue, linkedHashMap.get(keyValue));
					root.put(range, innerLinkedHashMap);
					linkedHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FHHMMNode> innerLinkedHashMap = new LinkedHashMap <>();
					innerLinkedHashMap.put(keyValue,linkedHashMap.get(keyValue));
					root.put(range, innerLinkedHashMap);
					linkedHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FHHMMNode> innerLinkedHashMap = new LinkedHashMap <>();
					innerLinkedHashMap.put(keyValue, linkedHashMap.get(keyValue));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <>();
					root.put(range, innerLinkedHashMap);
					linkedHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return linkedHashMapRoot;
	}
	
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map<Integer, Map> mcogsEuclid(Map<String, FHHMMNode> concurrentHashMap) {
		Map <Integer, Map> concurrentHashMapRoot = new ConcurrentHashMap <>();
		Iterator<String> iter = concurrentHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			char charOfkeyValue = keyValue.charAt(DataString.INT_ZERO);
			Integer charOfkeyValueToInteger =Integer.valueOf(charOfkeyValue);
			int range = (charOfkeyValueToInteger.intValue() >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(concurrentHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,ConcurrentHashMap> root = concurrentHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					ConcurrentHashMap <String, FHHMMNode> innerConcurrentHashMap = root.get(range); 
					innerConcurrentHashMap.put(keyValue, concurrentHashMap.get(keyValue));
					root.put(range, innerConcurrentHashMap);
					concurrentHashMapRoot.put(rangeHigh, root);
				}else {
					ConcurrentHashMap <String, FHHMMNode> innerConcurrentHashMap = new ConcurrentHashMap <>();
					innerConcurrentHashMap.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, innerConcurrentHashMap);
					concurrentHashMapRoot.put(rangeHigh, root);
				}
			}else {
					ConcurrentHashMap <String, FHHMMNode> innerConcurrentHashMap = new ConcurrentHashMap <>();
					innerConcurrentHashMap.put(keyValue, concurrentHashMap.get(keyValue));
					ConcurrentHashMap <Integer,ConcurrentHashMap> root = new ConcurrentHashMap <>();
					root.put(range, innerConcurrentHashMap);
					concurrentHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return concurrentHashMapRoot;
	}
}
