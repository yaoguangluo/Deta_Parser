package org.tinos.fhmm;
import java.io.IOException;
import java.util.Map;
import org.tinos.zabbi.DataString;
public abstract interface FHMMList{
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot();
	public Map<String, Integer> getWords();
	public void index() throws IOException;
	public String getEuclid();
	public void setEuclid(String euclid) ;
	public void setWords(Map<String, Integer> words) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot() ;
}