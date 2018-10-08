package org.tinos.fhmm;
import java.util.List;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.zabbi.DataString;
public interface FLHMMList extends FHMMList{
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot();
	public void setWords(Map<String, String> words);
	public Map<String, FLHMMNode> getForestMaps();
	public Map<String, FLHMMNode> getLinkedHashMap() ;
	public Map<String, FLHMMNode> loopLoadForest(String cTempString);
	public void setLinkedHashMap(Map<String, FLHMMNode> linkedHashMap);
	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot);
	public int docheckNeroPostFix(List<String> temp, int j, String cTempString, 
			int i, int find) ;
	public Map<String, FLHMMNode> doNeroPostCognitive(FLHMMNode fDHMMNode, String
			cTempString, int Positon) ;
	public Map<String, FLHMMNode> doRunNeroPostFIX(int i, String cTempString, 
			FLHMMNode fDHMMNode, List<String> temp);
}