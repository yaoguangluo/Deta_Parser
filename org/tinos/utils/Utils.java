package org.tinos.utils;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.obj.FHHMMNode;
import org.tinos.zabbi.DataString;
public abstract interface Utils{
	@SuppressWarnings({DataString.RAW_TYPES})
	public Map <Integer, Map> linerEuclid(Map<String, FLHMMNode> linkedHashMap);
	@SuppressWarnings({DataString.RAW_TYPES})
	public Map<Integer, Map> hashEuclid(Map<String, FHHMMNode> linkedHashMap);	
	@SuppressWarnings({DataString.RAW_TYPES})
	public Map<Integer, Map> mcogsEuclid(Map<String, FHHMMNode> concurrentHashMap);
}
