package org.tinos.engine.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.CogsBinaryForestAnalyzer;
import org.tinos.fhmm.FHMMList;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.fhmm.imp.FMHMMListImp;
import org.tinos.fhmm.imp.NeroFeedHMMImp;
import org.tinos.utils.EngineUtils;
import org.tinos.utils.imp.EngineUtilsImp;
import org.tinos.zabbi.DataString;
public class CogsBinaryForestAnalyzerImp implements  CogsBinaryForestAnalyzer{
	private FHMMList fHMMList;
	private NeroFeedHMM neroFeedHMM;
	private EngineUtils engineUtils;
	@Override
	public void init() throws IOException {
		this.fHMMList = new FMHMMListImp();
		fHMMList.index();
		neroFeedHMM = new NeroFeedHMMImp(); 
		engineUtils = new EngineUtilsImp();
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> parserString(String inputString) {
		Map <String, Integer> wordsForest = fHMMList.getWords();
		String linerEuclid = fHMMList.getEuclid();
		List<String> outputString = new ArrayList<>();
		Map <Integer, Map> forestRoots = fHMMList.getRoot();
		int inputStringLength = inputString.length();
		int forestDepth = DataString.INT_ZERO;
		int tempInputStringLength;
		for(int charPosition = DataString.INT_ZERO; charPosition < inputStringLength; charPosition += 
				(tempInputStringLength == DataString.INT_ZERO ? DataString.INT_ONE : tempInputStringLength)){
			String tempWordNode = DataString.EMPTY_STRING + inputString.charAt(charPosition);
			tempWordNode = neroFeedHMM.getBinaryForestRecurWord(tempWordNode, inputString, charPosition, 
					inputStringLength, forestRoots, forestDepth);
			if(tempWordNode.length() == DataString.INT_ONE){
				outputString.add(tempWordNode);
			}else if(tempWordNode.length() == DataString.INT_TWO){
				outputString.add(tempWordNode);
			}else if(tempWordNode.length() == DataString.INT_THREE) {
				outputString = engineUtils.doEuclidCheck(outputString, linerEuclid, tempWordNode);
			}else if(tempWordNode.length() == DataString.INT_FOUR){
				outputString = engineUtils.doSlangCheck(outputString, wordsForest, tempWordNode);
			}else{
				for(int tempWordNodeCharPosition = DataString.INT_ZERO; tempWordNodeCharPosition < tempWordNode.length();
						tempWordNodeCharPosition++) {
					outputString.add(DataString.EMPTY_STRING + tempWordNode.charAt(tempWordNodeCharPosition));
				}
			}			
			tempInputStringLength = tempWordNode.length();
		}
		return outputString;
	}
}

 
