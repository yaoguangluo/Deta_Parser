package org.tinos.engine.nero.imp;
import java.util.Map;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
import org.tinos.engine.nero.NEROControllerOneTime;
public class NEROControllerOneTimeImp implements NEROControllerOneTime {
	public StringBuilder getBinaryForestRecurWordOneTime(StringBuilder outputWordNode, String inputString
			, int charPosition, int inputStringLength, Map<Long, FMHMMNode> forestRoots, int forestDepth
			, int charPositionNext) {
		if (StableData.INT_THREE== forestDepth){
			return outputWordNode;
		}
		FMHMMNode fFHMMNode= forestRoots.get(Long.valueOf(inputString.charAt(charPosition)));
		if (null== fFHMMNode) {
			return outputWordNode;
		}
		Map<String, Integer> outputList= fFHMMNode.getNext();
		if (null== outputList || charPositionNext>= inputStringLength) {
			return outputWordNode;
		}
		char positionOfi= inputString.charAt(charPositionNext);
		if (outputList.containsKey(String.valueOf(positionOfi))) {
			outputWordNode = getBinaryForestRecurWordOneTime(outputWordNode.append(positionOfi), inputString, charPositionNext
					, inputStringLength, forestRoots, ++forestDepth, ++charPositionNext);
		}
		return outputWordNode;
	}
	//prepare for the big map collection in the future.
	public StringBuilder getBinaryForestsRecurWordOneTime(StringBuilder outputWordNode, String inputString
			, int charPosition, int inputStringLength, Map<Long, FMHMMNode>[] forestsRoots, int forestDepth
			,int charPositionNext) {
		if (StableData.INT_THREE== forestDepth){
			return outputWordNode;
		}
		FMHMMNode fFHMMNode= getFMHMMNode(forestsRoots,inputString,charPosition);
		if (null== fFHMMNode) {
			return outputWordNode;
		}
		Map<String, Integer> outputList= fFHMMNode.getNext();
		if (null== outputList|| charPositionNext>= inputStringLength) {
			return outputWordNode;
		}
		char positionOfi= inputString.charAt(charPositionNext);
		if (outputList.containsKey(String.valueOf(positionOfi))) {
			outputWordNode= getBinaryForestsRecurWordOneTime(outputWordNode.append(positionOfi), inputString, charPositionNext
					, inputStringLength, forestsRoots, ++forestDepth, ++charPositionNext);
		}
		return outputWordNode;
	}

	private FMHMMNode getFMHMMNode(Map<Long, FMHMMNode>[] forestsRoots, String inputString, int charPosition) {
		for(Map<Long, FMHMMNode> forestsRoot: forestsRoots) {
			if(forestsRoot.containsKey(Long.valueOf(inputString.charAt(charPosition)))){
				return forestsRoot.get(Long.valueOf(inputString.charAt(charPosition)));
			}
		}
		return null;
	}

	public StringBuilder getQuickForestRecurWord(StringBuilder outputWordNode, String inputString, int charPosition
			, int inputStringLength, Map<String, String> posCntoCn, int forestDepth, int charPositionNext ) {
		if (StableData.INT_THREE== forestDepth|| charPositionNext>= inputStringLength) {
			return outputWordNode;
		}
		char positionOfi= inputString.charAt(charPositionNext);
		if (posCntoCn.containsKey(String.valueOf(outputWordNode.toString()+ positionOfi))) {
			outputWordNode= getQuickForestRecurWord(outputWordNode.append(positionOfi), inputString
					, charPositionNext, inputStringLength, posCntoCn, ++forestDepth, ++charPositionNext);
		}
		return outputWordNode;
	}
}