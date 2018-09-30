package org.tinos.utils;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.obj.FHHMMNode;
import org.tinos.zabbi.DataString;
public interface Utils{
	@SuppressWarnings({ DataString.RAW_TYPES})
	public Map <Integer, Map> lEuclid(Map<String, FLHMMNode> concurrentHashMap);
	@SuppressWarnings({ DataString.RAW_TYPES})
	public Map<Integer, Map> hEuclid(Map<String, FHHMMNode> concurrentHashMap);	
	@SuppressWarnings({ DataString.RAW_TYPES})
	public Map<Integer, Map> mEuclid(Map<String, FHHMMNode> concurrentHashMap);
}
