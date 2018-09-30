package org.tinos.fhmm;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.zabbi.DataString;
public interface FLHMMList extends FHMMList{
	public Map<String, FLHMMNode> getMap();
	public void index() throws IOException ;
	public Map<String, FLHMMNode> loopLoadForest(String ctempString) ;
	public Map<String, FLHMMNode> doNeroPostCognitive(FLHMMNode fDHMMNode, String ctempString, int i) ;
	public Map<String, FLHMMNode> doRunNeroPostFIX(int i, String ctempString, FLHMMNode fDHMMNode, List<String> temp);
	public int docheckNeroPostFix(List<String> temp, int j, String ctempString, int i, int find) ;
	public String getEuclid();
	public Map<String, Integer> getWords() ;
	public Map<String, FLHMMNode> getLinkedHashMap() ;
	public void setLinkedHashMap(Map<String, FLHMMNode> linkedHashMap) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot();
	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot);
	public void setEuclid(String euclid) ;
	public void setWords(Map<String, Integer> words);
}