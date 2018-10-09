package org.tinos.utils.imp;
import java.util.List;
import java.util.Map;
import org.tinos.utils.EngineUtils;
import org.tinos.zabbi.DataString;
public class EngineUtilsImp implements EngineUtils{
	public List<String> doSlangCheck(List<String> output, Map<String, String> words, String inputString) {
		if(words.containsKey(inputString)){
			output.add(inputString);
		}else {
			output.add(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO) + 
					inputString.charAt(DataString.INT_ONE));
			output.add(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO) +
					inputString.charAt(DataString.INT_THREE));
		}
		return output;
	}

	public List<String> doEuclidCheck(List<String> output, String euclid, String inputString) {
		if(euclid.contains(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO))){
			String []inputStrings = inputString.split(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO));
			output.add(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO));
			output.add(DataString.EMPTY_STRING + inputStrings[DataString.INT_ONE]);
		}else if(euclid.contains(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO))){
			String []inputStrings = inputString.split(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO));
			output.add(DataString.EMPTY_STRING + inputStrings[DataString.INT_ZERO]);
			output.add(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO));
		}else {
			output.add(DataString.EMPTY_STRING + inputString);
		}
		return output;
	}

	public List<String> doPOSAndEMMCheck(List<String> output, String euclid,  Map<String, String> wordsForest, String inputString){
		String [] Strings = new String[DataString.INT_FOUR];
		Strings[DataString.INT_ZERO] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO);
		Strings[DataString.INT_ONE] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO) + inputString.charAt(DataString.INT_ONE);
		Strings[DataString.INT_TWO] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ONE) + inputString.charAt(DataString.INT_TWO);
		Strings[DataString.INT_THREE] = DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO);
		if(wordsForest.get(Strings[DataString.INT_ZERO]).contains(DataString.NLP_LIAN_CI) && (output.size() > DataString.INT_ZERO)){
				if(wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_MING_CI)
						|| wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_DAI_CI)){
					output.add(Strings[DataString.INT_ZERO]);
					output.add(Strings[DataString.INT_TWO]);
					return output;
				}else if(wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_XIAN_DING_CI) 
						|| wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_ZHU_CI)){
					output.add(Strings[DataString.INT_ONE]);
					output.add(Strings[DataString.INT_THREE]);
					return output;
				}	
		}
		if(wordsForest.get(Strings[DataString.INT_ZERO]).contains(DataString.NLP_JIE_CI) && (output.size() > DataString.INT_ZERO)){
				if(wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_LIAN_CI)
						|| wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_QING_TAI_CI)){
					output.add(Strings[DataString.INT_ZERO]);
					output.add(Strings[DataString.INT_TWO]);
					return output;
				}else if(wordsForest.get(output.get(output.size() - DataString.INT_ONE)).contains(DataString.NLP_FU_CI)){
					output.add(Strings[DataString.INT_ONE]);
					output.add(Strings[DataString.INT_THREE]);
					return output;
				}
		}
		if(euclid.contains(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO))){
			String []inputStrings = inputString.split(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO));
			output.add(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_ZERO));
			output.add(DataString.EMPTY_STRING + inputStrings[DataString.INT_ONE]);
			return output;
		}else if(euclid.contains(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO))){
			String []inputStrings = inputString.split(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO));
			output.add(DataString.EMPTY_STRING + inputStrings[DataString.INT_ZERO]);
			output.add(DataString.EMPTY_STRING + inputString.charAt(DataString.INT_TWO));
			return output;
		}else {
			output.add(DataString.EMPTY_STRING + inputString);
			return output;
		}
	}
}