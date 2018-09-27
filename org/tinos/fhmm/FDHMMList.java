package org.tinos.fhmm;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
import org.tinos.zabbi.DataString;
public interface FDHMMList{
	public void index() throws IOException;
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap<Integer, LinkedHashMap> getRoot() ;
	public LinkedHashMap<String, FDHMMNode> getMap() ;
	public String getEuclid() ;
	public LinkedHashMap<String, Integer> getChengYu();
	
}
