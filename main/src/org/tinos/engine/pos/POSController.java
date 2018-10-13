package org.tinos.engine.pos;

import java.util.List;
import java.util.Map;
public interface POSController {
    int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    void didNotFindFirstChar(List<String> outputList, String[] strings, String[] fixWord
            , Map<String, String> wordsForest);

    int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings, String[] fixWord
            , Map<String, String> wordsForest);

    int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings, String[] fixWord
            , Map<String, String> wordsForest);

    int loopCheckBackFix(String[] fixWord, int backPosition, Map<String, String> wordsForest
            , int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength);

    int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
            , String[] strings, String[] prefixWord);
}

