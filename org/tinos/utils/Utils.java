package org.tinos.utils;
import java.util.Map;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.zabbi.DataString;
public interface Utils{
	@SuppressWarnings({DataString.RAW_TYPES})
	public Map <Integer,Map> euclid(Map<String, FDHMMNode> linkedHashMap);
	@SuppressWarnings({DataString.RAW_TYPES})
	public Map <Integer,Map> fEuclid(Map<String, FFHMMNode> linkedHashMap);
}
