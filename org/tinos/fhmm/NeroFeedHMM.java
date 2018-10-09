package org.tinos.fhmm;
import java.util.Map;
import org.tinos.obj.FLHMMNode;
import org.tinos.obj.FHHMMNode;
import org.tinos.zabbi.DataString;
public interface NeroFeedHMM{
	@SuppressWarnings(DataString.RAW_TYPES)
	public String getBinaryForestRecurWord(String inputStringWordNode, String inputString, int charPosition, int inputStringLength, 
			Map<Integer, Map> forestRoots, int forestDepth);
	@SuppressWarnings(DataString.RAW_TYPES)
	public String getPrettyRecurWord(String inputString, String input, int i, int length, 
			Map<Integer, Map> roots, int forestDepth);
	public String getFastRecurWord(String inputString, Map<String, FLHMMNode> maps,
			String input, int i, int length);
	@SuppressWarnings(DataString.RAW_TYPES)
	public String doBinaryForestRecurWordKerner(String inputString, FHHMMNode fFHMMNode, int length, 
			String input, int i, Map<Integer, Map> roots,int forestDepth) ;
	@SuppressWarnings(DataString.RAW_TYPES)
	public String doPrettyRecurWordKerner(String inputString, FLHMMNode fDHMMNode, int length, 
			String input, int i, Map<Integer, Map> roots, int forestDepth) ;
}
