package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.tinos.engine.FastAnalyzer;
import org.tinos.fhmm.FDHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FDHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.obj.FDHMMNode;
import org.tinos.zabbi.DataString;;
public class FastAnalyzerImp implements  FastAnalyzer{
	public FDHMMList fDHMMList;
	public NeroFeedHMM neroFeedHMM;
	public void init() throws IOException {
		fDHMMList = new FDHMMListImp();
		fDHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}

	public List<String> parserString(String input) {
		List<String> output = new ArrayList<String>();
		LinkedHashMap<String, FDHMMNode> maps = fDHMMList.getMap();
		int length = input.length();
		for(int i = DataString.INT_ZERO; i < length; i+=DataString.INT_ZERO){
			String char_i = DataString.EMPTY_STRING+ input.charAt(i);
			String temp = char_i ;
			temp = neroFeedHMM.getFastRecurWord(temp,maps,input,i,length);
			output.add(temp);
			int t_length = temp.length();
			i += (t_length == DataString.INT_ZERO ? DataString.INT_ONE : t_length);
		}
		return output;
	}

	public void parserStringWithType(String[] types, String string) {

	}
}

 
 
