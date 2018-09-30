package org.tinos.fhmm;
import java.util.Map;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.zabbi.DataString;
public interface NeroFeedHMM{
	@SuppressWarnings(DataString.RAW_TYPES)
	public String getBinaryForestRecurWord(String temp, String input, int i, int length, 
			Map<Integer, Map> roots, int depth);
	@SuppressWarnings(DataString.RAW_TYPES)
	public String getPrettyRecurWord(String temp, String input, int i, int length, 
			Map<Integer, Map> roots, int depth);
	public String getFastRecurWord(String temp, Map<String, FDHMMNode> maps,
			String input, int i, int length);
	@SuppressWarnings(DataString.RAW_TYPES)
	public String doBinaryForestRecurWordKerner(String temp, FFHMMNode fFHMMNode, int length, String input, int i, Map<Integer, Map> roots,int depth) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public String doPrettyRecurWordKerner(String temp, FDHMMNode fDHMMNode, int length, String input, int i, Map<Integer, Map> roots, int depth) ;
}
