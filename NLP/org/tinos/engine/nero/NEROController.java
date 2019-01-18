package org.tinos.engine.nero;
import java.util.Map;
import org.tinos.view.stable.StableData;
public interface NEROController {
	@SuppressWarnings(StableData.RAW_TYPES)
	StringBuilder getBinaryForestRecurWord(StringBuilder inputStringWordNode, String inputString, int charPosition
			, int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth, int charPositionNext);
}
