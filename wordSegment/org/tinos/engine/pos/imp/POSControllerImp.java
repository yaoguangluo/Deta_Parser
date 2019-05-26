package org.tinos.engine.pos.imp;

import java.util.List;
import java.util.Map;
import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
import org.tinos.view.stable.StableMaps;

public class POSControllerImp implements POSController{
	public int chuLiBaDongCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, StringBuilder[] prefixWord){
		if (!wordsForest.containsKey(prefixWord[StableData.INT_ZERO].toString())){
			return countInputStringLength;
		}
		if (StableMaps.daiCi.containsKey(prefixWord[StableData.INT_ZERO].toString())
				||StableMaps.fuCi.containsKey(prefixWord[StableData.INT_ZERO].toString())) {
			countInputStringLength = parserFirstCharOfTwo(countInputStringLength, outputList, strings, prefixWord);
			return countInputStringLength;
		}
		if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
			return countInputStringLength;
		}
		return countInputStringLength- StableData.INT_TWO;
	}

	public int chuLiMingCiOfTwo(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord, int charPosition, String inputString){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.liangCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstCharOfTwo(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if (StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					||StableMaps.xingRongCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					||StableMaps.mingCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					||StableMaps.zhuCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					||StableMaps.liangCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
				if(StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])) {
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					outputList.add(strings[StableData.INT_ONE]);
					return countInputStringLength;
				}
				countInputStringLength= parserFirstCharOfTwo(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			addFixWordsOfTwo(charPosition, inputString, fixWord);
			if (StableData.INT_ZERO< fixWord[StableData.INT_ONE].length()&& StableMaps.fuCi.containsKey(StableData.EMPTY_STRING
					+ fixWord[StableData.INT_ONE].toString().charAt(StableData.INT_ZERO))){
				countInputStringLength= parserFirstCharOfTwo(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				return countInputStringLength;
			}
			countInputStringLength= parserFirstCharOfTwo(countInputStringLength, outputList, strings, fixWord);
			return countInputStringLength;
		}
		return countInputStringLength;
	}
	
	public void addFixWordsOfTwo(int charPosition, String inputString, StringBuilder[] fixWords) {
		fixWords[StableData.INT_ONE].delete(StableData.INT_ZERO, fixWords[StableData.INT_ONE].length());
		if (charPosition+ StableData.INT_SEVEN < inputString.length()) {
			fixWords[StableData.INT_ONE].append(inputString.substring(charPosition + StableData.INT_TWO
					, charPosition + StableData.INT_SEVEN));
			return;
		}
		fixWords[StableData.INT_ONE].append(inputString.substring(charPosition + StableData.INT_TWO
				, inputString.length()));
	}

	public int parserFirstCharOfTwo(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord){
		outputList.add(strings[StableData.INT_ZERO]);
		String postNext=StableData.EMPTY_STRING + strings[StableData.INT_ONE].charAt(StableData.INT_ONE);
		outputList.add(postNext);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(postNext);
		return countInputStringLength;
	}

	public int chuLiLianCiPostFixOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord) {
		if (StableMaps.lianCi.containsKey(strings[StableData.INT_TWO])){
			countInputLength= parserFirstCharOfThree(countInputLength, outputList, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
			countInputLength= parserFirstTwoCharOfThree(countInputLength, outputList, strings, prefixWord);
			return countInputLength;
		}
		return countInputLength;
	}
	
	public int chuLiLianCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord){
		if (outputList.size() == StableData.INT_ZERO){
			didNotFindFirstChar(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) && (StableMaps.mingCi.containsKey(fixWord[StableData.INT_ZERO].toString())
				|| StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())|| StableMaps.fuCi.containsKey(fixWord[StableData.INT_ZERO].toString())
				|| StableMaps.daiCi.containsKey(fixWord[StableData.INT_ZERO].toString()) || StableMaps.weiCi.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) && (StableMaps.zhuCi.containsKey(fixWord[StableData.INT_ZERO].toString())
				|| StableMaps.shengLueCi.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			for (int BackPosition = StableData.INT_ZERO; BackPosition < fixWord[StableData.INT_ONE].length(); BackPosition++){
				int[] nestCountInputStringLength = new int[StableData.INT_ONE];
				int result = loopCheckBackFix(fixWord, BackPosition, wordsForest, countInputStringLength, outputList, strings
						, nestCountInputStringLength);
				if (result == StableData.INT_RIGHT){
					return nestCountInputStringLength[StableData.INT_ZERO];
				}
			}
			if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				return countInputStringLength- StableData.INT_ONE;
			}
			return countInputStringLength- StableData.INT_THREE;
		}
		if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			return countInputStringLength- StableData.INT_ONE;
		}
		return countInputStringLength- StableData.INT_THREE;
	}

	public int loopCheckBackFix(StringBuilder[] fixWord, int backPosition, Map<String, String> wordsForest
			, int countInputStringLength, List<String> outputList, String[] strings, int[] nestCountInputStringLength){
		String charPositionAtFixWord = StableData.EMPTY_STRING + fixWord[StableData.INT_ONE].charAt(backPosition);
		if (wordsForest.containsKey(charPositionAtFixWord) && (StableMaps.zhuCi.containsKey(charPositionAtFixWord) 
				|| StableMaps.shengLueCi.containsKey(charPositionAtFixWord)|| StableMaps.fuCi.containsKey(charPositionAtFixWord))){
			if(!wordsForest.get(fixWord[StableData.INT_ZERO].toString()).contains(StableData.NLP_CI_SHENG_LUE)
					&& wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_CI_FU)){
				return StableData.INT_ERROR;
			}
			nestCountInputStringLength[StableData.INT_ZERO]= parserFirstCharOfThree(countInputStringLength, outputList
					, strings, fixWord);	
			return StableData.INT_RIGHT;
		}
		return StableData.INT_ERROR;
	}

	public void didNotFindFirstChar(List<String> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest){
		if(!wordsForest.containsKey(strings[StableData.INT_TWO])){
			if(wordsForest.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				outputList.add(strings[StableData.INT_THREE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_THREE]);
			}
			return;
		}
		if (StableMaps.fuCi.containsKey(strings[StableData.INT_TWO])){
			outputList.add(strings[StableData.INT_ZERO]);
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return;
		}
		if(StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			outputList.add(strings[StableData.INT_THREE]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_THREE]);
		}
	}

	public int parserFirstCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord){
		outputList.add(strings[StableData.INT_ZERO]);
		outputList.add(strings[StableData.INT_TWO]);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
		return countInputStringLength;
	}
	
	public int parserFirstTwoCharOfThree(int countInputStringLength, List<String> outputList, String[] strings
			, StringBuilder[] fixWord){
		outputList.add(strings[StableData.INT_ONE]);
		outputList.add(strings[StableData.INT_THREE]);
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_THREE]);
		return countInputStringLength;
	}

	public int chuLiZhuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord){
		if (StableData.INT_ZERO== outputList.size()){
			didNotFindFirstChar(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
				if(wordsForest.containsKey(strings[StableData.INT_TWO])) {
					countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
					return countInputStringLength;
				}
				outputList.add(strings[StableData.INT_ZERO]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
				return 	countInputStringLength= StableData.INT_ONE;
			} else if(fixWord[StableData.INT_ONE].length()> StableData.INT_ONE) {
				String firstChar= StableData.EMPTY_STRING+ fixWord[StableData.INT_ONE].toString().charAt(StableData.INT_ZERO);
				String secondChar= StableData.EMPTY_STRING+ fixWord[StableData.INT_ONE].toString().charAt(StableData.INT_ONE);
				if(!StableMaps.fuCi.containsKey(firstChar)&& !StableMaps.fuCi.containsKey(secondChar)
						&&!StableMaps.fuCi.containsKey(firstChar+ secondChar)) {
					if(wordsForest.containsKey(firstChar)&& wordsForest.containsKey(secondChar)) {
						outputList.add(strings[StableData.INT_ZERO]);
						fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
						fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
						countInputStringLength= StableData.INT_ONE;
						if(wordsForest.containsKey(strings[StableData.INT_TWO])) {
							outputList.add(strings[StableData.INT_TWO]);
							fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
							fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
							countInputStringLength= StableData.INT_THREE;
						}
						return countInputStringLength;
					}
				}
			}
			if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				return countInputStringLength- StableData.INT_ONE;
			}
			if (StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])){
				outputList.add(strings[StableData.INT_ZERO]);
				outputList.add(strings[StableData.INT_TWO]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
				return countInputStringLength;
			}
		}
		return countInputStringLength;
	}

	public int chuLiJieCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord){
		if (StableData.INT_ZERO== outputList.size()&& (wordsForest.get(strings[StableData.INT_TWO])
				.contains(StableData.NLP_CI_WEI))){
			outputList.add(strings[StableData.INT_ZERO]);
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		if (outputList.size() > StableData.INT_ZERO&& wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.qingTaiCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					|| StableMaps.weiCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					|| StableMaps.lianCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			} else{
				if(StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())) {
					if(StableMaps.xingWeiCi.containsKey(strings[StableData.INT_ONE])
							|| StableMaps.xingRongCi.containsKey(strings[StableData.INT_ONE])) {
						countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
						return countInputStringLength;
					}
				}
				if (StableMaps.mingCi.containsKey(strings[StableData.INT_TWO])){
					outputList.add(strings[StableData.INT_ZERO]);
					outputList.add(strings[StableData.INT_TWO]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
					return countInputStringLength;
				}else if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
					if(StableMaps.jieCi.containsKey(strings[StableData.INT_ONE])) {
						countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
						return countInputStringLength;
					}
					outputList.add(strings[StableData.INT_ONE]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
					countInputStringLength = StableData.INT_TWO;
					return countInputStringLength;
				}else if (StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])){
					outputList.add(strings[StableData.INT_ZERO]);
					outputList.add(strings[StableData.INT_TWO]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
					return countInputStringLength;
				}
			}
		}
		if(StableMaps.jieCi.containsKey(strings[StableData.INT_ONE])) {
			countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
			return countInputStringLength;
		}
		outputList.add(strings[StableData.INT_ZERO]);
		if(StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])) {
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		return countInputStringLength= StableData.INT_ONE;
	}

	public int chuLiLiangCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.mingCi.containsKey(fixWord[StableData.INT_ZERO].toString())|| StableMaps.daiCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength = parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			} 
			if (StableMaps.liangCi.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				return StableData.INT_TWO;
			}
			if ((StableMaps.xingWeiCi.containsKey(fixWord[StableData.INT_ZERO].toString())||StableMaps.xingRongCi.containsKey(fixWord[StableData.INT_ZERO].toString()))
					&& StableMaps.mingCi.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
				fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
				return StableData.INT_TWO;
			}
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		outputList.add(strings[StableData.INT_ZERO]);
		if (StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])){
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return StableData.INT_THREE;
		}
		return  StableData.INT_ONE;
	}

	public int chuLiMingCiOfTwoForMap(Map<String, String> wordsForest,  Map<String, WordFrequency> outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.liangCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
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
			, String[] strings, StringBuilder[] fixWord){
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.mingCi.containsKey(fixWord[StableData.INT_ZERO].toString())|| StableMaps.daiCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
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
			, String[] strings, StringBuilder[] fixWord){
		if (outputList.size()== StableData.INT_ZERO&& StableMaps.weiCi.containsKey(strings[StableData.INT_TWO])){
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
			if (StableMaps.lianCi.containsKey(fixWord[StableData.INT_ZERO].toString())|| StableMaps.qingTaiCi.containsKey(fixWord[StableData.INT_ZERO].toString())
					|| StableMaps.weiCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
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
			, String[] strings, StringBuilder[] fixWord){
		if (outputList.size()== StableData.INT_ZERO){
			didNotFindFirstCharForMap(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (StableMaps.mingCi.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| StableMaps.daiCi.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| StableMaps.weiCi.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| StableMaps.fuCi.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			countInputStringLength = parserFirstCharOfThreeForMap(countInputStringLength, outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString()) 
				&& (StableMaps.zhuCi.containsKey(fixWord[StableData.INT_ZERO].toString())
						|| StableMaps.shengLueCi.containsKey(fixWord[StableData.INT_ZERO].toString()))){
			for (int BackPosition= StableData.INT_ZERO; BackPosition< fixWord[StableData.INT_ONE].length(); BackPosition++){
				int[] nestCountInputStringLength = new int[StableData.INT_ONE];
				int result= loopCheckBackFixForMap(fixWord, BackPosition, wordsForest, countInputStringLength, outputList, strings
						, nestCountInputStringLength);
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
			, int countInputStringLength, Map<String, WordFrequency> outputList, String[] strings, int[] nestCountInputStringLength){
		String charPositionAtFixWord= StableData.EMPTY_STRING+ fixWord[StableData.INT_ONE].charAt(backPosition);
		if (wordsForest.containsKey(charPositionAtFixWord)&& (StableMaps.zhuCi.containsKey(charPositionAtFixWord) 
				|| wordsForest.get(charPositionAtFixWord).contains(StableData.NLP_CI_SHENG_LUE))){
			nestCountInputStringLength[StableData.INT_ZERO]= parserFirstCharOfThreeForMap(countInputStringLength, outputList
					, strings, fixWord, wordsForest);
			return StableData.INT_RIGHT;
		}
		return StableData.INT_ERROR;
	}

	public int chuLiZhuCiOfThreeForMap(Map<String, String> wordsForest, Map<String, WordFrequency>  outputList, int countInputStringLength
			, String[] strings, StringBuilder[] fixWord){
		if (StableData.INT_ZERO== outputList.size()){
			didNotFindFirstCharForMap(outputList, strings, fixWord, wordsForest);
			return countInputStringLength;
		}
		if (wordsForest.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if (StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
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

	public void didNotFindFirstCharForMap(Map<String, WordFrequency> outputList, String[] strings, StringBuilder[] fixWord
			, Map<String, String> wordsForest){
		if(!wordsForest.containsKey(strings[StableData.INT_TWO])){
			return;
		}
		if (StableMaps.fuCi.containsKey(strings[StableData.INT_TWO])){
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
			String[] strings, StringBuilder[] fixWord){
		if (StableMaps.xingWeiCi.containsKey(fixWord[StableData.INT_ZERO].toString())|| StableMaps.mingCi
				.containsKey(fixWord[StableData.INT_ZERO].toString())){
			if(StableMaps.dongCi.containsKey(strings[StableData.INT_TWO])){
				countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if(StableMaps.dongCi.containsKey(strings[StableData.INT_THREE])){
				if(StableMaps.fuCi.containsKey(StableData.EMPTY_STRING+ fixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))){
					if(StableMaps.dongCi.containsKey(strings[StableData.INT_ONE])|| StableMaps.qingTaiCi.containsKey(strings[StableData.INT_ONE])) {
						countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
						return countInputStringLength;
					}
					outputList.add(strings[StableData.INT_ZERO]);
					if (StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])){
						outputList.add(strings[StableData.INT_TWO]);
						fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
						fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
						return countInputStringLength;
					}
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
					return countInputStringLength= StableData.INT_ONE;
				}
				if(!StableMaps.dingMingCi.containsKey(strings[StableData.INT_ZERO])){
					if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
						countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
						return countInputStringLength;
					}
					outputList.add(strings[StableData.INT_ZERO]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
					return countInputStringLength= StableData.INT_ONE;
				}
			}
			if(StableMaps.mingCi.containsKey(strings[StableData.INT_TWO])){
				if(StableData.INT_ZERO< fixWord[StableData.INT_ONE].length()&& StableMaps.zhuCi.containsKey(StableData.EMPTY_STRING
						+ fixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))){
					if(StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])) {
						if(!StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
							countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
							return countInputStringLength;
						}
						countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
						return countInputStringLength;
					}
					outputList.add(strings[StableData.INT_ZERO]);
					fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
					fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
					return countInputStringLength= StableData.INT_ONE;
				}
				countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if(StableMaps.mingCi.containsKey(strings[StableData.INT_ONE])|| StableMaps.fuCi.containsKey(strings[StableData.INT_ONE])){
				countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if (StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])){
				countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			outputList.add(strings[StableData.INT_ZERO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
			countInputStringLength= StableData.INT_ONE;
			return countInputStringLength;
		} 
		if(StableMaps.dongCi.containsKey(strings[StableData.INT_THREE])){
			if(StableMaps.dongCi.containsKey(strings[StableData.INT_TWO])){
				if(StableMaps.mingCi.containsKey(strings[StableData.INT_ZERO])) {
					countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
					return countInputStringLength;
				}
			}
			if(null!= fixWord[StableData.INT_ZERO]&& StableData.INT_ZERO<fixWord[StableData.INT_ZERO].length()){
				if(StableMaps.zhuCi.containsKey(StableData.EMPTY_STRING+ fixWord[StableData.INT_ZERO].charAt(StableData.INT_ZERO))){
					if(!StableMaps.mingCi.containsKey(strings[StableData.INT_ONE])) {
						countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);	
						return countInputStringLength;
					}
				}
			}
			if(StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])) {
				countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);	
				return countInputStringLength;
			}
			countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
			return countInputStringLength;
		}
		if(StableMaps.mingCi.containsKey(strings[StableData.INT_TWO])){
			if(StableData.INT_ZERO< fixWord[StableData.INT_ONE].length()&& StableMaps.zhuCi.containsKey(StableData.EMPTY_STRING
					+ fixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))){
				if(StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
					if(!StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
						countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
						return countInputStringLength;
					}
					countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
					return countInputStringLength;
				}
				countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if(StableData.INT_ZERO< fixWord[StableData.INT_ONE].length()&& StableMaps.dingMingCi.containsKey(StableData.EMPTY_STRING
					+ fixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))){
				countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			if(StableMaps.dongCi.containsKey(fixWord[StableData.INT_ZERO].toString())){
				countInputStringLength= parserFirstTwoCharOfThree(countInputStringLength, outputList, strings, fixWord);
				return countInputStringLength;
			}
			countInputStringLength= parserFirstCharOfThree(countInputStringLength, outputList, strings, fixWord);
			return countInputStringLength;
		}
		if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
			countInputStringLength= StableData.INT_TWO;
			return countInputStringLength;
		}
		outputList.add(strings[StableData.INT_ZERO]);
		if(StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])) {
			outputList.add(strings[StableData.INT_TWO]);
			fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
			fixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputStringLength;
		}
		fixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, fixWord[StableData.INT_ZERO].length());
		fixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		return countInputStringLength= StableData.INT_ONE;
	}

	public int chuLiShiTaiCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord) {
		if ((StableMaps.mingCi.containsKey(strings[StableData.INT_TWO].toString()))
				&& (StableMaps.jieCi.containsKey(prefixWord[StableData.INT_ZERO].toString())
						|| StableMaps.xingWeiCi.containsKey(prefixWord[StableData.INT_ZERO].toString())
						|| StableMaps.dongCi.containsKey(prefixWord[StableData.INT_ZERO].toString()))){
			countInputLength= parserFirstCharOfThree(countInputLength, outputList, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.dongCi.containsKey(strings[StableData.INT_TWO].toString())
				||StableMaps.liangCi.containsKey(strings[StableData.INT_TWO].toString())) {
			countInputLength= parserFirstCharOfThree(countInputLength, outputList, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])){
			outputList.add(strings[StableData.INT_ONE]);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(strings[StableData.INT_ONE]);
			countInputLength= StableData.INT_TWO;
			return countInputLength;
		}
		outputList.add(strings[StableData.INT_ZERO]);
		if(StableMaps.CiTwo.containsKey(strings[StableData.INT_TWO])) {
			outputList.add(strings[StableData.INT_TWO]);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(strings[StableData.INT_TWO]);
			return countInputLength;
		}
		prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
		prefixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		return countInputLength= StableData.INT_ONE;
	}

	public int chuLiFuCiOfThree(Map<String, String> wordsForest, List<String> outputList, int countInputLength,
			String[] strings, StringBuilder[] prefixWord) {
		if (StableMaps.fuCi.containsKey(strings[StableData.INT_TWO].toString())) {
			if (StableMaps.fuCi.containsKey(prefixWord[StableData.INT_ZERO].toString())) {
				countInputLength= parserFirstCharOfThree(countInputLength, outputList, strings, prefixWord);
				return countInputLength;
			}
		}
		if (StableMaps.dongCi.containsKey(strings[StableData.INT_TWO].toString())) {
			if (StableMaps.zhuCi.containsKey(StableData.EMPTY_STRING+ prefixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))
					|| StableMaps.daiCi.containsKey(StableData.EMPTY_STRING+ prefixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))) {
				countInputLength= parserFirstCharOfThree(countInputLength, outputList, strings, prefixWord);
				return countInputLength;
			}
		}
		if(StableMaps.CiTwo.containsKey(strings[StableData.INT_ONE])) {
			countInputLength= parserFirstTwoCharOfThree(countInputLength, outputList, strings, prefixWord);
			return countInputLength;
		}
		outputList.add(strings[StableData.INT_ZERO]);
		prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
		prefixWord[StableData.INT_ZERO].append(strings[StableData.INT_ZERO]);
		return StableData.INT_ONE;
	}
}