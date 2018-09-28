package org.tinos.fhmm;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.tinos.zabbi.DataString;
public abstract interface FHMMList{
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap<Integer, LinkedHashMap> getRoot();
	public LinkedHashMap<String, Integer> getWords();
	public void index() throws IOException;
	public String getEuclid();
}