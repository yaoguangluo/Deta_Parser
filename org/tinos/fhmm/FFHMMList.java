package org.tinos.fhmm;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.zabbi.DataString;
public interface FFHMMList{
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap<Integer, LinkedHashMap> getRoot() ;
	public LinkedHashMap<String, FFHMMNode> getFMap() ;
	public void indexF() throws IOException; 
	public String getEuclid();
	public LinkedHashMap<String, Integer> getWords(); 
	public void index() throws IOException; 
	public LinkedHashMap<String, FDHMMNode> getMap(); 
}