package org.tinos.engine.nlp.imp;
import java.util.List;
import java.util.Map;
import org.tinos.engin.utils.WordForestUtil;
import org.tinos.engine.nlp.NLPController;
import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
public class NLPControllerImp implements NLPController {
	public int doSlangPartAndPOSCheckForTwoChar(int countInputStringLength, List<String> outputList
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils) {
		String countWordNode = stringBuilder.toString();
		if (!wordsForest.containsKey(countWordNode)) {
			outputList.add(String.valueOf(countWordNode.charAt(StableData.INT_ZERO)));
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(countWordNode.charAt(StableData.INT_ZERO));
			return --countInputStringLength;
		}
		if (prefixWord[StableData.INT_ZERO].length() == StableData.INT_ZERO) {
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(countWordNode);
			outputList.add(countWordNode);
			return countInputStringLength;
		}
		String[] strings = new String[StableData.INT_TWO];
		strings[StableData.INT_ZERO] = String.valueOf(countWordNode.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE] = String.valueOf(countWordNode.charAt(StableData.INT_ZERO))
				+ String.valueOf(countWordNode.charAt(StableData.INT_ONE));
		if (wordsForest.containsKey(strings[StableData.INT_ZERO])) {
			if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_MING_CI)) {
				countInputStringLength = posUtils.chuLiMingCiOfTwo(wordsForest, outputList, countInputStringLength
						, strings, prefixWord);
				return countInputStringLength;
			}
		}
		if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(countWordNode);
			outputList.add(countWordNode);
			return countInputStringLength;
		}
		return StableData.INT_ZERO;
	}

	public int doPOSAndEMMCheckOfThree(int countInputLength, List<String> outputList
			, Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] prefixWord
			, POSController posUtils) {
		String inputString = stringBuilder.toString();
		String[] strings = new String[StableData.INT_FOUR];
		strings[StableData.INT_ZERO] = String.valueOf(inputString.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE] = String.valueOf(inputString.charAt(StableData.INT_ZERO)) 
				+ inputString.charAt(StableData.INT_ONE);
		strings[StableData.INT_TWO] = String.valueOf(inputString.charAt(StableData.INT_ONE)) 
				+ inputString.charAt(StableData.INT_TWO);
		strings[StableData.INT_THREE] = String.valueOf(inputString.charAt(StableData.INT_TWO));
		if (prefixWord[StableData.INT_ZERO] == null) {
			if (wordsForest.containsKey(inputString)) {
				prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
				prefixWord[StableData.INT_ZERO].append(inputString);
				outputList.add(inputString);
				return countInputLength;
			} 
			StringBuilder stringsBuilder = new StringBuilder();
			countInputLength = doSlangPartAndPOSCheckForTwoChar(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
			return countInputLength;
		}
		if (!wordsForest.containsKey(strings[StableData.INT_ZERO])) {
			StringBuilder stringsBuilder = new StringBuilder();
			countInputLength = doSlangPartAndPOSCheckForTwoChar(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_LIANG_CI)) {
			countInputLength = posUtils.chuLiLiangCiOfThree(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_JIE_CI)) {
			countInputLength = posUtils.chuLiJieCiOfThree(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_LIAN_CI)) {
			countInputLength = posUtils.chuLiLianCiOfThree(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_ZHU_CI)) {
			countInputLength = posUtils.chuLiZhuCiOfThree(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.containsKey(inputString)) {
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(inputString);
			outputList.add(inputString);
			return countInputLength;
		}
		StringBuilder stringsBuilder = new StringBuilder();
		countInputLength = doSlangPartAndPOSCheckForTwoChar(--countInputLength, outputList
				, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
		return countInputLength;
	} 

	public int doSlangCheckForMap(int countInputStringLength, List<String> output, StringBuilder stringBuilder
			, Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils) {
		String inputString = stringBuilder.toString();
		if (wordsForest.containsKey(inputString)) {
			output.add(inputString);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(inputString);
			return countInputStringLength;
		} else {
			countInputStringLength = doPOSAndEMMCheckOfThree(--countInputStringLength, output, wordsForest
					, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils);
			return countInputStringLength;
		}
	}

	public int doSlangPartAndPOSCheckForTwoCharForMap(int countInputStringLength, Map<String, WordFrequency> outputList
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils) {
		String countWordNode = stringBuilder.toString();
		if (!wordsForest.containsKey(countWordNode)) {
			WordForestUtil.wordsForestNotContainsKey(outputList, countWordNode, prefixWord);
			return --countInputStringLength;
		}
		if (prefixWord[StableData.INT_ZERO].length() == StableData.INT_ZERO) {
			WordForestUtil.prefixWordEqualZero(outputList, countWordNode, prefixWord);
			return countInputStringLength;
		}
		String[] strings = new String[StableData.INT_TWO];
		strings[StableData.INT_ZERO] = String.valueOf(countWordNode.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE] = String.valueOf(countWordNode.charAt(StableData.INT_ZERO))
				+ String.valueOf(countWordNode.charAt(StableData.INT_ONE));
		if (wordsForest.containsKey(strings[StableData.INT_ZERO])) {
			if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_MING_CI)) {
				countInputStringLength = posUtils.chuLiMingCiOfTwoForMap(wordsForest, outputList, countInputStringLength
						, strings, prefixWord);
				return countInputStringLength;
			}
		}
		if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
			WordForestUtil.wordsForestContainsKey(outputList, countWordNode, prefixWord);
			return countInputStringLength;
		}
		return StableData.INT_ZERO;
	}

	public int doPOSAndEMMCheckOfThreeForMap(int countInputLength, Map<String, WordFrequency> outputList
			, Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] prefixWord
			, POSController posUtils) {
		String inputString = stringBuilder.toString();
		String[] strings = new String[StableData.INT_FOUR];
		strings[StableData.INT_ZERO] = String.valueOf(inputString.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE] = String.valueOf(inputString.charAt(StableData.INT_ZERO)) 
				+ inputString.charAt(StableData.INT_ONE);
		strings[StableData.INT_TWO] = String.valueOf(inputString.charAt(StableData.INT_ONE) 
				+ inputString.charAt(StableData.INT_TWO));
		strings[StableData.INT_THREE] = String.valueOf(inputString.charAt(StableData.INT_TWO));
		if (prefixWord[StableData.INT_ZERO] == null) {
			if (wordsForest.containsKey(inputString)) {
				WordForestUtil.wordsForestContainsKey(outputList, inputString, prefixWord);
				return countInputLength;
			} 
			StringBuilder stringsBuilder = new StringBuilder();
			countInputLength = doSlangPartAndPOSCheckForTwoCharForMap(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
			return countInputLength;
		}
		if (!wordsForest.containsKey(strings[StableData.INT_ZERO])) {
			StringBuilder stringsBuilder = new StringBuilder();
			countInputLength = doSlangPartAndPOSCheckForTwoCharForMap(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_LIANG_CI)) {
			countInputLength = posUtils.chuLiLiangCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_JIE_CI)) {
			countInputLength = posUtils.chuLiJieCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_LIAN_CI)) {
			countInputLength = posUtils.chuLiLianCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_ZHU_CI)) {
			countInputLength = posUtils.chuLiZhuCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (wordsForest.containsKey(inputString)) {
			WordForestUtil.wordsForestContainsKey(outputList, inputString, prefixWord);
			return countInputLength;
		}
		StringBuilder stringsBuilder = new StringBuilder();
		countInputLength = doSlangPartAndPOSCheckForTwoCharForMap(--countInputLength, outputList
				, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
		return countInputLength;
	} 

	public int doSlangCheckForMap(int countInputStringLength, Map<String, WordFrequency> output, StringBuilder stringBuilder
			, Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils) {
		String inputString = stringBuilder.toString();
		if (wordsForest.containsKey(inputString)) {
			WordForestUtil.wordsForestContainsKey(output, inputString, prefixWord);
			return countInputStringLength;
		} else {
			countInputStringLength = doPOSAndEMMCheckOfThreeForMap(--countInputStringLength, output, wordsForest
					, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils);
			return countInputStringLength;
		}
	}

	public int doSlangCheck(int countInputStringLength, List<String> output, StringBuilder stringBuilder,
			Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils) {
		String inputString = stringBuilder.toString();
		if (wordsForest.containsKey(inputString)) {
			output.add(inputString);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(inputString);
			return countInputStringLength;
		} else {
			countInputStringLength = doPOSAndEMMCheckOfThree(--countInputStringLength, output, wordsForest
					, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils);
			return countInputStringLength;
		}
	}
}