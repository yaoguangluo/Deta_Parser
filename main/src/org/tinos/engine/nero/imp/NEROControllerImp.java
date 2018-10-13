package org.tinos.engine.nero.imp;

import java.util.Map;

import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;
import org.tinos.engine.nero.NEROController;

public class NEROControllerImp implements NEROController {
    @SuppressWarnings({StableData.RAW_TYPES, StableData.UNCHECKED})
    public String getBinaryForestRecurWord(String outputWordNode, String inputString, int charPosition
            , int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth) {
        if (forestDepth == StableData.INT_THREE) {
            return outputWordNode;
        }
        String stringPosition = StableData.EMPTY_STRING + inputString.charAt(charPosition);
        int range = stringPosition.charAt(StableData.INT_ZERO) >> StableData.INT_SIX;
        int rangeHigh = range >> StableData.INT_FOUR;
        Map<Integer, Map> trees = forestRoots.get(rangeHigh);
        if (trees != null && trees.containsKey(range)) {
            Map<String, FMHMMNode> maps = trees.get(range);
            outputWordNode = doBinaryForestRecurWordKernel(outputWordNode, maps.get(stringPosition), inputStringLength
                    , inputString, charPosition + StableData.INT_ONE, forestRoots, forestDepth);
        }
        return outputWordNode;
    }

    @SuppressWarnings(StableData.RAW_TYPES)
    public String doBinaryForestRecurWordKernel(String output, FMHMMNode fFHMMNode, int length, String input
            , int i, Map<Integer, Map> roots, int forestDepth) {
        if (fFHMMNode != null && fFHMMNode.getNext() != null) {
            Map<String, Integer> outputList = fFHMMNode.getNext();
            if (i < length) {
                String charPostPosition = StableData.EMPTY_STRING + input.charAt(i);
                if (outputList.containsKey(charPostPosition)) {
                    output = getBinaryForestRecurWord(output + charPostPosition, input, i, length
                            , roots, forestDepth + StableData.INT_ONE);
                }
            }
        }
        return output;
    }
}