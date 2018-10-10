package org.tinos.utils;
import java.util.List;
import java.util.Map;
public abstract interface EngineUtils{
	public int doSlangCheck(int countInputStringLength, List<String> output
			, String inputString, Map<String, String> words);
	public int doPOSAndEMMCheck(int countInputStringLength, List<String> output
			,  Map<String, String> wordsForest
			, String inputString);
	public int doSlangPartCheck(int countInputStringLength, List<String> outputString
			, String countWordNode
			, Map<String, String> wordsForest);
	public int chuLiLianCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength,String[] strings);
	public int chuLiJieCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength,String[] strings); 
	public int chuLiLiangCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings); 
}
