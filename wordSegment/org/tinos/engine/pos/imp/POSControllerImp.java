package org.tinos.engine.pos.imp;

import java.util.List;
import java.util.Map;

import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;

public class POSControllerImp implements POSController{
	public int chuLiBaDongCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] prefixWord, Map<String, String> daiCiMap, Map<String, String> fuCiMap){
		if (wordsForest.containsKey(prefixWord[StableData.INT_ZERO].toString())){
			if (daiCiMap.containsKey(prefixWord[StableData.INT_ZERO].toString())
					||fuCiMap.containsKey(prefixWord[StableData.INT_ZERO].toString())) {
				countInputStringLength = parserFirstCharOfTwo(countInputStringLength, outputList, strings, prefixWord);
				return countInputStringLength;
			}
			countInputStringLength -= StableData.INT_TWO;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
				prefixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		return countInputStringLength;
	}
	
	public int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> liangCiMap){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (liangCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength = parserFirstCharOfTwo(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			countInputStringLength -= StableData.INT_TWO;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
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
			, StringBuilder[] fixWord){
		countInputStringLength -= StableData.INT_TWO;
		outputList.add(strings[StableData.INT_ZERO]);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength += StableData.INT_ONE;
		return countInputStringLength;
	}

	public int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> dongCiMap
			, Map<String, String> fuCiMap, Map<String, String> daiCiMap, Map<String, String> weiCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap){
		if (outputList.size() == StableData.INT_ZERO){
			didNotFindFirstChar(outputList, strings, fixWord, wordsForest, fuCiMap);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) && (mingCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
				|| dongCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())|| fuCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
				|| daiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString()) || weiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) && (zhuCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
				|| shengLueCiMap.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			for (int BackPosition = StableData.INT_ZERO; BackPosition < fixWord[StableData.INT_ONE].length(); BackPosition++){
				int[] nestCountInputStringLength = new int[StableData.INT_ONE];
				int result = loopCheckBackFix(fixWord, BackPosition, wordsForest, countInputStringLength, outputList, strings
						, nestCountInputStringLength, zhuCiMap, shengLueCiMap, fuCiMap);
				if (result == StableData.INT_RIGHT){
					return nestCountInputStringLength[StableData.INT_ZERO];
				}
			}
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
		countInputStringLength -= StableData.INT_THREE;
		if (wordsForest.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			countInputStringLength += StableData.INT_TWO;
		}
		return countInputStringLength;
	}

	public int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength
			, Map<String, String> zhuCiMap, Map<String, String> shengLueCiMap, Map<String, String> fuCiMap){
		String charPositionAtFixWord = StableData.EMPTY_STRING + fixWord[StableData.INT_ONE].charAt(backPosition);
		if (wordsForest.containsKey(charPositionAtFixWord) && (zhuCiMap.containsKey(charPositionAtFixWord) 
				|| shengLueCiMap.containsKey(charPositionAtFixWord)|| fuCiMap.containsKey(charPositionAtFixWord))){
			if(!wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_SHENG_LUE_CI)
					&& wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_FU_CI)){
				return StableData.INT_ERROR;
			}
			nestCountInputStringLength[StableData.INT_ZERO] = parserFirstCharOfThree(countInputStringLength, outputList
					, strings, fixWord, wordsForest);	
			return StableData.INT_RIGHT;
		}
		return StableData.INT_ERROR;
	}

	public void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest, Map<String, String> fuCiMap){
		if(!wordsForest.containsKey(strings[StableData.INT_TWO])){
			return;
		}
		if (fuCiMap.containsKey(strings[StableData.INT_TWO])){
			outputList.add(strings[StableData.INT_ZERO]);
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
		}
	}

	public int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest){
		countInputStringLength -= StableData.INT_THREE;
		outputList.add(strings[StableData.INT_ZERO]);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength ++;
		if (wordsForest.containsKey(strings[StableData.INT_TWO])){
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			countInputStringLength += StableData.INT_TWO;
			return countInputStringLength;
		}
		return countInputStringLength;
	}

	public int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> dongCiMap, Map<String, String> fuCiMap){
		if (outputList.size() == StableData.INT_ZERO){
			didNotFindFirstChar(outputList, strings, fixWord, wordsForest, fuCiMap);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (dongCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else{
				countInputStringLength -= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])){
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
			, String[] strings, StringBuilder[] fixWord, Map<String, String> qingTaiCiMap, Map<String, String> weiCiMap
			, Map<String, String> lianCiMap){
		if (outputList.size() == StableData.INT_ZERO && (wordsForest.get(strings[StableData.INT_TWO])
				.contains(StableData.NLP_WEI_CI))){
			outputList.add(strings[StableData.INT_ZERO]);
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		if (outputList.size() > StableData.INT_ZERO && wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (qingTaiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
					|| weiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
					|| lianCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else{
				countInputStringLength -= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])){
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
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> daiCiMap){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (mingCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())|| daiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} 
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
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
			, String[] strings, StringBuilder[] fixWord, Map<String, String> liangCiMap){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (liangCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength = parserFirstCharOfTwoForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			}
			countInputStringLength -= StableData.INT_TWO;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
				if (outputList.containsKey(strings[StableData.INT_ONE])){
					WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
					wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				} else{
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
			, StringBuilder[] fixWord, Map<String, String> wordsForest){
		countInputStringLength -= StableData.INT_TWO;
		if (outputList.containsKey(strings[StableData.INT_ZERO])){
			WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ZERO]);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		} else{
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
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> daiCiMap){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (mingCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())|| daiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} 
			countInputStringLength -= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
				if (outputList.containsKey(strings[StableData.INT_ONE])){
					WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
					wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				} else{
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
			, String[] strings, StringBuilder[] fixWord, Map<String, String> lianCiMap, Map<String, String> qingTaiCiMap
			, Map<String, String> weiCiMap){
		if (outputList.size()== StableData.INT_ZERO&& weiCiMap.containsKey(strings[StableData.INT_TWO])){
			if (outputList.containsKey(strings[StableData.INT_ZERO])){
				WordFrequency wordFrequency= outputList.get(strings[StableData.INT_ZERO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			} else{
				WordFrequency wordFrequency= new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_ZERO]);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			}
			if (outputList.containsKey(strings[StableData.INT_TWO])){
				WordFrequency wordFrequency= outputList.get(strings[StableData.INT_TWO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			} else{
				WordFrequency wordFrequency= new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_TWO]);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		if (outputList.size()> StableData.INT_ZERO&& wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (lianCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())|| qingTaiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
					|| weiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord, wordsForest);
				return countInputStringLength;
			} else{
				countInputStringLength-= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])){
					if (outputList.containsKey(strings[StableData.INT_ONE])){
						WordFrequency wordFrequency= outputList.get(strings[StableData.INT_ONE]);
						wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					} else{
						WordFrequency wordFrequency= new WordFrequency();
						wordFrequency.setFrequency(StableData.INT_ONE);
						wordFrequency.setWord(strings[StableData.INT_ONE]);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					}
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength+= StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public int chuLiLianCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> mingCiMap, Map<String, String> daiCiMap
			, Map<String, String> weiCiMap, Map<String, String> dongCiMap, Map<String, String> fuCiMap, Map<String, String> zhuCiMap
			, Map<String, String> shengLueCiMap){
		if (outputList.size()== StableData.INT_ZERO){
			didNotFindFirstCharForMap(outputList, strings, fixWord, wordsForest, fuCiMap);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (mingCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| daiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| weiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| dongCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| fuCiMap.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			countInputStringLength = parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (zhuCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| shengLueCiMap.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			for (int BackPosition= StableData.INT_ZERO; BackPosition< fixWord[StableData.INT_ONE].length(); BackPosition++){
				int[] nestCountInputStringLength = new int[StableData.INT_ONE];
				int result= loopCheckBackFixForMap(fixWord, BackPosition, wordsForest, countInputStringLength, outputList, strings
						, nestCountInputStringLength, shengLueCiMap, zhuCiMap);
				if (result== StableData.INT_RIGHT){
					return nestCountInputStringLength[StableData.INT_ZERO];
				}
			}
			countInputStringLength-= StableData.INT_THREE;
			if (wordsForest.containsKey(strings[StableData.INT_ONE])){
				if (outputList.containsKey(strings[StableData.INT_ONE])){
					WordFrequency wordFrequency= outputList.get(strings[StableData.INT_ONE]);
					wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
					outputList.put(strings[StableData.INT_ONE], wordFrequency);
				} else{
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
		countInputStringLength-= StableData.INT_THREE;
		if (wordsForest.containsKey(strings[StableData.INT_ONE])){
			if (outputList.containsKey(strings[StableData.INT_ONE])){
				WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ONE]);
				wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
				outputList.put(strings[StableData.INT_ONE], wordFrequency);
			} else{
				WordFrequency wordFrequency= new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_ONE]);
				outputList.put(strings[StableData.INT_ONE], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			countInputStringLength+= StableData.INT_TWO;
		}
		return countInputStringLength;
	}

	public int loopCheckBackFixForMap(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings, int[] nestCountInputStringLength
			, Map<String, String> zhuCiMap, Map<String, String> shengLueCiMap){
		String charPositionAtFixWord= StableData.EMPTY_STRING+ fixWord[StableData.INT_ONE].charAt(backPosition);
		if (wordsForest.containsKey(charPositionAtFixWord)&& (zhuCiMap.containsKey(charPositionAtFixWord) 
				|| wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_SHENG_LUE_CI))){
			nestCountInputStringLength[StableData.INT_ZERO]= parserFirstCharOfThreeForMap(countInputStringLength, outputList
					, strings, fixWord, wordsForest);
			return StableData.INT_RIGHT;
		}
		return StableData.INT_ERROR;
	}

	public int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, Map<String, String> dongCiMap, Map<String, String> fuCiMap){
		if (outputList.size()== StableData.INT_ZERO){
			didNotFindFirstCharForMap(outputList, strings, fixWord, wordsForest,fuCiMap);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (dongCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord
						, wordsForest);
				return countInputStringLength;
			} else{
				countInputStringLength-= StableData.INT_THREE;
				if (wordsForest.containsKey(strings[StableData.INT_ONE])){
					if (outputList.containsKey(strings[StableData.INT_ONE])){
						WordFrequency wordFrequency= outputList.get(strings[StableData.INT_ONE]);
						wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					} else{
						WordFrequency wordFrequency= new WordFrequency();
						wordFrequency.setFrequency(StableData.INT_ONE);
						wordFrequency.setWord(strings[StableData.INT_ONE]);
						outputList.put(strings[StableData.INT_ONE], wordFrequency);
					}
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength+= StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public void didNotFindFirstCharForMap(Map<String, WordFrequency> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest, Map<String, String> fuCiMap){
		if(!wordsForest.containsKey(strings[StableData.INT_TWO])){
			return;
		}
		if (fuCiMap.containsKey(strings[StableData.INT_TWO])){
			if (outputList.containsKey(strings[StableData.INT_ZERO])){
				WordFrequency wordFrequency= outputList.get(strings[StableData.INT_ZERO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			} else{
				WordFrequency wordFrequency= new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_ZERO]);
				outputList.put(strings[StableData.INT_ZERO], wordFrequency);
			}
			if (outputList.containsKey(strings[StableData.INT_TWO])){
				WordFrequency wordFrequency= outputList.get(strings[StableData.INT_TWO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			} else{
				WordFrequency wordFrequency= new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_TWO]);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
		}
	}

	public int parserFirstCharOfThreeForMap(int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings
			, StringBuilder[] fixWord, Map<String, String> wordsForest){
		countInputStringLength-= StableData.INT_THREE;
		if (outputList.containsKey(strings[StableData.INT_ZERO])){
			WordFrequency wordFrequency = outputList.get(strings[StableData.INT_ZERO]);
			wordFrequency.setFrequency(wordFrequency.getFrequency()+ StableData.INT_ONE);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		} else{
			WordFrequency wordFrequency= new WordFrequency();
			wordFrequency.setFrequency(StableData.INT_ONE);
			wordFrequency.setWord(strings[StableData.INT_ZERO]);
			outputList.put(strings[StableData.INT_ZERO], wordFrequency);
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		countInputStringLength ++;
		if (wordsForest.containsKey(strings[StableData.INT_TWO])){
			if (outputList.containsKey(strings[StableData.INT_TWO])){
				WordFrequency wordFrequency= outputList.get(strings[StableData.INT_TWO]);
				wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			} else{
				WordFrequency wordFrequency= new WordFrequency();
				wordFrequency.setFrequency(StableData.INT_ONE);
				wordFrequency.setWord(strings[StableData.INT_TWO]);
				outputList.put(strings[StableData.INT_TWO], wordFrequency);
			}
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			countInputStringLength+= StableData.INT_TWO;
			return countInputStringLength;
		}
		return countInputStringLength;
	}

	public int chuLiMingCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] fixWord, Map<String, String> xingWeiCiMap, Map<String, String> mingCiMap){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (xingWeiCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())|| mingCiMap.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord, wordsForest);
				return countInputStringLength;
			} 
		}
		if (wordsForest.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
			countInputStringLength= StableData.INT_TWO;
			return countInputStringLength;
		}
		countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord
				, wordsForest);
		return countInputStringLength;
	}
}