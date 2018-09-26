package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.tinos.engine.PrettyAnalyzer;
import org.tinos.fhmm.FDHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FDHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.zabbi.DataString;
public class PrettyAnalyzerImp implements  PrettyAnalyzer{
	public FDHMMList fDHMMList;
	public NeroFeedHMM neroFeedHMM;
	
	@Override
	public void init() throws IOException {
		fDHMMList = new FDHMMListImp();
		fDHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> parserString(String input) {
		List<String> output = new ArrayList<String>();
		LinkedHashMap <Integer, LinkedHashMap> roots = fDHMMList.getRoot();
		int length=input.length();
		for(int i = DataString.INT_ZERO; i < length; i += DataString.INT_ZERO){
			String char_i = DataString.EMPTY_STRING+input.charAt(i);
			String temp = char_i ;
			temp = neroFeedHMM.getPrettyRecurWord(temp,input,i,length,roots);
			output.add(temp);
			int t_length=temp.length();
			i += (t_length == DataString.INT_ZERO ? DataString.INT_ONE : t_length);
		}
		return output;
	}

	@Override
	public void parserStringWithType(String[] types, String string) {
		// TODO Auto-generated method stub
		
	}
}

 
