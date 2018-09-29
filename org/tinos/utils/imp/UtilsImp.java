package org.tinos.utils.imp;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.utils.Utils;
import org.tinos.zabbi.DataString;
public class UtilsImp implements Utils{
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map <Integer, Map> euclid(Map<String, FDHMMNode> concurrentHashMap) {
		Map <Integer, Map> concurrentHashMapRoot = new ConcurrentHashMap<>();
		Iterator<String> iter = concurrentHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(concurrentHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,Map> root = concurrentHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					Map <String, FDHMMNode> temp = root.get(range); 
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}else {
					ConcurrentHashMap <String, FDHMMNode> temp = new ConcurrentHashMap <>();
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}
			}else {
					ConcurrentHashMap <String, FDHMMNode> temp = new ConcurrentHashMap <>();
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					ConcurrentHashMap <Integer,ConcurrentHashMap> root = new ConcurrentHashMap <>();
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return concurrentHashMapRoot;
	}
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED })
	public Map<Integer, Map> fEuclid(Map<String, FFHMMNode> concurrentHashMap) {
		Map <Integer, Map> concurrentHashMapRoot = new ConcurrentHashMap <>();
		Iterator<String> iter = concurrentHashMap.keySet().iterator();
		while (iter.hasNext()) {
			String keyValue = iter.next();
			int range = ((int)(keyValue.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
			int rangeHigh = range >> DataString.INT_FOUR; 
			if(concurrentHashMapRoot.containsKey(rangeHigh)) {
				Map <Integer,ConcurrentHashMap> root = concurrentHashMapRoot.get(rangeHigh);
				if(root.containsKey(range)) {
					ConcurrentHashMap <String, FFHMMNode> temp = root.get(range); 
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}else {
					ConcurrentHashMap <String, FFHMMNode> temp = new ConcurrentHashMap <>();
					temp.put(keyValue,concurrentHashMap.get(keyValue));
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);
				}
			}else {
					ConcurrentHashMap <String, FFHMMNode> temp = new ConcurrentHashMap <>();
					temp.put(keyValue, concurrentHashMap.get(keyValue));
					ConcurrentHashMap <Integer,ConcurrentHashMap> root = new ConcurrentHashMap <>();
					root.put(range, temp);
					concurrentHashMapRoot.put(rangeHigh, root);	
			}		
		}
		return concurrentHashMapRoot;
	}
}
