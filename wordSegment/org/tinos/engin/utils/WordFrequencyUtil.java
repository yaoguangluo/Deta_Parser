package org.tinos.engin.utils;

import java.util.Map;

import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;

public class WordFrequencyUtil {
	public static void WordFrequencyFindCheck(Map<String, WordFrequency> outputList,StringBuilder[] fixWords) {
		String string= fixWords[StableData.INT_ZERO].toString();
		if (outputList.containsKey(string)) {
			WordFrequency wordFrequency= outputList.get(string);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(string, wordFrequency);
			return;
		}
		WordFrequency wordFrequency= new WordFrequency();
		wordFrequency.setFrequency(StableData.INT_ONE);
		wordFrequency.setWord(string);
		outputList.put(string, wordFrequency);
	}

	public static void WordFrequencyCompareCheck(Map<String, WordFrequency> outputList , StringBuilder[] fixWords,
			String countWordNode) {
		if (outputList.containsKey(countWordNode)) {
			WordFrequency wordFrequency=outputList.get(countWordNode);
			wordFrequency.setFrequency(wordFrequency.getFrequency() + StableData.INT_ONE);
			outputList.put(countWordNode, wordFrequency);
			return;
		}
		WordFrequency wordFrequency=new WordFrequency();
		wordFrequency.setFrequency(StableData.INT_ONE);
		wordFrequency.setWord(fixWords[StableData.INT_ZERO].toString());
		outputList.put(countWordNode,wordFrequency);
	}
}