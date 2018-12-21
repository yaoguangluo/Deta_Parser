package org.tinos.engine.nero.imp;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
import org.tinos.engine.nero.NEROController;
public class NEROControllerImp implements NEROController {
	@SuppressWarnings({StableData.RAW_TYPES, StableData.UNCHECKED})
	public StringBuilder getBinaryForestRecurWord(StringBuilder outputWordNode, String inputString, int charPosition
			, int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth,int charPositionNext ) {
		if (forestDepth == StableData.INT_THREE) {
			return outputWordNode;
		}
		char charAtPosition = inputString.charAt(charPosition);
		int rangeHigh = charAtPosition >> StableData.INT_TEN;
		Map<Integer, Map> trees = forestRoots.get(rangeHigh);
		if (trees == null) {
			return outputWordNode;
		}
		int range = charAtPosition >> StableData.INT_SIX;
		if (!trees.containsKey(range)) {
			return outputWordNode;
		}
		Map<String, FMHMMNode> maps = trees.get(range);
		FMHMMNode fFHMMNode = maps.get(String.valueOf(charAtPosition));
		if (fFHMMNode == null) {
			return outputWordNode;
		}
		Map<String, Integer> outputList = fFHMMNode.getNext();
		if (outputList == null ||charPositionNext >= inputStringLength) {
			return outputWordNode;
		}
		char positionOfi = inputString.charAt(charPositionNext);
		if (outputList.containsKey(String.valueOf(positionOfi))) {
			outputWordNode = getBinaryForestRecurWord(outputWordNode.append(positionOfi), inputString
					, charPositionNext, inputStringLength, forestRoots
					, forestDepth + StableData.INT_ONE, ++charPositionNext);
		}
		return outputWordNode;
	}
}