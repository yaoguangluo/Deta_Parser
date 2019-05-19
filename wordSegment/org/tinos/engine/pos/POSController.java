package org.tinos.engine.pos;
import java.util.List;
import java.util.Map;
import org.tinos.view.obj.WordFrequency;

public interface POSController {
	int chuLiBaDongCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] prefixWord);
	int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, int charPosition, String inputString);

	void addFixWordsOfTwo(int charPosition, String inputString, StringBuilder[] fixWords);
	int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord);

	int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);
	int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength);
	void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord);

	int parserFirstTwoCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord);

	int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);
	int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);
	int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	int chuLiMingCiOfTwoForMap(Map<String, String> wordsForest,  Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	int parserFirstCharOfTwoForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	int chuLiLiangCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	int chuLiJieCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	int chuLiLianCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	int loopCheckBackFixForMap(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings, int[] nestCountInputStringLength);

	int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	void didNotFindFirstCharForMap(Map<String, WordFrequency> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	int parserFirstCharOfThreeForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	int chuLiMingCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] fixWord);

	int chuLiShiTaiCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord);
	int chuLiFuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord);
	int chuLiLianCiPostFixOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord);
}

