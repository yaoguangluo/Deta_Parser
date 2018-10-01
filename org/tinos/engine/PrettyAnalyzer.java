package org.tinos.engine;
import java.util.List;
import java.util.Map;

import org.tinos.zabbi.DataString;
public abstract interface PrettyAnalyzer extends BaseAnalyzer{
	@SuppressWarnings(DataString.RAW_TYPES)
	public List<String> kerner(List<String> output, int depth, int length, Map<Integer, Map> roots, Map<String, Integer> words,
			String euclid, String input);
}