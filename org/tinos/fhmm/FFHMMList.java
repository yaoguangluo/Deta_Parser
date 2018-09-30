package org.tinos.fhmm;
import java.io.IOException;
import java.util.Map;
import org.tinos.obj.FFHMMNode;
import org.tinos.zabbi.DataString;
public interface FFHMMList extends FHMMList{
	public Map<String, FFHMMNode> getFMap();
	public Map<String, FFHMMNode> getLinkedHashMap();
	public void setLinkedHashMap(Map<String, FFHMMNode> linkedHashMap);
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot() ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public void setLinkedHashMapRoot(Map<Integer, Map> linkedHashMapRoot) ;
	public void setEuclid(String euclid) ;
	public void setWords(Map<String, Integer> words) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot();
	public void index() throws IOException;
	public Map<String, FFHMMNode> loopLoadForest(String ctempString) ;
	public Map<String, FFHMMNode> doNeroPostCognitive(FFHMMNode fFHMMNode, String ctempString, int i) ;
	public Map<String, FFHMMNode> doCheckAndRunNeroPostFix(FFHMMNode fFHMMNode, String ctempString, int i);
	@Override
	public String getEuclid();
	@Override
	public Map<String, Integer> getWords();
}
