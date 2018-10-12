package org.tinos.engine.euclid.imp;

import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.tinos.engine.euclid.EuclidController;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;

public class EuclidControllerImp implements EuclidController {
    @SuppressWarnings({StableData.RAW_TYPES, StableData.UNCHECKED})
    public Map<Integer, Map> mCogsEuclid(Map<String, FMHMMNode> concurrentHashMap) {
        Map<Integer, Map> concurrentHashMapRoot = new ConcurrentHashMap<>();
        Iterator<String> iter = concurrentHashMap.keySet().iterator();
        while (iter.hasNext()) {
            String keyValue = iter.next();
            char charOfKeyValue = keyValue.charAt(StableData.INT_ZERO);
            Integer charOfKeyValueToInteger = Integer.valueOf(charOfKeyValue);
            int range = (charOfKeyValueToInteger.intValue() >> StableData.INT_SIX);
            int rangeHigh = range >> StableData.INT_FOUR;
            if (concurrentHashMapRoot.containsKey(rangeHigh)) {
                Map<Integer, ConcurrentHashMap> root = concurrentHashMapRoot.get(rangeHigh);
                if (root.containsKey(range)) {
                    ConcurrentHashMap<String, FMHMMNode> innerConcurrentHashMap = root.get(range);
                    innerConcurrentHashMap.put(keyValue, concurrentHashMap.get(keyValue));
                    root.put(range, innerConcurrentHashMap);
                    concurrentHashMapRoot.put(rangeHigh, root);
                } else {
                    ConcurrentHashMap<String, FMHMMNode> innerConcurrentHashMap = new ConcurrentHashMap<>();
                    innerConcurrentHashMap.put(keyValue, concurrentHashMap.get(keyValue));
                    root.put(range, innerConcurrentHashMap);
                    concurrentHashMapRoot.put(rangeHigh, root);
                }
            } else {
                ConcurrentHashMap<String, FMHMMNode> innerConcurrentHashMap
                        = new ConcurrentHashMap<>();
                innerConcurrentHashMap.put(keyValue, concurrentHashMap.get(keyValue));
                ConcurrentHashMap<Integer, ConcurrentHashMap> root = new ConcurrentHashMap<>();
                root.put(range, innerConcurrentHashMap);
                concurrentHashMapRoot.put(rangeHigh, root);
            }
        }
        return concurrentHashMapRoot;
    }
}
