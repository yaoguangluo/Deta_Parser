package org.tinos.engine.pos;

import java.util.List;
import java.util.Map;

import org.tinos.view.obj.WordFrequency;

public interface POSController {
	int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, StringBuilder[] prefixWord);

	int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, StringBuilder[] prefixWord);

	int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, StringBuilder[] prefixWord);

	int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, StringBuilder[] prefixWord);

	void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength);

	int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] prefixWord);

	int chuLiMingCiOfTwoForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList,
			int countInputStringLength, String[] strings, StringBuilder[] prefixWord);

	int chuLiLiangCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList,
			int countInputLength, String[] strings, StringBuilder[] prefixWord);

	int chuLiJieCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord);

	int chuLiLianCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord);

	int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord);
}

