package org.tinos.engine.euclid;

import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;

import java.util.Map;

public interface EuclidController {
    @SuppressWarnings({StableData.RAW_TYPES})
    Map<Integer, Map> mCogsEuclid(Map<String, FMHMMNode> concurrentHashMap);
}
