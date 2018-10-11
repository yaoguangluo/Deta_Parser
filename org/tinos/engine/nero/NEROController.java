package org.tinos.engine.nero;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
public abstract interface NEROController{
	@SuppressWarnings(StableData.RAW_TYPES)
	public String getBinaryForestRecurWord(String inputStringWordNode, String inputString
			, int charPosition, int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth);
	@SuppressWarnings(StableData.RAW_TYPES)
	public String doBinaryForestRecurWordKerner(String inputString, FMHMMNode fFHMMNode, int length, 
			String input, int i, Map<Integer, Map> roots,int forestDepth);
}
