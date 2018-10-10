package org.tinos.utils.imp;
import java.util.List;
import java.util.Map;
import org.tinos.utils.EngineUtils;
import org.tinos.zabbi.DataString;
public class EngineUtilsImp implements EngineUtils{
	public int doSlangCheck(int countInputStringLength, List<String> output, String inputString,
			Map<String, String> wordsForest) { 
		if(wordsForest.containsKey(inputString)){
			output.add(inputString);
			return countInputStringLength;
		}else {
			countInputStringLength = doPOSAndEMMCheck(countInputStringLength - DataString.INT_ONE
					, output, wordsForest,
					DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO) 
					+ inputString.charAt(DataString.INT_ONE) 
					+ inputString.charAt(DataString.INT_TWO));	
			return countInputStringLength;
		}
	}

	public int doPOSAndEMMCheck(int countInputStringLength, List<String> outputList
			, Map<String, String> wordsForest, String inputString){
		if(wordsForest.containsKey(inputString)) {
			outputList.add(inputString);
			return countInputStringLength;
		}	
		String [] strings = new String[DataString.INT_FOUR];
		strings[DataString.INT_ZERO] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO);
		strings[DataString.INT_ONE] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO) 
		+ inputString.charAt(DataString.INT_ONE);	
		strings[DataString.INT_TWO] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ONE) 
		+ inputString.charAt(DataString.INT_TWO);
		strings[DataString.INT_THREE] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO);
		//HAS FIRST
		if(wordsForest.get(strings[DataString.INT_ZERO]).contains(DataString.NLP_LIANG_CI)){
			countInputStringLength = chuLiLiangCi(wordsForest, outputList, countInputStringLength, strings);	
			return countInputStringLength;
		}else if(wordsForest.get(strings[DataString.INT_ZERO]).contains(DataString.NLP_JIE_CI)){
			countInputStringLength = chuLiJieCi(wordsForest, outputList, countInputStringLength, strings);	
			return countInputStringLength;
		}else if(wordsForest.get(strings[DataString.INT_ZERO]).contains(DataString.NLP_LIAN_CI)){
			countInputStringLength = chuLiLianCi(wordsForest, outputList, countInputStringLength, strings);	
			return countInputStringLength;
		}	
		countInputStringLength = doSlangPartCheck(countInputStringLength - DataString.INT_ONE, outputList
				, strings[DataString.INT_ONE], wordsForest);
		return countInputStringLength;
	}

	public int chuLiLianCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings) {
		if(outputList.size() > DataString.INT_ZERO) {
			if(wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
					.contains(DataString.NLP_FU_CI)
					|| wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
					.contains(DataString.NLP_DONG_CI)){
				countInputStringLength -= DataString.INT_THREE;
				outputList.add(strings[DataString.INT_ZERO]);
				countInputStringLength += DataString.INT_ONE;
				if(wordsForest.containsKey(strings[DataString.INT_TWO])) {
					outputList.add(strings[DataString.INT_TWO]);
					countInputStringLength += DataString.INT_TWO;
					return countInputStringLength;
				}
				return countInputStringLength;
			}else {
				countInputStringLength -= DataString.INT_THREE;
				if(wordsForest.containsKey(strings[DataString.INT_ONE])) {
					outputList.add(strings[DataString.INT_ONE]);
					countInputStringLength += DataString.INT_TWO;
				}
				return countInputStringLength;
			}
		}else {
			if(wordsForest.get(strings[DataString.INT_TWO]).contains(DataString.NLP_FU_CI)){
				outputList.add(strings[DataString.INT_ZERO]);
				outputList.add(strings[DataString.INT_TWO]);
				return countInputStringLength;
			}
		}
		return countInputStringLength; 
	}

	public int chuLiJieCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings) {
		if(outputList.size() > DataString.INT_ZERO) {
			if(wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
					.contains(DataString.NLP_LIAN_CI)
					|| wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
					.contains(DataString.NLP_QING_TAI_CI)
					|| wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
					.contains(DataString.NLP_WEI_CI)){
				countInputStringLength -= DataString.INT_THREE;
				outputList.add(strings[DataString.INT_ZERO]);
				countInputStringLength += DataString.INT_ONE;
				if(wordsForest.containsKey(strings[DataString.INT_TWO])) {
					outputList.add(strings[DataString.INT_TWO]);
					countInputStringLength += DataString.INT_TWO;
					return countInputStringLength;
				}
				return countInputStringLength;
			}else{
				countInputStringLength -= DataString.INT_THREE;
				if(wordsForest.containsKey(strings[DataString.INT_ONE])) {
					outputList.add(strings[DataString.INT_ONE]);
					countInputStringLength += DataString.INT_TWO;
				}
				return countInputStringLength;
			}
		}else {
			if(wordsForest.get(strings[DataString.INT_TWO]).contains(DataString.NLP_WEI_CI)){
				outputList.add(strings[DataString.INT_ZERO]);
				outputList.add(strings[DataString.INT_TWO]);
				return countInputStringLength;
			}
		}
		return countInputStringLength; 
	}

	public int chuLiLiangCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings) {
		if(wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
				.contains(DataString.NLP_MING_CI)
				|| wordsForest.get(outputList.get(outputList.size() - DataString.INT_ONE))
				.contains(DataString.NLP_DAI_CI)){
			countInputStringLength -= DataString.INT_THREE;
			outputList.add(strings[DataString.INT_ZERO]);
			countInputStringLength += DataString.INT_ONE;
			if(wordsForest.containsKey(strings[DataString.INT_TWO])) {
				outputList.add(strings[DataString.INT_TWO]);
				countInputStringLength += DataString.INT_TWO;
				return countInputStringLength;
			}
			return countInputStringLength;
		}else {
			countInputStringLength -= DataString.INT_THREE;
			if(wordsForest.containsKey(strings[DataString.INT_ONE])) {
				outputList.add(strings[DataString.INT_ONE]);
				countInputStringLength += DataString.INT_TWO;
			}
			return countInputStringLength;
		}
	}

	public int doSlangPartCheck(int countInputStringLength, List<String> outputList
			, String countWordNode, Map<String, String> wordsForest) {
		if(wordsForest.containsKey(countWordNode)) {
			outputList.add(countWordNode);
			return countInputStringLength;
		}else {
			outputList.add(DataString.EMPTY_STRING + countWordNode.charAt(DataString
					.INT_ZERO));
			countInputStringLength --;
			return countInputStringLength;
		} 
	}
}