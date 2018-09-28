package org.tinos.fhmm;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
public interface FDHMMList extends FHMMList{
	public LinkedHashMap<String, FDHMMNode> getMap();
}