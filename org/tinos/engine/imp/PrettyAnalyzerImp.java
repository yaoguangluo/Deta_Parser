package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.PrettyAnalyzer;
import org.tinos.fhmm.FHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FLHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.utils.EngineUtils;
import org.tinos.utils.imp.EngineUtilsImp;
import org.tinos.zabbi.DataString;
public class PrettyAnalyzerImp implements  PrettyAnalyzer{
	private FHMMList fLHMMList;
	private NeroFeedHMM neroFeedHMM;
	
	public void init() throws IOException {
		fLHMMList = new FLHMMListImp();
		fLHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> parserString(String input) {
		Map <String, String> words = fLHMMList.getWords();
		String euclid = fLHMMList.getEuclid();
		List<String> output = new ArrayList<String>();
		Map <Integer, Map> roots = fLHMMList.getRoot();
		int length = input.length();
		int depth = DataString.INT_ZERO;
		output= kerner(output, depth, length, roots, words, euclid, input); 
		return output;
	}

	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> kerner(List<String> output, int depth, int length, Map<Integer, Map> roots,
			Map<String, String> words, String euclid, String input) {
		EngineUtils engineUtils = new EngineUtilsImp();
		int inputStringLength;
		for(int i = DataString.INT_ZERO; i < length; i += (inputStringLength == DataString.INT_ZERO ?
				DataString.INT_ONE : inputStringLength)){
			String charPosition = DataString.EMPTY_STRING + input.charAt(i);
			String charPositionforCaculation = charPosition;
			charPositionforCaculation = neroFeedHMM.getPrettyRecurWord(charPositionforCaculation, input, i, length, roots, depth);
			inputStringLength = charPositionforCaculation.length();
			if(charPositionforCaculation.length() == DataString.INT_THREE) {
				output = engineUtils.doEuclidCheck(output, euclid, charPositionforCaculation);
			}else if(charPositionforCaculation.length() == DataString.INT_FOUR) {
				output = engineUtils.doSlangCheck(output, words, charPositionforCaculation);
			}else if(charPositionforCaculation.length() == DataString.INT_TWO){ 
				output.add(charPositionforCaculation);
			}else{
				for(int j = DataString.INT_ZERO; j < charPositionforCaculation.length(); j++) { 
					output.add(DataString.EMPTY_STRING + charPositionforCaculation.charAt(j));
				}
			}			
		}
		return output;
	}

	public Map<String, String> getWord() throws IOException {
		return fLHMMList.getWords();
	}	
}

 
