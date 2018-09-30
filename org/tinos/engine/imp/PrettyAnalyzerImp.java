package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.PrettyAnalyzer;
import org.tinos.fhmm.FHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FDHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.utils.EngineUtils;
import org.tinos.utils.imp.EngineUtilsImp;
import org.tinos.zabbi.DataString;
public class PrettyAnalyzerImp implements  PrettyAnalyzer{
	private FHMMList fDHMMList;
	private NeroFeedHMM neroFeedHMM;
	
	@Override
	public void init() throws IOException {
		fDHMMList = new FDHMMListImp();
		fDHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> parserString(String input) {
		Map <String, Integer> words = fDHMMList.getWords();
		String euclid = fDHMMList.getEuclid();
		List<String> output = new ArrayList<>();
		Map <Integer, Map> roots = fDHMMList.getRoot();
		int length = input.length();
		int depth = DataString.INT_ZERO;
		output= kerner(output, depth, length, roots, words, euclid, input); 
		return output;
	}

	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> kerner(List<String> output, int depth, int length, Map<Integer, Map> roots, Map<String, Integer> words,
			String euclid, String input) {
		EngineUtils engineUtils = new EngineUtilsImp();
		int tempLength;
		for(int i = DataString.INT_ZERO; i < length; i += (tempLength == DataString.INT_ZERO ? DataString.INT_ONE : tempLength)){
			String charPosition = DataString.EMPTY_STRING + input.charAt(i);
			String temp = charPosition;
			temp = neroFeedHMM.getPrettyRecurWord(temp, input, i, length, roots, depth);
			if(temp.length() == DataString.INT_THREE) {
				output = engineUtils.doEuclidCheck(output,euclid,temp);
			}else if(temp.length() == DataString.INT_FOUR) {
				output = engineUtils.doSlangCheck(output,words,temp);
			}else if(temp.length() == DataString.INT_TWO){ 
				output.add(temp);
			}else{
				for(int j = DataString.INT_ZERO; j < temp.length(); j++) { 
					output.add(DataString.EMPTY_STRING + temp.charAt(j));
				}
			}			
			tempLength = temp.length();
		}
		return output;
	}	
}

 
