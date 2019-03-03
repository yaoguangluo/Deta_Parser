package org.tinos.engine.analysis;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.tinos.view.obj.WordFrequency;
public interface Analyzer {
	void init() throws IOException;
	List<String> parserString(String input);
	void addFixWords(int charPosition, String inputString, StringBuilder[] fixWords);
	Map<String, WordFrequency> getWordFrequencyMap(List<String> sets) throws IOException;
	List<WordFrequency> sortWordFrequencyMap(Map<String, WordFrequency> map) throws IOException;
	List<WordFrequency> getWordFrequency(List<String> sets)throws IOException;
	Map<Integer, WordFrequency> getWordFrequencyByReturnSortMap(List<String> sets) throws IOException;
	Map<Integer, WordFrequency> sortWordFrequencyMapToUnsortMap(Map<String, WordFrequency> map);
	Map<Integer, WordFrequency> sortWordFrequencyMapToSortMap(Map<String, WordFrequency> map);
	Map<String, WordFrequency> parserStringByReturnFrequencyMap(String inputString);
	Map<String, String> getPosEnToCn();
	Map<String, String> getPosEnToEn();
	Map<String, String> getPosCnToCn();
	Map<String, String> getEnToCn();
	Map<String, String> getCnToEn();
	Map<String, String> getFullEnToCn();
	Map<String, String> getFullCnToEn();
	String[] parserEnglishString(String englishString);
	List<String> parserMixedString(String mixedString);
	Map<String, WordFrequency> parserMixStringByReturnFrequencyMap(String key);
}