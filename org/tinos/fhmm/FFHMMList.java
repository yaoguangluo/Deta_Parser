package org.tinos.fhmm;
import java.util.LinkedHashMap;
import org.tinos.obj.FFHMMNode;
public interface FFHMMList extends FHMMList{
	public LinkedHashMap<String, FFHMMNode> getFMap() ;
}