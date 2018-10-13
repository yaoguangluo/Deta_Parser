package org.tinos.engine.nlp;

import org.tinos.engine.pos.POSController;

import java.util.List;
import java.util.Map;

public interface NLPController {
    int doSlangCheck(int countInputStringLength, List<String> output, StringBuilder stringBuilder
            , Map<String, String> words, StringBuilder[] prefixWord, POSController posUtils);

    int doPOSAndEMMCheckOfThree(int countInputStringLength, List<String> output, Map<String, String> wordsForest
            , StringBuilder stringBuilder, StringBuilder[] prefixWord, POSController posUtils);

    int doSlangPartAndPOSCheckForTwoChar(int countInputStringLength, List<String> outputString
            , StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
            , POSController posUtils);
}
