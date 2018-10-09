package org.tinos.utils;
import java.util.List;
import java.util.Map;
public abstract interface EngineUtils{
	public List<String> doSlangCheck(List<String> output, Map<String, String> words, String inputString);
	public List<String> doEuclidCheck(List<String> output, String euclid, String inputString);
	public List<String> doPOSAndEMMCheck(List<String> output, String euclid,  Map<String, String> wordsForest, 
			String inputString);
}
