package org.tinos.fhmm;
import java.io.IOException;
import java.util.Map;
import org.tinos.zabbi.DataString;
public abstract interface FHMMList{
	public void index() throws IOException;
	public String getEuclid();
	public void setEuclid(String euclid) ;
	public void setWords(Map<String, String> words);
	public Map<String, String> getWords();
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getRoot();
	@SuppressWarnings(DataString.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot();
}