package org.tinos.engine.pos;
import java.util.List;
import java.util.Map;
public abstract interface POSController{
	public int chuLiLianCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength,String[] strings, String[] prefixWord);
	public int chuLiZhuCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength,String[] strings, String[] prefixWord);
	public int chuLiJieCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength,String[] strings, String[] prefixWord); 
	public int chuLiLiangCi(Map<String, String> wordsForest, List<String> outputList
			, int countInputStringLength, String[] strings, String[] prefixWord); 
}
