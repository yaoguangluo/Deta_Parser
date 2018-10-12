package org.tinos.engine.pos.imp;

import org.tinos.engine.pos.POSController;
import org.tinos.view.stable.StableData;

import java.util.List;
import java.util.Map;

public class POSControllerImp implements POSController {
    public int chuLiLianCi(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
            , String[] strings, String[] fixWord) {
        if (outputList.size() > StableData.INT_ZERO) {
            if (wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_FU_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_DONG_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_WEI_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_DAI_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_MING_CI)) {
                countInputStringLength = parserFirstChar(countInputStringLength, outputList, strings, fixWord
                        , wordsForest);
                return countInputStringLength;
            } else if (wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_ZHU_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_SHENG_LUE_CI)) {
                for (int BackPosition = StableData.INT_ZERO; BackPosition < fixWord[StableData.INT_ONE].length()
                        ; BackPosition++) {
                    String charPositionAtfixWord = StableData.EMPTY_STRING + fixWord[StableData.INT_ONE]
                            .charAt(BackPosition);
                    if (wordsForest.containsKey(charPositionAtfixWord) && (wordsForest.get(charPositionAtfixWord)
                            .contains(StableData.NLP_ZHU_CI) || wordsForest.get(charPositionAtfixWord)
                            .contains(StableData
                                    .NLP_SHENG_LUE_CI))) {
                        countInputStringLength = parserFirstChar(countInputStringLength, outputList, strings, fixWord
                                , wordsForest);
                        return countInputStringLength;
                    }
                }
                countInputStringLength -= StableData.INT_THREE;
                if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
                    outputList.add(strings[StableData.INT_ONE]);
                    fixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
                    countInputStringLength += StableData.INT_TWO;
                }
                return countInputStringLength;
            } else {
                countInputStringLength -= StableData.INT_THREE;
                if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
                    outputList.add(strings[StableData.INT_ONE]);
                    fixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
                    countInputStringLength += StableData.INT_TWO;
                }
                return countInputStringLength;
            }
        } else {
            didntFindFirstChar(countInputStringLength, outputList, strings, fixWord, wordsForest);
        }
        return countInputStringLength;
    }

    private void didntFindFirstChar(int countInputStringLength, List<String> outputList, String[] strings
            , String[] fixWord, Map<String, String> wordsForest) {
        if (wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_FU_CI)) {
            outputList.add(strings[StableData.INT_ZERO]);
            outputList.add(strings[StableData.INT_TWO]);
            fixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
        }
    }

    private int parserFirstChar(int countInputStringLength, List<String> outputList, String[] strings, String[] fixWord
            , Map<String, String> wordsForest) {
        countInputStringLength -= StableData.INT_THREE;
        outputList.add(strings[StableData.INT_ZERO]);
        fixWord[StableData.INT_ZERO] = strings[StableData.INT_ZERO];
        countInputStringLength += StableData.INT_ONE;
        if (wordsForest.containsKey(strings[StableData.INT_TWO])) {
            outputList.add(strings[StableData.INT_TWO]);
            fixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
            countInputStringLength += StableData.INT_TWO;
            return countInputStringLength;
        }
        return countInputStringLength;
    }

    public int chuLiZhuCi(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
                          String[] strings, String[] fixWord) {
        if (outputList.size() > StableData.INT_ZERO) {
            if (wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_DONG_CI)) {
                countInputStringLength = parserFirstChar(countInputStringLength, outputList, strings, fixWord
                        , wordsForest);
                return countInputStringLength;
            } else {
                countInputStringLength -= StableData.INT_THREE;
                if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
                    outputList.add(strings[StableData.INT_ONE]);
                    fixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
                    countInputStringLength += StableData.INT_TWO;
                }
                return countInputStringLength;
            }
        } else {
            didntFindFirstChar(countInputStringLength, outputList, strings, fixWord, wordsForest);
        }
        return countInputStringLength;
    }

    public int chuLiJieCi(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
            , String[] strings, String[] fixWord) {
        if (outputList.size() > StableData.INT_ZERO) {
            if (wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_LIAN_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_QING_TAI_CI)
                    || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_WEI_CI)) {
                countInputStringLength = parserFirstChar(countInputStringLength, outputList, strings, fixWord
                        , wordsForest);
                return countInputStringLength;
            } else {
                countInputStringLength -= StableData.INT_THREE;
                if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
                    outputList.add(strings[StableData.INT_ONE]);
                    fixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
                    countInputStringLength += StableData.INT_TWO;
                }
                return countInputStringLength;
            }
        } else {
            if (wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_WEI_CI)) {
                outputList.add(strings[StableData.INT_ZERO]);
                outputList.add(strings[StableData.INT_TWO]);
                fixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
                return countInputStringLength;
            }
        }
        return countInputStringLength;
    }

    public int chuLiLiangCi(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] fixWord) {
        if (wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_MING_CI)
                || wordsForest.get(fixWord[StableData.INT_ZERO]).contains(StableData.NLP_DAI_CI)) {
            countInputStringLength = parserFirstChar(countInputStringLength, outputList, strings, fixWord
                    , wordsForest);
            return countInputStringLength;
        } else {
            countInputStringLength -= StableData.INT_THREE;
            if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
                outputList.add(strings[StableData.INT_ONE]);
                fixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
                countInputStringLength += StableData.INT_TWO;
            }
            return countInputStringLength;
        }
    }
}