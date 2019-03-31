package org.tinos.engine.euclid.imp;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.concurrent.HashMap;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
import org.tinos.engine.euclid.EuclidController;
public class EuclidControllerImp implements EuclidController {
	@SuppressWarnings({StableData.RAW_TYPES, StableData.UNCHECKED})
	public Map<Integer, Map> mCogsEuclid(Map<Long, FMHMMNode> HashMap) {
		Map<Integer, Map> HashMapRoot= new HashMap<>();
		Iterator<Long> iter= HashMap.keySet().iterator();
		Here:
			while (iter.hasNext()) {
				Long keyValue= iter.next();
				Integer charOfKeyValueToInteger= Integer.valueOf(StableData.EMPTY_STRING + keyValue);
				int range= (charOfKeyValueToInteger.intValue()>> StableData.INT_SIX);
				int rangeHigh= range >> StableData.INT_FOUR;
				if (!HashMapRoot.containsKey(rangeHigh)) {
					HashMap<Long, FMHMMNode> innerHashMap = new HashMap<>();
					innerHashMap.put(keyValue, HashMap.get(keyValue));
					HashMap<Integer, HashMap> root = new HashMap<>();
					root.put(range, innerHashMap);
					HashMapRoot.put(rangeHigh, root);
					continue Here;
				}
				Map<Integer, HashMap> root= HashMapRoot.get(rangeHigh);
				if (!root.containsKey(range)) {
					HashMap<Long, FMHMMNode> innerHashMap = new HashMap<>();
					innerHashMap.put(keyValue, HashMap.get(keyValue));
					root.put(range, innerHashMap);
					HashMapRoot.put(rangeHigh, root);
					continue Here;
				}
				HashMap<Long, FMHMMNode> innerHashMap = root.get(range);
				innerHashMap.put(keyValue, HashMap.get(keyValue));
				root.put(range, innerHashMap);
				HashMapRoot.put(rangeHigh, root);
			}
		return HashMapRoot;
	}
}
