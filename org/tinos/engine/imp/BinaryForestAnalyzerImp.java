package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.tinos.engine.BinaryForestAnalyzer;
import org.tinos.fhmm.FHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FFHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.zabbi.DataString;
public class BinaryForestAnalyzerImp implements  BinaryForestAnalyzer{
	public FHMMList fHMMList;
	public NeroFeedHMM neroFeedHMM;
	
	@Override
	public void init() throws IOException {
		this.fHMMList = new FFHMMListImp();
		fHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> parserString(String input) {
		LinkedHashMap <String, Integer> words = fHMMList.getWords();
		String euclid = fHMMList.getEuclid();
		List<String> output = new ArrayList<String>();
		LinkedHashMap <Integer, LinkedHashMap> roots = fHMMList.getRoot();
		int length = input.length();
		int depth = 0;
		for(int i = DataString.INT_ZERO; i < length; i += DataString.INT_ZERO){
			String char_i = DataString.EMPTY_STRING + input.charAt(i);
			String temp = char_i ;
			temp = neroFeedHMM.getBinaryForestRecurWord(temp,input,i,length,roots, depth);
			if(temp.length() == DataString.INT_THREE) {
					if(euclid.contains(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO))){
						String temps[] = temp.split(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO));
						output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO));
						output.add(DataString.EMPTY_STRING + temps[DataString.INT_ONE]);
					}else if(euclid.contains(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO))){
						String temps[] = temp.split(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO));
						output.add(DataString.EMPTY_STRING + temps[DataString.INT_ZERO]);
						output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO));
					}else {
						output.add(DataString.EMPTY_STRING + temp);
					}
			}else if(temp.length() == DataString.INT_FOUR) {
				if(words.containsKey(temp)){
					output.add(temp);
				}else {
					output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO) + temp.charAt(DataString.INT_ONE));
					output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO) + temp.charAt(DataString.INT_THREE));
				}
			}else if(temp.length() == DataString.INT_TWO){
				output.add(temp);
			}else{
				for(int j = DataString.INT_ZERO; j < temp.length(); j++) {
					output.add(DataString.EMPTY_STRING + temp.charAt(j));
				}
			}			
			int t_length = temp.length();
			i += (t_length == DataString.INT_ZERO ? DataString.INT_ONE : t_length);
		}
		return output;
	}
}

 
