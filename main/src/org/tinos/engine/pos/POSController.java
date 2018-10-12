package org.tinos.engine.pos;

import java.util.List;
import java.util.Map;

public interface POSController {
    int chuLiLianCi(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    int chuLiZhuCi(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    int chuLiJieCi(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);

    int chuLiLiangCi(Map<String, String> wordsForest, List<String> outputList
            , int countInputStringLength, String[] strings, String[] prefixWord);
}
