package org.tinos.engine.nero.imp;
import java.util.Map;
import org.tinos.engine.nero.NEROController;
import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
public class NEROControllermp implements NEROController{	
	@SuppressWarnings({StableData.RAW_TYPES, StableData.UNCHECKED})
	public String getBinaryForestRecurWord(String outputWordNode, String inputString, int charPosition
			, int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth){
		if(forestDepth == StableData.INT_THREE){
			return outputWordNode;
		}
		String StringPosition = StableData.EMPTY_STRING + inputString.charAt(charPosition);	
		int range = ((int)(StringPosition.charAt(StableData.INT_ZERO)) >> StableData.INT_SIX);
		int rangeHigh = range >> StableData.INT_FOUR; 	
		if(forestRoots.containsKey(rangeHigh)){
			Map<Integer, Map> trees = forestRoots.get(rangeHigh);
			if(trees.containsKey(range)){
				Map<String, FMHMMNode> maps = trees.get(range);
				FMHMMNode fFHMMNode = maps.get(StringPosition);
				outputWordNode = doBinaryForestRecurWordKerner(outputWordNode, fFHMMNode
						, inputStringLength, inputString, charPosition, forestRoots, forestDepth);
			}
		}
		return outputWordNode;
	}

	@SuppressWarnings(StableData.RAW_TYPES)
	public String doBinaryForestRecurWordKerner(String output, FMHMMNode fFHMMNode, int length, String input
			, int i, Map<Integer, Map> roots, int forestDepth){
		if(fFHMMNode != null && fFHMMNode.getNext() != null){
			Map<String, Integer> outputList = fFHMMNode.getNext();
			if(i + StableData.INT_ONE < length){
				String charPostPosition = StableData.EMPTY_STRING + input.charAt(i + StableData.INT_ONE);
				if(outputList.containsKey(charPostPosition)){
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(output);
					stringBuilder.append(charPostPosition);
					output = stringBuilder.toString();
					output = getBinaryForestRecurWord(output, input, i + StableData.INT_ONE, length, roots
							, forestDepth + StableData.INT_ONE);
				}
			}
		}
		return output;
	}
}