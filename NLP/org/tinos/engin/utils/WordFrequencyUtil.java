package org.tinos.engin.utils;

import java.util.Map;

import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;

public class WordFrequencyUtil {
	public static void WordFrequencyFindCheck(Map<String, WordFrequency> outputList, StringBuilder[] fixWords) {
		if (outputList.containsKey(fixWords[StableData.INT_ZERO].toString())) {
			WordFrequency wordFrequency = outputList.get(fixWords[StableData.INT_ZERO].toString());
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(fixWords[StableData.INT_ZERO].toString(), wordFrequency);
		} else {
			WordFrequency wordFrequency = new WordFrequency();
			wordFrequency.setFrequency(StableData.INT_ONE);
			wordFrequency.setWord(fixWords[StableData.INT_ZERO].toString());
			outputList.put(fixWords[StableData.INT_ZERO].toString(), wordFrequency);
		}
	}
	
	public static void WordFrequencyCompareCheck(Map<String, WordFrequency> outputList, StringBuilder[] fixWords,
			String countWordNode) {
		if (outputList.containsKey(countWordNode)) {
			WordFrequency wordFrequency = outputList.get(countWordNode);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(countWordNode, wordFrequency);
		} else {
			WordFrequency wordFrequency = new WordFrequency();
			wordFrequency.setFrequency(StableData.INT_ONE);
			wordFrequency.setWord(fixWords[StableData.INT_ZERO].toString());
			outputList.put(countWordNode, wordFrequency);
		}
	}
}