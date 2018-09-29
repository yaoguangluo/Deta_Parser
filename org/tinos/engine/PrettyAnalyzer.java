package org.tinos.engine;
import java.util.List;
import java.util.Map;
public interface PrettyAnalyzer extends Analyzer{
	@SuppressWarnings("rawtypes")
	public List<String> kerner(List<String> output, int depth, int length, Map<Integer, Map> roots, Map<String, Integer> words,
			String euclid, String input);
}