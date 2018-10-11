package org.tinos.engine.euclid;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
public abstract interface EuclidController{
	@SuppressWarnings({StableData.RAW_TYPES})
	public Map<Integer, Map> mcogsEuclid(Map<String, FMHMMNode> concurrentHashMap);
	}
