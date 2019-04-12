package org.tinos.engine.nlp.imp;
import java.util.List;
import java.util.Map;
import org.tinos.engin.utils.WordForestUtil;
import org.tinos.engine.nlp.NLPController;
import org.tinos.engine.pos.POSController;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
import org.tinos.view.stable.StableMaps;
public class NLPControllerImp implements NLPController{
	public int doSlangPartAndPOSCheckForTwoChar(int countInputStringLength, List<String> outputList
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils, int charPosition, String textInputString){
		String countWordNode = stringBuilder.toString();
		if (!wordsForest.containsKey(countWordNode)){
			outputList.add(String.valueOf(countWordNode.charAt(StableData.INT_ZERO)));
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(countWordNode.charAt(StableData.INT_ZERO));
			return --countInputStringLength;
		}
		if (prefixWord[StableData.INT_ZERO].length()== StableData.INT_ZERO){
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(countWordNode);
			outputList.add(countWordNode);
			return countInputStringLength;
		}
		String[] strings= new String[StableData.INT_TWO];
		strings[StableData.INT_ZERO]= String.valueOf(countWordNode.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE]= String.valueOf(countWordNode.charAt(StableData.INT_ZERO))
				+ String.valueOf(countWordNode.charAt(StableData.INT_ONE));
		if (StableMaps.mingCi.containsKey(strings[StableData.INT_ZERO])){
			countInputStringLength= posUtils.chuLiMingCiOfTwo(wordsForest, outputList, countInputStringLength
					, strings, prefixWord, charPosition, textInputString);
			return countInputStringLength;
		}
		if (StableMaps.baDongCi.containsKey(strings[StableData.INT_ZERO])){
			countInputStringLength = posUtils.chuLiBaDongCiOfTwo(wordsForest, outputList, countInputStringLength
					, strings, prefixWord);
			return countInputStringLength;
		}
		prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
		prefixWord[StableData.INT_ZERO].append(countWordNode);
		outputList.add(countWordNode);
		return countInputStringLength;
	}

