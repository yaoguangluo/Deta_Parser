package org.tinos.fhmm;
import java.util.List;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.zabbi.DataString;
public interface FLHMMList extends FHMMList{
	public Map<String, FLHMMNode> loopLoadForest(String cTempString) ;
	public Map<String, FLHMMNode> doNeroPostCognitive(FLHMMNode fDHMMNode, String cTempString, int i) ;
	public Map<String, FLHMMNode> doRunNeroPostFIX(int i, String cTempString, FLHMMNode fDHMMNode, List<String> temp);
	public int docheckNeroPostFix(List<String> temp, int j, String cTempString, int i, int find) ;
	public Map<String, FLHMMNode> getLinkedHashMap() ;
	public void setLinkedHashMap(Map<String, FLHMMNode> linkedHashMap) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot();
	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot);
	public void setWords(Map<String, Integer> words);
	public Map<String, FLHMMNode> getForestMaps();
}