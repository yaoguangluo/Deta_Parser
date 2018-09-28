package org.tinos.fhmm;
import java.util.LinkedHashMap;
import org.tinos.zabbi.DataString;
public abstract interface FHMMList{
	@SuppressWarnings(DataString.RAW_TYPES)
	public LinkedHashMap<Integer, LinkedHashMap> getRoot();
	public String getEuclid();
	public LinkedHashMap<String, Integer> getWords();
}