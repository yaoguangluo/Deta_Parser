package org.tinos.engine.pos.imp;
import java.util.List;
import java.util.Map;
import org.tinos.engine.pos.POSController;
import org.tinos.view.stable.StableData;
public class POSControllerImp implements POSController{
	public int chuLiZhuCi(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength,
			String[] strings, String[] prefixWord){
		if(outputList.size() > StableData.INT_ZERO){
			if(wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_DONG_CI)){
				countInputStringLength -= StableData.INT_THREE;
				outputList.add(strings[StableData.INT_ZERO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ZERO];
				countInputStringLength += StableData.INT_ONE;
				if(wordsForest.containsKey(strings[StableData.INT_TWO])){
					outputList.add(strings[StableData.INT_TWO]);
					prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
					countInputStringLength += StableData.INT_TWO;
					return countInputStringLength;
				}
				return countInputStringLength;
			}else{
				countInputStringLength -= StableData.INT_THREE;
				if(wordsForest.containsKey(strings[StableData.INT_ONE])){
					outputList.add(strings[StableData.INT_ONE]);
					prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}else{
			if(wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_FU_CI)){
				outputList.add(strings[StableData.INT_ZERO]);
				outputList.add(strings[StableData.INT_TWO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
				return countInputStringLength;
			}
		}
		return countInputStringLength; 
	}

	public int chuLiLianCi(Map<String, String> wordsForest, List<String> outputList, int countInputStringLength
			, String[] strings, String[] prefixWord){
		if(outputList.size() > StableData.INT_ZERO){
			if(wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_FU_CI) 
					|| wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_DONG_CI)
					|| wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_WEI_CI)
					|| wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_DAI_CI)){
				countInputStringLength -= StableData.INT_THREE;
				outputList.add(strings[StableData.INT_ZERO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ZERO];
				countInputStringLength += StableData.INT_ONE;
				if(wordsForest.containsKey(strings[StableData.INT_TWO])){
					outputList.add(strings[StableData.INT_TWO]);
					prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
					countInputStringLength += StableData.INT_TWO;
					return countInputStringLength;
				}
				return countInputStringLength;
			}else{
				countInputStringLength -= StableData.INT_THREE;
				if(wordsForest.containsKey(strings[StableData.INT_ONE])){
					outputList.add(strings[StableData.INT_ONE]);
					prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}else{
			if(wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_FU_CI)){
				outputList.add(strings[StableData.INT_ZERO]);
				outputList.add(strings[StableData.INT_TWO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
				return countInputStringLength;
			}
		}
		return countInputStringLength; 
	}

	public int chuLiJieCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, String[] prefixWord){
		if(outputList.size() > StableData.INT_ZERO){
			if(wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_LIAN_CI)
					|| wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_QING_TAI_CI)
					|| wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_WEI_CI)){
				countInputStringLength -= StableData.INT_THREE;
				outputList.add(strings[StableData.INT_ZERO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ZERO];
				countInputStringLength += StableData.INT_ONE;
				if(wordsForest.containsKey(strings[StableData.INT_TWO])){
					outputList.add(strings[StableData.INT_TWO]);
					prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
					countInputStringLength += StableData.INT_TWO;
					return countInputStringLength;
				}
				return countInputStringLength;
			}else{
				countInputStringLength -= StableData.INT_THREE;
				if(wordsForest.containsKey(strings[StableData.INT_ONE])){
					outputList.add(strings[StableData.INT_ONE]);
					prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
					countInputStringLength += StableData.INT_TWO;
				}
				return countInputStringLength;
			}
		}else{
			if(wordsForest.get(strings[StableData.INT_TWO]).contains(StableData.NLP_WEI_CI)){
				outputList.add(strings[StableData.INT_ZERO]);
				outputList.add(strings[StableData.INT_TWO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
				return countInputStringLength;
			}
		}
		return countInputStringLength; 
	}

	public int chuLiLiangCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, String[] prefixWord){
		if(wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_MING_CI)
				|| wordsForest.get(prefixWord[StableData.INT_ZERO]).contains(StableData.NLP_DAI_CI)){
			countInputStringLength -= StableData.INT_THREE;
			outputList.add(strings[StableData.INT_ZERO]);
			prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ZERO];
			countInputStringLength += StableData.INT_ONE;
			if(wordsForest.containsKey(strings[StableData.INT_TWO])){
				outputList.add(strings[StableData.INT_TWO]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_TWO];
				countInputStringLength += StableData.INT_TWO;
				return countInputStringLength;
			}
			return countInputStringLength;
		}else{
			countInputStringLength -= StableData.INT_THREE;
			if(wordsForest.containsKey(strings[StableData.INT_ONE])){
				outputList.add(strings[StableData.INT_ONE]);
				prefixWord[StableData.INT_ZERO] = strings[StableData.INT_ONE];
				countInputStringLength += StableData.INT_TWO;
			}
			return countInputStringLength;
		}
	}
}
