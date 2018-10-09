package org.tinos.fhmm;
import java.util.Map;
import org.tinos.obj.FHHMMNode;
import org.tinos.zabbi.DataString;
public interface FHHMMList extends FHMMList{
	public Map<String, FHHMMNode> getFMap();
	public Map<String, FHHMMNode> getLinkedHashMap();
	public void setLinkedHashMap(Map<String, FHHMMNode> linkedHashMap);
	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot) ;
	public Map<String, FHHMMNode> loopLoadForest(String cInputString) ;
	public Map<String, FHHMMNode> doNeroPostCognitive(FHHMMNode fFHMMNode,
			String cInputString, int i) ;
	public Map<String, FHHMMNode> doCheckAndRunNeroPostFix(FHHMMNode fFHMMNode,
			String cInputString, int i);
}