package org.tinos.engine.analysis.imp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import java.io.IOException;

import org.tinos.ortho.fhmm.FHMMList;
import org.tinos.ortho.fhmm.imp.FMHMMListImp;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
import org.tinos.engine.nero.NEROController;
import org.tinos.engine.nero.imp.NEROControllerImp;
import org.tinos.engine.nlp.NLPController;
import org.tinos.engine.nlp.imp.NLPControllerImp;
import org.tinos.engine.pos.POSController;
import org.tinos.engine.pos.imp.POSControllerImp;
import org.tinos.engine.analysis.CogsBinaryForestAnalyzer;
import org.tinos.engine.linerScience.Quick6DLuoYaoguangSort;
import org.tinos.engine.linerScience.imp.Quick6DLuoYaoguangSortImp;

public class CogsBinaryForestAnalyzerImp implements CogsBinaryForestAnalyzer {
    private FHMMList fHMMList;
    private NEROController neroController;
    private NLPController nlpController;
    private POSController posController;
    private Quick6DLuoYaoguangSort quick6DLuoYaoguangSort;

    public void init() throws IOException {
        this.fHMMList = new FMHMMListImp();
        fHMMList.index();
        neroController = new NEROControllerImp();
        nlpController = new NLPControllerImp();
        posController = new POSControllerImp();
        quick6DLuoYaoguangSort = new Quick6DLuoYaoguangSortImp();
    }

    @SuppressWarnings(StableData.RAW_TYPES)
    public List<String> parserString(String inputString) {
        Map<String, String> wordsForest = fHMMList.getWords();
        List<String> outputList = new LinkedList<>();
        Map<Integer, Map> forestRoots = fHMMList.getRoot();
        int inputStringLength = inputString.length();
        int forestDepth = StableData.INT_ZERO;
        int countInputStringLength;
        StringBuilder[] fixWords = new StringBuilder[StableData.INT_TWO];
        fixWords[StableData.INT_ZERO] = new StringBuilder();
        fixWords[StableData.INT_ONE] = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        Here:
        for (int charPosition = StableData.INT_ZERO; charPosition < inputStringLength; charPosition
                += (countInputStringLength == StableData.INT_ZERO ? StableData.INT_ONE : countInputStringLength)) {
            stringBuilder.delete(StableData.INT_ZERO, stringBuilder.length());
            stringBuilder = neroController.getBinaryForestRecurWord(stringBuilder.append(inputString
                    .charAt(charPosition)), inputString, charPosition, inputStringLength, forestRoots, forestDepth);
            String countWordNode = stringBuilder.toString();
            int compare = countInputStringLength = countWordNode.length();
            if (compare == StableData.INT_THREE) {
                addFixWords(charPosition, inputString, fixWords);
                countInputStringLength = nlpController.doPOSAndEMMCheckOfThree(countInputStringLength, outputList
                        , wordsForest, stringBuilder, fixWords, posController);
                continue Here;
            }
            if (compare == StableData.INT_TWO) {
                countInputStringLength = nlpController.doSlangPartAndPOSCheckForTwoChar(countInputStringLength
                        , outputList, stringBuilder, wordsForest, fixWords, posController);
                continue Here;
            }
            if (compare == StableData.INT_FOUR) {
                addFixWords(charPosition, inputString, fixWords);
                countInputStringLength = nlpController.doSlangCheck(countInputStringLength, outputList, stringBuilder
                        , wordsForest, fixWords, posController);
                continue Here;
            }
            if (compare == StableData.INT_ONE) {
                outputList.add(countWordNode);
                fixWords[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWords[StableData.INT_ZERO].length());
                fixWords[StableData.INT_ZERO].append(countWordNode);
            }
        }
        return outputList;
    }

    private void addFixWords(int charPosition, String inputString, StringBuilder[] fixWords) {
        fixWords[StableData.INT_ONE].delete(StableData.INT_ZERO, fixWords[StableData.INT_ONE].length());
        if (charPosition + StableData.INT_EIGHT < inputString.length()) {
            fixWords[StableData.INT_ONE].append(inputString.substring(charPosition + StableData.INT_THREE
                    , charPosition + StableData.INT_EIGHT));
        } else {
            fixWords[StableData.INT_ONE].append(inputString.substring(charPosition + StableData.INT_THREE
                    , inputString.length()));
        }
    }

    public Map<String, String> getWord() {
        return fHMMList.getWords();
    }

    public List<WordFrequency> getWordFrequency(List<String> sets) throws IOException {
        Map<String, WordFrequency> map = new ConcurrentHashMap<>();
        for (int i = StableData.INT_ZERO; i < sets.size(); i++) {
            if (map.containsKey(sets.get(i))) {
                WordFrequency wordFrequency = map.get(sets.get(i));
                wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
                map.put(sets.get(i), wordFrequency);
            } else {
                WordFrequency wordFrequency = new WordFrequency();
                wordFrequency.setFrequency(StableData.INT_ONE);
                wordFrequency.setWord(sets.get(i));
                map.put(sets.get(i), wordFrequency);
            }
        }
        List<WordFrequency> list = quick6DLuoYaoguangSort.frequencyWordMapToList(map);
        quick6DLuoYaoguangSort.quick6DLuoYaoGuangSortWordFrequency(list, StableData.INT_ZERO
                , list.size() - StableData.INT_ONE);
        return list;
    }
}