	public int doPOSAndEMMCheckOfThree(int countInputLength, List<String> outputList
			, Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] prefixWord
			, POSController posUtils, int charPosition, String textInputString){
		String inputString= stringBuilder.toString();
		if (wordsForest.containsKey(inputString)){
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(inputString);
			outputList.add(inputString);
			return countInputLength;
		}
		String[] strings= new String[StableData.INT_FOUR];
		strings[StableData.INT_ZERO]= String.valueOf(inputString.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE]= String.valueOf(inputString.charAt(StableData.INT_ZERO)) 
				+ inputString.charAt(StableData.INT_ONE);
		strings[StableData.INT_TWO]= String.valueOf(inputString.charAt(StableData.INT_ONE)) 
				+ inputString.charAt(StableData.INT_TWO);
		strings[StableData.INT_THREE]= String.valueOf(inputString.charAt(StableData.INT_TWO));
		if (null== prefixWord[StableData.INT_ZERO]){
			if (wordsForest.containsKey(inputString)){
				prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
				prefixWord[StableData.INT_ZERO].append(inputString);
				outputList.add(inputString);
				return countInputLength;
			} 
			StringBuilder stringsBuilder= new StringBuilder();
			countInputLength= doSlangPartAndPOSCheckForTwoChar(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils, charPosition, textInputString);
			return countInputLength;
		}
		if (!wordsForest.containsKey(strings[StableData.INT_ZERO])){
			StringBuilder stringsBuilder= new StringBuilder();
			countInputLength= doSlangPartAndPOSCheckForTwoChar(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils, charPosition, textInputString);
			return countInputLength;
		}
		if (StableMaps.lianCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength = posUtils.chuLiLianCiOfThree(wordsForest, outputList, countInputLength, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.jieCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength = posUtils.chuLiJieCiOfThree(wordsForest, outputList, countInputLength, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.zhuCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength = posUtils.chuLiZhuCiOfThree(wordsForest, outputList, countInputLength, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.liangCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength = posUtils.chuLiLiangCiOfThree(wordsForest, outputList, countInputLength, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.mingCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength= posUtils.chuLiMingCiOfThree(wordsForest, outputList, countInputLength, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.shiTaiCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength= posUtils.chuLiShiTaiCiOfThree(wordsForest, outputList, countInputLength, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.dongCi.containsKey(strings[StableData.INT_ZERO])){
			if(StableMaps.zhuCi.containsKey(prefixWord[StableData.INT_ZERO].toString())
					|| StableMaps.mingCi.containsKey(strings[StableData.INT_TWO])) {
				countInputLength = posUtils.parserFirstCharOfThree(countInputLength, outputList, strings, prefixWord, wordsForest);
				return countInputLength;
			}
		}
		StringBuilder stringsBuilder= new StringBuilder();
		countInputLength= doSlangPartAndPOSCheckForTwoChar(--countInputLength, outputList
				, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils, charPosition, textInputString);
		return countInputLength;
	} 

	public int doSlangCheck(int countInputStringLength, List<String> output, StringBuilder stringBuilder,
			Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils, int charPosition, String textInputString){
		String inputString = stringBuilder.toString();
		if (wordsForest.containsKey(inputString)){
			output.add(inputString);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(inputString);
			return countInputStringLength;
		}//will make pre 3 or post 3 check. now finished pre 3 .20190330
		String preRegister= StableData.EMPTY_STRING+ inputString.charAt(StableData.INT_ZERO)+ inputString.charAt(StableData.INT_ONE);
		if(StableMaps.dongCi.containsKey(StableData.EMPTY_STRING+ inputString.charAt(StableData.INT_THREE)
		+ prefixWord[StableData.INT_ONE].charAt(StableData.INT_ZERO))) {
			countInputStringLength= doPOSAndEMMCheckOfThree(--countInputStringLength, output, wordsForest
					, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils, charPosition, textInputString);
			return countInputStringLength;
		}
		if(StableMaps.mingCi.containsKey(preRegister)) {
			String postRegister= StableData.EMPTY_STRING+ inputString.charAt(StableData.INT_TWO)+ inputString.charAt(StableData.INT_THREE);
			if(StableMaps.mingCi.containsKey(postRegister)) {
				output.add(preRegister);
				prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
				prefixWord[StableData.INT_ZERO].append(preRegister);
				return countInputStringLength-StableData.INT_TWO;
			}
		}
		countInputStringLength= doPOSAndEMMCheckOfThree(--countInputStringLength, output, wordsForest
				, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils, charPosition, textInputString);
		return countInputStringLength;
	}

	public int doSlangCheckForMap(int countInputStringLength, List<String> output, StringBuilder stringBuilder
			, Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils, int charPosition, String textInputString){
		String inputString= stringBuilder.toString();
		if (wordsForest.containsKey(inputString)){
			output.add(inputString);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(inputString);
			return countInputStringLength;
		}
		countInputStringLength= doPOSAndEMMCheckOfThree(--countInputStringLength, output, wordsForest
				, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils, charPosition, textInputString);
		return countInputStringLength;
	}

	public int doSlangPartAndPOSCheckForTwoCharForMap(int countInputStringLength, Map<String, WordFrequency> outputList
			, StringBuilder stringBuilder, Map<String, String> wordsForest, StringBuilder[] prefixWord
			, POSController posUtils){
		String countWordNode= stringBuilder.toString();
		if (!wordsForest.containsKey(countWordNode)){
			WordForestUtil.wordsForestNotContainsKey(outputList, countWordNode, prefixWord);
			return --countInputStringLength;
		}
		if (prefixWord[StableData.INT_ZERO].length()== StableData.INT_ZERO){
			WordForestUtil.prefixWordEqualZero(outputList, countWordNode, prefixWord);
			return countInputStringLength;
		}
		String[] strings= new String[StableData.INT_TWO];
		strings[StableData.INT_ZERO]= String.valueOf(countWordNode.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE]= String.valueOf(countWordNode.charAt(StableData.INT_ZERO))
				+ String.valueOf(countWordNode.charAt(StableData.INT_ONE));
		if (wordsForest.containsKey(strings[StableData.INT_ZERO])){
			if (wordsForest.get(strings[StableData.INT_ZERO]).contains(StableData.NLP_CI_MING)){
				countInputStringLength= posUtils.chuLiMingCiOfTwoForMap(wordsForest, outputList, countInputStringLength
						, strings, prefixWord);
				return countInputStringLength;
			}
		}
		if (wordsForest.containsKey(strings[StableData.INT_ONE])){
			WordForestUtil.wordsForestContainsKey(outputList, countWordNode, prefixWord);
			return countInputStringLength;
		}
		return StableData.INT_ZERO;
	}

	public int doPOSAndEMMCheckOfThreeForMap(int countInputLength, Map<String, WordFrequency> outputList
			, Map<String, String> wordsForest, StringBuilder stringBuilder, StringBuilder[] prefixWord
			, POSController posUtils){
		String inputString= stringBuilder.toString();
		if (wordsForest.containsKey(inputString)){
			WordForestUtil.wordsForestContainsKey(outputList, inputString, prefixWord);
			return countInputLength;
		}
		String[] strings= new String[StableData.INT_FOUR];
		strings[StableData.INT_ZERO]= String.valueOf(inputString.charAt(StableData.INT_ZERO));
		strings[StableData.INT_ONE]= String.valueOf(inputString.charAt(StableData.INT_ZERO)) 
				+ inputString.charAt(StableData.INT_ONE);
		strings[StableData.INT_TWO]= String.valueOf(inputString.charAt(StableData.INT_ONE) 
				+ inputString.charAt(StableData.INT_TWO));
		strings[StableData.INT_THREE]= String.valueOf(inputString.charAt(StableData.INT_TWO));
		if (null== prefixWord[StableData.INT_ZERO]){
			if (wordsForest.containsKey(inputString)){
				WordForestUtil.wordsForestContainsKey(outputList, inputString, prefixWord);
				return countInputLength;
			} 
			StringBuilder stringsBuilder= new StringBuilder();
			countInputLength= doSlangPartAndPOSCheckForTwoCharForMap(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
			return countInputLength;
		}
		if (!wordsForest.containsKey(strings[StableData.INT_ZERO])){
			StringBuilder stringsBuilder= new StringBuilder();
			countInputLength= doSlangPartAndPOSCheckForTwoCharForMap(--countInputLength, outputList
					, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
			return countInputLength;
		}
		if (StableMaps.zhuCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength= posUtils.chuLiZhuCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.liangCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength= posUtils.chuLiLiangCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.zhuCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength= posUtils.chuLiJieCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		if (StableMaps.lianCi.containsKey(strings[StableData.INT_ZERO])){
			countInputLength= posUtils.chuLiLianCiOfThreeForMap(wordsForest, outputList, countInputLength
					, strings, prefixWord);
			return countInputLength;
		}
		StringBuilder stringsBuilder= new StringBuilder();
		countInputLength= doSlangPartAndPOSCheckForTwoCharForMap(--countInputLength, outputList
				, stringsBuilder.append(strings[StableData.INT_ONE]), wordsForest, prefixWord, posUtils);
		return countInputLength;
	} 

	public int doSlangCheckForMap(int countInputStringLength, Map<String, WordFrequency> output, StringBuilder stringBuilder
			, Map<String, String> wordsForest, StringBuilder[] prefixWord, POSController posUtils){
		String inputString= stringBuilder.toString();
		if (wordsForest.containsKey(inputString)){
			WordForestUtil.wordsForestContainsKey(output, inputString, prefixWord);
			return countInputStringLength;
		}
		if(StableMaps.mingCi.containsKey(StableData.EMPTY_STRING+ inputString.charAt(StableData.INT_ZERO)+ inputString.charAt(StableData.INT_ONE))) {
			if(StableMaps.mingCi.containsKey(StableData.EMPTY_STRING+ inputString.charAt(StableData.INT_TWO)+ inputString.charAt(StableData.INT_THREE))) {
				WordForestUtil.wordsForestContainsKey(output, StableData.EMPTY_STRING+ inputString.charAt(StableData.INT_ZERO)+ inputString.charAt(StableData.INT_ONE), prefixWord);
				return countInputStringLength;
			}
		}
		countInputStringLength= doPOSAndEMMCheckOfThreeForMap(--countInputStringLength, output, wordsForest
				, stringBuilder.delete(StableData.INT_THREE, StableData.INT_FOUR), prefixWord, posUtils);
		return countInputStringLength;
	}
}