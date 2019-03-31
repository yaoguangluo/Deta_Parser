package org.tinos.engine.pos;
import java.util.List;
import java.util.Map;
import org.tinos.view.obj.WordFrequency;

public interface POSController {
	public int chuLiBaDongCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] prefixWord, Map<String, String> daiCiMap, Map<String, String> fuCiMap);

	public int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> liangCiMap);

	public int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord);

	public int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> dongCiMap
			, Map<String, String> fuCiMap, Map<String, String> daiCiMap, Map<String, String> weiCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap);

	public int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength
			, Map<String, String> zhuCiMap, Map<String, String> shenglueCiMap, Map<String, String> fuCiMap);

	public void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest, Map<String, String> fuCiMap);

	public int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	public int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> dongCiMap, Map<String, String> fuCiMap);

	public int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> qingTaiCiMap, Map<String, String> weiCiMap
			, Map<String, String> lianCiMap);

	public int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> daiCiMap);

	public int chuLiMingCiOfTwoForMap(Map<String, String> wordsForest,  Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> liangCiMap);

	public int parserFirstCharOfTwoForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	public int chuLiLiangCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> daiCiMap);

	public int chuLiJieCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> lianCiMap, Map<String, String> qingTaiCiMap
			, Map<String, String> weiCiMap);

	public int chuLiLianCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> daiCiMap
			, Map<String, String> weiCiMap, Map<String, String> dongCiMap, Map<String, String> fuCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap);

	public int loopCheckBackFixForMap(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings, int[] nestCountInputStringLength
			, Map<String, String> zhuCiMap, Map<String, String> shengLueCiMap);

	public int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> dongCiMap, Map<String, String> fuCiMap);

	public void didNotFindFirstCharForMap(Map<String, WordFrequency> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest, Map<String, String> fuCiMap);

	public int parserFirstCharOfThreeForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest);

	public int chuLiMingCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] fixWord, Map<String, String> xingWeiCiMap, Map<String, String> mingCiMap);
}

