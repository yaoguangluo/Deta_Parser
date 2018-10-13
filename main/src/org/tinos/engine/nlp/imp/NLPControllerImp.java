package org.tinos.engine.nlp.imp;

import java.util.List;
import java.util.Map;

import org.tinos.engine.nlp.NLPController;
import org.tinos.engine.pos.POSController;
import org.tinos.view.stable.StableData;

public class NLPControllerImp implements NLPController {
    public int doSlangCheck(int countInputStringLength, List<String> output, String inputString
            , Map<String, String> wordsForest, String[] prefixWord, POSController posUtils) {
        if (wordsForest.containsKey(inputString)) {
            output.add(inputString);
            prefixWord[StableData.INT_ZERO] = inputString;
            return countInputStringLength;
        } else {
            countInputStringLength = doPOSAndEMMCheck(countInputStringLength
                    - StableData.INT_ONE, output, wordsForest, StableData.EMPTY_STRING + inputString
                    .charAt(StableData.INT_ZERO) + inputString.charAt(StableData.INT_ONE) + inputString
                    .charAt(StableData.INT_TWO), prefixWord, posUtils);
            return countInputStringLength;
        }
    }

    public int doPOSAndEMMCheck(int countInputStringLength, List<String> outputList, Map<String, String> wordsForest
            , String inputString, String[] prefixWord, POSController posUtils) {
        if (prefixWord[StableData.INT_ZERO] == null) {
            prefixWord[StableData.INT_ZERO] = inputString;
            return countInputStringLength;
        }
        String[] strings = new String[StableData.INT_FOUR];
        strings[StableData.INT_ZERO] = StableData.EMPTY_STRING + inputString.charAt(StableData.INT_ZERO);
        strings[StableData.INT_ONE] = StableData.EMPTY_STRING + inputString.charAt(StableData.INT_ZERO) + inputString
                .charAt(StableData.INT_ONE);
        strings[StableData.INT_TWO] = StableData.EMPTY_STRING + inputString.charAt(StableData.INT_ONE) + inputString
                .charAt(StableData.INT_TWO);
        strings[StableData.INT_THREE] = StableData.EMPTY_STRING + inputString.charAt(StableData.INT_TWO);
        if (!wordsForest.containsKey(strings[StableData.INT_ZERO])) {
            countInputStringLength = doSlangPartCheck(countInputStringLength - StableData
                    .INT_ONE, outputList, strings[StableData.INT_ONE], wordsForest, prefixWord);
            return countInputStringLength;
        }
        if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_LIANG_CI)) {
            countInputStringLength = posUtils.chuLiLiangCi(wordsForest, outputList, countInputStringLength, strings
                    , prefixWord);
            return countInputStringLength;
        }
        if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_JIE_CI)) {
            countInputStringLength = posUtils.chuLiJieCi(wordsForest, outputList, countInputStringLength, strings
                    , prefixWord);
            return countInputStringLength;
        }
        if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_LIAN_CI)) {
            countInputStringLength = posUtils.chuLiLianCi(wordsForest, outputList, countInputStringLength, strings
                    , prefixWord);
            return countInputStringLength;
        }
        if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_ZHU_CI)) {
            countInputStringLength = posUtils.chuLiZhuCi(wordsForest, outputList, countInputStringLength, strings
                    , prefixWord);
            return countInputStringLength;
        }
        return countInputStringLength;
    }

    public int doSlangPartCheck(int countInputStringLength, List<String> outputList, String countWordNode,
                                Map<String, String> wordsForest, String[] prefixWord) {
        if (wordsForest.containsKey(countWordNode)) {
            outputList.add(countWordNode);
            prefixWord[StableData.INT_ZERO] = countWordNode;
            return countInputStringLength;
        } else {
            outputList.add(StableData.EMPTY_STRING + countWordNode.charAt(StableData.INT_ZERO));
            prefixWord[StableData.INT_ZERO] = StableData.EMPTY_STRING + countWordNode.charAt(StableData.INT_ZERO);
            countInputStringLength--;
            return countInputStringLength;
        }
    }
}