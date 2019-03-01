package org.tinos.engine.nero;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
public interface NEROControllerOneTime {
	StringBuilder getBinaryForestsRecurWordOneTime(StringBuilder inputStringWordNode, String inputString, int charPosition
			, int inputStringLength, Map<String, FMHMMNode>[] forestsRoots, int forestDepth, int charPositionNext);
	StringBuilder getBinaryForestRecurWordOneTime(StringBuilder inputStringWordNode, String inputString, int charPosition
			, int inputStringLength, Map<String, FMHMMNode> forestRoots, int forestDepth, int charPositionNext);
}
