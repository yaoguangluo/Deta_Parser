package org.tinos.engine.nero.imp;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
import org.tinos.engine.nero.NEROControllerOneTime;
public class NEROControllerOneTimeImp implements NEROControllerOneTime {
	public StringBuilder getBinaryForestRecurWordOneTime(StringBuilder outputWordNode, String inputString
			, int charPosition, int inputStringLength, Map<String, FMHMMNode> forestRoots, int forestDepth
			,int charPositionNext ) {
		if (forestDepth == StableData.INT_THREE){
			return outputWordNode;
		}
		FMHMMNode fFHMMNode = forestRoots.get(String.valueOf(inputString.charAt(charPosition)));
		if (fFHMMNode == null) {
			return outputWordNode;
		}
		Map<String, Integer> outputList = fFHMMNode.getNext();
		if (outputList == null || charPositionNext >= inputStringLength) {
			return outputWordNode;
		}
		char positionOfi = inputString.charAt(charPositionNext);
		if (outputList.containsKey(String.valueOf(positionOfi))) {
			outputWordNode = getBinaryForestRecurWordOneTime(outputWordNode.append(positionOfi), inputString, charPositionNext
					, inputStringLength, forestRoots, ++forestDepth , ++charPositionNext);
		}
		return outputWordNode;
	}
}