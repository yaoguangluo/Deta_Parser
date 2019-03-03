package org.tinos.engine.pos.imp;

import java.util.List;
import java.util.Map;

import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;

public class POSControllerImp implements POSController {
	public int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_LIANG_CI)) {
				countInputStringLength = parserFirstCharOfTwo(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			}
			countInputStringLength -= StableData.INT_TWO;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		return countInputStringLength;
	}

	public int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest) {
		countInputStringLength -= StableData.INT_TWO;
		outputList.add(strings[StableData.INT_ZERO]);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength += StableData.INT_ONE;
		return countInputStringLength;
	}

	public int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (outputList.size() == StableData.INT_ZERO) {
			didNotFindFirstChar(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_MING_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DAI_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_WEI_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DONG_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_FU_CI))) {
			countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_ZHU_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_SHENG_LUE_CI))) {
			for (int BackPosition = StableData.INT_ZERO; BackPosition < fixWord[StableData.INT_ONE].length(); BackPosition++) {
				int[] nestCountInputStringLength = new int[StableData.INT_ONE];
				int result = loopCheckBackFix(fixWord, BackPosition, wordsForest, countInputStringLength, outputList, strings
						, nestCountInputStringLength);
				if (result == StableData.INT_RIGHT) {
					return nestCountInputStringLength[StableData.INT_ZERO];
				}
			}
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		countInputStringLength -= StableData.INT_THREE;
		if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
			outputList.add(strings[StableData.INT_ONE]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			countInputStringLength += StableData.INT_TWO;
		}
		return countInputStringLength;
	}

	public int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength) {
		String charPositionAtFixWord = StableData.EMPTY_STRING + fixWord[StableData.INT_ONE].charAt(backPosition);
		if (wordsForest.containsKey(charPositionAtFixWord) && (wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_ZHU_CI) 
				|| wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_SHENG_LUE_CI))) {
			nestCountInputStringLength[StableData.INT_ZERO] = parserFirstCharOfThree(countInputStringLength, outputList
					, strings, fixWord, wordsForest);
			return StableData.INT_RIGHT;
		}
		return StableData.INT_ERROR;
	}

	public void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest) {
		if(!wordsForest.containsKey(strings[StableData.INT_TWO])) {
			return;
		}
		if (wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_FU_CI)) {
			outputList.add(strings[StableData.INT_ZERO]);
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
		}
	}

	public int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest) {
		countInputStringLength -= StableData.INT_THREE;
		outputList.add(strings[StableData.INT_ZERO]);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength ++;
		if (wordsForest.containsKey(strings[StableData.INT_TWO])) {
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			countInputStringLength += StableData.INT_TWO;
			return countInputStringLength;
		}
		return countInputStringLength;
	}

	public int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (outputList.size() == StableData.INT_ZERO) {
			didNotFindFirstChar(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DONG_CI)) {
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else {
				countInputStringLength -= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
					outputList.add(strings[StableData.INT_ONE]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (outputList.size() == StableData.INT_ZERO && (wordsForest.get(strings[StableData.INT_TWO])
				.contains(StableData.NLP_WEI_CI))) {
			outputList.add(strings[StableData.INT_ZERO]);
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		if (outputList.size() > StableData.INT_ZERO && wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_LIAN_CI)
					|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_QING_TAI_CI)
					|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_WEI_CI)) {
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else {
				countInputStringLength -= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
					outputList.add(strings[StableData.INT_ONE]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_MING_CI) || wordsForest
					.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DAI_CI)) {
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} 
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);	
		outputList.add(fixWord[StableData.INT_ZERO].toString());
		return countInputStringLength;
	}

	public int chuLiMingCiOfTwoForMap(Map<String, String> wordsForest,  Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_LIANG_CI)) {
				countInputStringLength = parserFirstCharOfTwoForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			}
			countInputStringLength -= StableData.INT_TWO;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
				if (outputList.containsKey(strings[StableData.INT_ONE])) {
					WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
					wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				} else {
					WordFrequency wordFrequency = new WordFrequency();
					wordFrequency.setFrequency(StableData.INT_ONE);
					wordFrequency.setWord(strings[StableData.INT_ONE]);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				}
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		return countInputStringLength;
	}

	public int parserFirstCharOfTwoForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest) {
		countInputStringLength -= StableData.INT_TWO;
		if (outputList.containsKey(strings[StableData.INT_ZERO])) {
			WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ZERO]);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		} else {
			WordFrequency wordFrequency = new WordFrequency();
			wordFrequency.setFrequency(StableData.INT_ONE);
			wordFrequency.setWord(strings[StableData.INT_ZERO]);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength += StableData.INT_ONE;
		return countInputStringLength;
	}

	public int chuLiLiangCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_MING_CI) || wordsForest
					.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DAI_CI)) {
				countInputStringLength = parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} 
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
				if (outputList.containsKey(strings[StableData.INT_ONE])) {
					WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
					wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				} else {
					WordFrequency wordFrequency = new WordFrequency();
					wordFrequency.setFrequency(StableData.INT_ONE);
					wordFrequency.setWord(strings[StableData.INT_ONE]);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				}
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		return countInputStringLength;
	}

	public int chuLiJieCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (outputList.size() == StableData.INT_ZERO && (wordsForest.get(strings[StableData.INT_TWO])
				.contains(StableData.NLP_WEI_CI))) {
			if (outputList.containsKey(strings[StableData.INT_ZERO])) {
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ZERO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			} else {
				WordFrequency wordFrequency = new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_ZERO]);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			}
			if (outputList.containsKey(strings[StableData.INT_TWO])) {
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_TWO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			} else {
				WordFrequency wordFrequency = new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_TWO]);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		if (outputList.size() > StableData.INT_ZERO && wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_LIAN_CI)
					|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_QING_TAI_CI)
					|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_WEI_CI)) {
				countInputStringLength = parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else {
				countInputStringLength -= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
					if (outputList.containsKey(strings[StableData.INT_ONE])) {
						WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
						wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					} else {
						WordFrequency wordFrequency = new WordFrequency();
						wordFrequency.setFrequency(StableData.INT_ONE);
						wordFrequency.setWord(strings[StableData.INT_ONE]);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					}
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public int chuLiLianCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (outputList.size() == StableData.INT_ZERO) {
			didNotFindFirstCharForMap(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_MING_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DAI_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_WEI_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DONG_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_FU_CI))) {
			countInputStringLength = parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_ZHU_CI) 
						|| wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_SHENG_LUE_CI))) {
			for (int BackPosition = StableData.INT_ZERO; BackPosition < fixWord[StableData.INT_ONE].length(); BackPosition++) {
				int[] nestCountInputStringLength = new int[StableData.INT_ONE];
				int result = loopCheckBackFixForMap(fixWord, BackPosition, wordsForest, countInputStringLength, outputList, strings
						, nestCountInputStringLength);
				if (result == StableData.INT_RIGHT) {
					return nestCountInputStringLength[StableData.INT_ZERO];
				}
			}
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
				if (outputList.containsKey(strings[StableData.INT_ONE])) {
					WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
					wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				} else {
					WordFrequency wordFrequency = new WordFrequency();
					wordFrequency.setFrequency(StableData.INT_ONE);
					wordFrequency.setWord(strings[StableData.INT_ONE]);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				}
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		countInputStringLength -= StableData.INT_THREE;
		if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
			if (outputList.containsKey(strings[StableData.INT_ONE])) {
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_ONE], wordFrequency);
			} else {
				WordFrequency wordFrequency = new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_ONE]);
				outputList.put(strings[StableData.INT_ONE], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			countInputStringLength += StableData.INT_TWO;
		}
		return countInputStringLength;
	}

	public int loopCheckBackFixForMap(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings, int[] nestCountInputStringLength) {
		String charPositionAtFixWord = StableData.EMPTY_STRING + fixWord[StableData.INT_ONE].charAt(backPosition);
		if (wordsForest.containsKey(charPositionAtFixWord) && (wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_ZHU_CI) 
				|| wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_SHENG_LUE_CI))) {
			nestCountInputStringLength[StableData.INT_ZERO] = parserFirstCharOfThreeForMap(countInputStringLength, outputList
					, strings, fixWord, wordsForest);
			return StableData.INT_RIGHT;
		}
		return StableData.INT_ERROR;
	}

	public int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord) {
		if (outputList.size() == StableData.INT_ZERO) {
			didNotFindFirstCharForMap(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())) {
			if (wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_DONG_CI)) {
				countInputStringLength = parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else {
				countInputStringLength -= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])) {
					if (outputList.containsKey(strings[StableData.INT_ONE])) {
						WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
						wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					} else {
						WordFrequency wordFrequency = new WordFrequency();
						wordFrequency.setFrequency(StableData.INT_ONE);
						wordFrequency.setWord(strings[StableData.INT_ONE]);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					}
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public void didNotFindFirstCharForMap(Map<String, WordFrequency> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest) {
		if(!wordsForest.containsKey(strings[StableData.INT_TWO])) {
			return;
		}
		if (wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_FU_CI)) {
			if (outputList.containsKey(strings[StableData.INT_ZERO])) {
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ZERO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			} else {
				WordFrequency wordFrequency = new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_ZERO]);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			}
			if (outputList.containsKey(strings[StableData.INT_TWO])) {
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_TWO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			} else {
				WordFrequency wordFrequency = new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_TWO]);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
		}
	}

	public int parserFirstCharOfThreeForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest) {
		countInputStringLength -= StableData.INT_THREE;
		if (outputList.containsKey(strings[StableData.INT_ZERO])) {
			WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ZERO]);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		} else {
			WordFrequency wordFrequency = new WordFrequency();
			wordFrequency.setFrequency(StableData.INT_ONE);
			wordFrequency.setWord(strings[StableData.INT_ZERO]);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength ++;
		if (wordsForest.containsKey(strings[StableData.INT_TWO])) {
			if (outputList.containsKey(strings[StableData.INT_TWO])) {
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_TWO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			} else {
				WordFrequency wordFrequency = new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_TWO]);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			countInputStringLength += StableData.INT_TWO;
			return countInputStringLength;
		}
		return countInputStringLength;
	}
}