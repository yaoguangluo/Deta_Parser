package org.tinos.fhmm;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
public interface FDHMMList extends FHMMList{
	public void index() throws IOException;
	public LinkedHashMap<String, FDHMMNode> getMap();
}