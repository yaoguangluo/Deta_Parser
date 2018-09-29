package org.tinos.fhmm;
import java.util.LinkedHashMap;
import org.tinos.obj.FDHMMNode;
import org.tinos.zabbi.DataString;
public interface NeroFeedHMM{
	@SuppressWarnings(DataString.RAW_TYPES)
	public String getBinaryForestRecurWord(String temp, String input, int i, int length, 
			LinkedHashMap<Integer, LinkedHashMap> roots, int depth);
	@SuppressWarnings(DataString.RAW_TYPES)
	public String getPrettyRecurWord(String temp, String input, int i, int length, 
			LinkedHashMap<Integer, LinkedHashMap> roots, int depth);
	public String getFastRecurWord(String temp, LinkedHashMap<String, FDHMMNode> maps,
			String input, int i, int length);
}
