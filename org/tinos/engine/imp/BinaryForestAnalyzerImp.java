package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.BinaryForestAnalyzer;
import org.tinos.fhmm.FHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FHHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.utils.EngineUtils;
import org.tinos.utils.imp.EngineUtilsImp;
import org.tinos.zabbi.DataString;
public class BinaryForestAnalyzerImp implements  BinaryForestAnalyzer{
	private FHMMList fHMMList;
	private NeroFeedHMM neroFeedHMM;
	private EngineUtils engineUtils;
	
	public void init() throws IOException {
		this.fHMMList = new FHHMMListImp();
		fHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
		engineUtils = new EngineUtilsImp();
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> parserString(String input) {
		Map <String, String> words = fHMMList.getWords();
		String euclid = fHMMList.getEuclid();
		List<String> output = new ArrayList<>();
		Map <Integer, Map> roots = fHMMList.getRoot();
		int length = input.length();
		int depth = DataString.INT_ZERO;
		int countLength;
		for(int i = DataString.INT_ZERO; i < length; i += (countLength == DataString.INT_ZERO ? 
				DataString.INT_ONE : countLength)){
			String count = DataString.EMPTY_STRING + input.charAt(i);
			count = neroFeedHMM.getBinaryForestRecurWord(count, input, i, length, roots, depth);
			if(count.length() == DataString.INT_ONE){
				output.add(count);
			}else if(count.length() == DataString.INT_TWO){
				output.add(count);
			}else if(count.length() == DataString.INT_THREE) {
				output = engineUtils.doEuclidCheck(output, euclid, count);
			}else if(count.length() == DataString.INT_FOUR){
				output = engineUtils.doSlangCheck(output,words,count);
			}else{
				for(int j = DataString.INT_ZERO; j < count.length(); j++) {
					output.add(DataString.EMPTY_STRING + count.charAt(j));
				}
			}			
			countLength = count.length();
		}
		return output;
	}

	public Map<String, String> getWord() throws IOException {
		return fHMMList.getWords();
	}
}

 
