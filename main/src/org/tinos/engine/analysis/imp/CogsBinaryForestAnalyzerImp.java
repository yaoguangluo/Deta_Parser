package org.tinos.engine.analysis.imp;

import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.io.IOException;

import org.tinos.engine.analysis.CogsBinaryForestAnalyzer;
import org.tinos.engine.nero.NEROController;
import org.tinos.engine.nero.imp.NEROControllermp;
import org.tinos.engine.nlp.NLPController;
import org.tinos.engine.nlp.imp.NLPControllerImp;
import org.tinos.engine.pos.POSController;
import org.tinos.engine.pos.imp.POSControllerImp;
import org.tinos.ortho.fhmm.FHMMList;
import org.tinos.ortho.fhmm.imp.FMHMMListImp;
import org.tinos.view.stable.StableData;

public class CogsBinaryForestAnalyzerImp implements CogsBinaryForestAnalyzer {
    private FHMMList fHMMList;
    private NEROController neroUtils;
    private NLPController nlpUtils;
    private POSController posUtils;

    public void init() throws IOException {
        this.fHMMList = new FMHMMListImp();
        fHMMList.index();
        neroUtils = new NEROControllermp();
        nlpUtils = new NLPControllerImp();
        posUtils = new POSControllerImp();
    }

    @SuppressWarnings(StableData.RAW_TYPES)
    public List<String> parserString(String inputString) {
        Map<String, String> wordsForest = fHMMList.getWords();
        List<String> outputList = new LinkedList<>();
        Map<Integer, Map> forestRoots = fHMMList.getRoot();
        int inputStringLength = inputString.length();
        int forestDepth = StableData.INT_ZERO;
        int countInputStringLength;
        String[] fixWords = new String[StableData.INT_TWO];
        for (int charPosition = StableData.INT_ZERO; charPosition < inputStringLength; charPosition
                += (countInputStringLength == StableData.INT_ZERO ? StableData.INT_ONE : countInputStringLength)) {
            String countWordNode = StableData.EMPTY_STRING + inputString.charAt(charPosition);
            countWordNode = neroUtils.getBinaryForestRecurWord(countWordNode, inputString, charPosition
                    , inputStringLength, forestRoots, forestDepth);
            countInputStringLength = countWordNode.length();
            if (countWordNode.length() == StableData.INT_ONE) {
                outputList.add(countWordNode);
                fixWords[StableData.INT_ZERO] = countWordNode;
            } else if (countWordNode.length() == StableData.INT_TWO) {
                countInputStringLength = nlpUtils.doSlangPartCheck(countInputStringLength, outputList, countWordNode
                        , wordsForest, fixWords);
            } else if (countWordNode.length() == StableData.INT_THREE) {
                if (charPosition + StableData.INT_EIGHT < inputString.length()) {
                    fixWords[StableData.INT_ONE] = inputString.substring(charPosition + StableData.INT_THREE
                            , charPosition + StableData.INT_EIGHT);
                } else {
                    fixWords[StableData.INT_ONE] = inputString.substring(charPosition + StableData.INT_THREE
                            , inputString.length());
                }
                countInputStringLength = nlpUtils.doPOSAndEMMCheck(countInputStringLength, outputList, wordsForest
                        , countWordNode, fixWords, posUtils);
            } else if (countWordNode.length() == StableData.INT_FOUR) {
                if (charPosition + StableData.INT_EIGHT < inputString.length()) {
                    fixWords[StableData.INT_ONE] = inputString.substring(charPosition + StableData.INT_THREE
                            , charPosition + StableData.INT_EIGHT);
                } else {
                    fixWords[StableData.INT_ONE] = inputString.substring(charPosition + StableData.INT_THREE
                            , inputString.length());
                }
                countInputStringLength = nlpUtils.doSlangCheck(countInputStringLength, outputList, countWordNode
                        , wordsForest, fixWords, posUtils);
            }
        }
        return outputList;
    }

    public Map<String, String> getWord() throws IOException {
        return fHMMList.getWords();
    }
}

 
