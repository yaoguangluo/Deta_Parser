package org.tinos.engine.nlp;
import java.util.List;
import java.util.Map;
import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;
public interface NLPController {
	int doSlangCheck(int countInputStringLength, List<String> output, StringBuilder stringBuilder
			, Map<String, String> words, StringBuilder[] prefixWord, POSController posUtils);
	int doPOSAndEMMCheckOfThree(int countInputStringLength, List<String> output, Map<String, String> wordsForest
			, StringBuilder stringBuilder, StringBuilder[] prefixWord, POSController posUtils);
	int doSlangPartAndPOSCheckForTwoChar(int countInputStringLength, List<String> outputString
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils);
	int doSlangPartAndPOSCheckForTwoCharForMap(int countInputStringLength, Map<String, WordFrequency> outputList,
			StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] fixWords,
			POSController posController);
	int doPOSAndEMMCheckOfThreeForMap(int countInputStringLength, Map<String, WordFrequency> outputList,
			Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] fixWords,
			POSController posController);
	int doSlangCheckForMap(int countInputStringLength, Map<String, WordFrequency> outputList, StringBuilder stringBuilder,
			Map<String, String> wordsForest, StringBuilder[] fixWords, POSController posController);
}
