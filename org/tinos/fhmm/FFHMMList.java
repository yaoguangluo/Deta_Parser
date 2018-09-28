package org.tinos.fhmm;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.tinos.obj.FFHMMNode;
public interface FFHMMList extends FHMMList{
	public LinkedHashMap<String, FFHMMNode> getFMap() ;
	public void index() throws IOException; 
}