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
	public Map <Integer, Map> lEuclid(Map<String, FLHMMNode> concurrentHashMap) {
		Map <Integer, Map> concurrentHashMapRoot = new LinkedHashMap<>();
		Iterator<String> iter = concurrentHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(concurrentHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,Map> root = concurrentHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					Map <String, FLHMMNode> temp = root.get(range); 
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FLHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FLHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <>();
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return concurrentHashMapRoot;
	}
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map<Integer, Map> hEuclid(Map<String, FHHMMNode> concurrentHashMap) {
		Map <Integer, Map> concurrentHashMapRoot = new LinkedHashMap <>();
		Iterator<String> iter = concurrentHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(concurrentHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,LinkedHashMap> root = concurrentHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					LinkedHashMap <String, FHHMMNode> temp = root.get(range); 
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}else {
					LinkedHashMap <String, FHHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}
			}else {
					LinkedHashMap <String, FHHMMNode> temp = new LinkedHashMap <>();
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					LinkedHashMap <Integer,LinkedHashMap> root = new LinkedHashMap <>();
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return concurrentHashMapRoot;
	}
	
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map<Integer, Map> mEuclid(Map<String, FHHMMNode> concurrentHashMap) {
		Map <Integer, Map> concurrentHashMapRoot = new ConcurrentHashMap <>();
		Iterator<String> iter = concurrentHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(concurrentHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,ConcurrentHashMap> root = concurrentHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					ConcurrentHashMap <String, FHHMMNode> temp = root.get(range); 
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}else {
					ConcurrentHashMap <String, FHHMMNode> temp = new ConcurrentHashMap <>();
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}
			}else {
					ConcurrentHashMap <String, FHHMMNode> temp = new ConcurrentHashMap <>();
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					ConcurrentHashMap <Integer,ConcurrentHashMap> root = new ConcurrentHashMap <>();
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return concurrentHashMapRoot;
	}
}
