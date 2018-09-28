package org.tinos.utils;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.zabbi.DataString;
public interface Utils{
	@SuppressWarnings({DataString.RAW_TYPES})
	public LinkedHashMap <Integer,LinkedHashMap> OGLD(LinkedHashMap<String, FDHMMNode> linkedHashMap);
	@SuppressWarnings({DataString.RAW_TYPES})
	public LinkedHashMap <Integer,LinkedHashMap> FOGLD(LinkedHashMap<String, FFHMMNode> linkedHashMap);
}
