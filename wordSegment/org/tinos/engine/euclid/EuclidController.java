package org.tinos.engine.euclid;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
public interface EuclidController {
	@SuppressWarnings({StableData.RAW_TYPES})
	Map<Integer, Map> mCogsEuclid(Map<Long, FMHMMNode> concurrentHashMap);
}
