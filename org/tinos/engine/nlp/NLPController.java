package org.tinos.engine.nlp;
import java.util.List;
import java.util.Map;

import org.tinos.engine.pos.POSController;
public abstract interface NLPController{
	public int doSlangCheck(int countInputStringLength, List<String> output, String inputString
			, Map<String, String> words, String[] prefixWord, POSController posUtils);
	public int doPOSAndEMMCheck(int countInputStringLength, List<String> output, Map<String, String> wordsForest
			, String inputString, String[] prefixWord, POSController posUtils);
	public int doSlangPartCheck(int countInputStringLength, List<String> outputString, String countWordNode
			, Map<String, String> wordsForest, String[] prefixWord);
}
