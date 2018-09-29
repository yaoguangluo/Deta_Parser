package org.tinos.utils;
import java.util.List;
import java.util.Map;
public interface EngineUtils{
	public List<String> doSlangCheck(List<String> output, Map<String, Integer> words, String temp);
	public List<String> doEuclidCheck(List<String> output, String euclid, String temp);
}
