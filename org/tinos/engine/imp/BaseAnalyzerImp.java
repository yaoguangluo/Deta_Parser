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
		List<String> output = new ArrayList<String>();
		Map<String, FLHMMNode> linerForestMaps = fLHMMList.getForestMaps();
		int length = input.length();
		int tempLength; 
		for(int i = DataString.INT_ZERO; i < length; i += (tempLength == DataString.INT_ZERO ?
				DataString.INT_ONE : tempLength)){
			String temp = DataString.EMPTY_STRING + input.charAt(i);
			temp = neroFeedHMM.getFastRecurWord(temp, linerForestMaps, input, i, length);
			output.add(temp);
			tempLength = temp.length();
		}
		return output;
	}

	public Map<String, String> getWord() throws IOException {
		// TODO Auto-generated method stub
		return fLHMMList.getWords();
	}

}

 
