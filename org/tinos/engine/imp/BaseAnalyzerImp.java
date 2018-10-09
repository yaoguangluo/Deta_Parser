package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.BaseAnalyzer;
import org.tinos.fhmm.FLHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FLHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.obj.FLHMMNode;
import org.tinos.zabbi.DataString;
public class BaseAnalyzerImp implements  BaseAnalyzer{
	private FLHMMList fLHMMList;
	private NeroFeedHMM neroFeedHMM;
	public void init() throws IOException {
		fLHMMList = new FLHMMListImp();
		fLHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}

	public List<String> parserString(String input) {
		List<String> output = new ArrayList<>();
		Map<String, FLHMMNode> linerForestMaps = fLHMMList.getForestMaps();
		int length = input.length();
		int countLength; 
		for(int i = DataString.INT_ZERO; i < length; i += (countLength == DataString.INT_ZERO ?
				DataString.INT_ONE : countLength)){
			String count = DataString.EMPTY_STRING + input.charAt(i);
			count = neroFeedHMM.getFastRecurWord(count, linerForestMaps, input, i, length);
			output.add(count);
			countLength = count.length();
		}
		return output;
	}

	public Map<String, String> getWord() throws IOException {
		return fLHMMList.getWords();
	}

}

 
