package org.tinos.engin.utils;
import java.util.Map;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
public class WordForestUtil {
	public static void wordsForestNotContainsKey(Map<String, WordFrequency> outputList
			, String countWordNode, StringBuilder[] prefixWord) {
		String string= String.valueOf(countWordNode.charAt(StableData.INT_ZERO));
		if (outputList.containsKey(string)) {
			WordFrequency wordFrequency = outputList.get(string);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(string, wordFrequency);
			prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
			prefixWord[StableData.INT_ZERO].append(countWordNode.charAt(StableData.INT_ZERO));
			return;
		} 
		WordFrequency wordFrequency = new WordFrequency();
		wordFrequency.setFrequency(StableData.INT_ONE);
		wordFrequency.setWord(string);
		outputList.put(string, wordFrequency);
		prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
		prefixWord[StableData.INT_ZERO].append(countWordNode.charAt(StableData.INT_ZERO));
	}

	public static void prefixWordEqualZero(Map<String, WordFrequency> outputList,String countWordNode
			, StringBuilder[] prefixWord) {
		prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
		prefixWord[StableData.INT_ZERO].append(countWordNode);
		if (outputList.containsKey(countWordNode)) {
			WordFrequency wordFrequency = outputList.get(countWordNode);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(countWordNode, wordFrequency);
			return;
		}
		WordFrequency wordFrequency = new WordFrequency();
		wordFrequency.setFrequency(StableData.INT_ONE);
		wordFrequency.setWord(countWordNode);
		outputList.put(countWordNode, wordFrequency);
	}

	public static void wordsForestContainsKey(Map<String, WordFrequency> outputList,String countWordNode
			, StringBuilder[] prefixWord) {
		prefixWord[StableData.INT_ZERO].delete(StableData.INT_ZERO, prefixWord[StableData.INT_ZERO].length());
		prefixWord[StableData.INT_ZERO].append(countWordNode);
		if (outputList.containsKey(countWordNode)) {
			WordFrequency wordFrequency = outputList.get(countWordNode);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(countWordNode, wordFrequency);
			return;
		} 
		WordFrequency wordFrequency = new WordFrequency();
		wordFrequency.setFrequency(StableData.INT_ONE);
		wordFrequency.setWord(countWordNode);
		outputList.put(countWordNode, wordFrequency);
	}
}