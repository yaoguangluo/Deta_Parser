package org.tinos.engine.nlp;

import org.tinos.engine.pos.POSController;

import java.util.List;
import java.util.Map;

public  interface NLPController {
     int doSlangCheck(int countInputStringLength, List<String> output, String inputString
            , Map<String, String> words, String[] prefixWord, POSController posUtils);

     int doPOSAndEMMCheckOfThree(int countInputStringLength, List<String> output, Map<String, String> wordsForest
            , String inputString, String[] prefixWord, POSController posUtils);

     int doSlangPartAndPOSCheckForTwoChar(int countInputStringLength, List<String> outputString, String countWordNode
             , Map<String, String> wordsForest, String[] prefixWord, POSController posUtils);
}
