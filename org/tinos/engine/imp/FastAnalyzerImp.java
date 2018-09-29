package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.FastAnalyzer;
import org.tinos.fhmm.FDHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FDHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.obj.FDHMMNode;
import org.tinos.zabbi.DataString;
public class FastAnalyzerImp implements  FastAnalyzer{
	private FDHMMList fDHMMList;
	private NeroFeedHMM neroFeedHMM;
	public void init() throws IOException {
		fDHMMList = new FDHMMListImp();
		fDHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}

	public List<String> parserString(String input) {
		List<String> output = new ArrayList<>();
		Map<String, FDHMMNode> maps = fDHMMList.getMap();
		int length = input.length();
		int tempLength; 
		for(int i = DataString.INT_ZERO; i < length; i += (tempLength == DataString.INT_ZERO ? DataString.INT_ONE : tempLength)){
			String temp = DataString.EMPTY_STRING+ input.charAt(i);
			temp = neroFeedHMM.getFastRecurWord(temp, maps, input, i, length);
			output.add(temp);
			tempLength = temp.length();
		}
		return output;
	}

}

 
