package org.tinos.engine.pos;
import java.util.List;
import java.util.Map;
import org.tinos.view.obj.WordFrequency;

public interface POSController {
	public int chuLiBaDongCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] prefixWord);

	public int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord);

	public int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength);

	public void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	public int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	public int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int chuLiMingCiOfTwoForMap(Map<String, String> wordsForest,  Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int parserFirstCharOfTwoForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	public int chuLiLiangCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int chuLiJieCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int chuLiLianCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public int loopCheckBackFixForMap(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings, int[] nestCountInputStringLength);

	public int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord);

	public void didNotFindFirstCharForMap(Map<String, WordFrequency> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest);

	public int parserFirstCharOfThreeForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	public int chuLiMingCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] fixWord);
}

