package org.tinos.fhmm;
import java.io.IOException;
import java.util.Map;
import org.tinos.obj.FHHMMNode;
import org.tinos.zabbi.DataString;
public interface FMHMMList extends FHMMList{
	public Map<String, FHHMMNode> getFMap();
	public Map<String, FHHMMNode> getLinkedHashMap();
	public void setLinkedHashMap(Map<String, FHHMMNode> linkedHashMap);
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot() ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot) ;
	public void setEuclid(String euclid) ;
	public void setWords(Map<String, Integer> words) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot();
	public void index() throws IOException;
	public Map<String, FHHMMNode> loopLoadForest(String ctempString) ;
	public Map<String, FHHMMNode> doNeroPostCognitive(FHHMMNode fFHMMNode,
			String ctempString, int i) ;
	public Map<String, FHHMMNode> doCheckAndRunNeroPostFix(FHHMMNode fFHMMNode,
			String ctempString, int i);
	@Override
	public String getEuclid();
	@Override
	public Map<String, Integer> getWords();
}