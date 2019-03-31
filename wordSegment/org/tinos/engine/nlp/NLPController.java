package org.tinos.engine.nlp;
import java.util.List;
import java.util.Map;
import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;

public interface NLPController {
	public int doSlangPartAndPOSCheckForTwoChar(int countInputStringLength, List<String> outputList
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils, Map<String, String> liangCiMap, Map<String, String> daiCiMap, Map<String, String> fuCiMap
			, Map<String, String> mingCiMap, Map<String, String> baDongCiMap);
	
	public int doPOSAndEMMCheckOfThree(int countInputLength, List<String> outputList
			, Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] prefixWord
			, POSController posUtils, Map<String, String> xingWeiCiMap, Map<String, String> mingCiMap
			, Map<String, String> daiCiMap, Map<String, String> dongCiMap, Map<String, String> fuCiMap
			, Map<String, String> qingTaiCiMap, Map<String, String> weiCiMap, Map<String, String> lianCiMap
			, Map<String, String> zhuCiMap, Map<String, String> shengLueCiMap, Map<String, String> liangCiMap
			, Map<String, String> baDongCiMap);

	public int doSlangCheck(int countInputStringLength, List<String> output, StringBuilder stringBuilder,
			Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils
			, Map<String, String> xingWeiCiMap, Map<String, String> mingCiMap, Map<String, String> daiCiMap
			, Map<String, String> dongCiMap, Map<String, String> fuCiMap, Map<String, String> qingTaiCiMap
			, Map<String, String> weiCiMap, Map<String, String> lianCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap, Map<String, String> liangCiMap, Map<String, String> baDongCiMap);

	public int doSlangCheckForMap(int countInputStringLength, List<String> output, StringBuilder stringBuilder
			, Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils, Map<String, String> xingWeiCiMap
			, Map<String, String> mingCiMap, Map<String, String> daiCiMap, Map<String, String> dongCiMap, Map<String, String> fuCiMap
			, Map<String, String> qingTaiCiMap, Map<String, String> weiCiMap, Map<String, String> lianCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap, Map<String, String> liangCiMap, Map<String, String> baDongCiMap);

	public int doSlangPartAndPOSCheckForTwoCharForMap(int countInputStringLength, Map<String, WordFrequency> outputList
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils, Map<String, String> liangCiMap, Map<String, String> mingCiMap);

	public int doPOSAndEMMCheckOfThreeForMap(int countInputLength, Map<String, WordFrequency> outputList
			, Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] prefixWord
			, POSController posUtils, Map<String, String> mingCiMap, Map<String, String> daiCiMap
			, Map<String, String> weiCiMap, Map<String, String> dongCiMap, Map<String, String> fuCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap, Map<String, String> lianCiMap, Map<String, String> qingTaiCiMap
			, Map<String, String> liangCiMap);

	public int doSlangCheckForMap(int countInputStringLength, Map<String, WordFrequency> output, StringBuilder stringBuilder
			, Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils, Map<String, String> mingCiMap
			, Map<String, String> daiCiMap, Map<String, String> weiCiMap, Map<String, String> dongCiMap, Map<String, String> fuCiMap
			, Map<String, String> zhuCiMap, Map<String, String> shengLueCiMap, Map<String, String> lianCiMap
			, Map<String, String> qingTaiCiMap, Map<String, String> liangCiMap);
}
