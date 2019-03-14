package org.tinos.emotion.estimation;

import java.util.Map;
import org.tinos.view.obj.WordFrequency;

public interface RatioMap {
	Map<String, EmotionSample> getEmotionSampleMap(Map<Integer, WordFrequency> wordFrequencyMap
			, Map<String, Object> positive, Map<String, Object> negative);
	void getEmotionRatio(Map<String, EmotionSample> emotionSampleMap, double positiveCount
			, double negativeCount, double medCount);
	void getMotivationRatio(Map<String, EmotionSample> emotionSampleMap, double sumOfEmotion);
	void getCorrelationRatio(Map<String, EmotionSample> emotionSampleMap, double sumOfEmotion);
	void getContinusRatio(Map<String, EmotionSample> emotionSampleMap, double emotionRatio);
	
	void getTrendsRatio( Map<String, EmotionSample> emotionSampleMap);
	void getPredictionRatio(Map<String, EmotionSample> emotionSampleMap);
	void getGuessRatio(Map<String, EmotionSample> emotionSampleMap);
	void getSensingRatio(Map<String, EmotionSample> emotionSampleMap);
	double findTotalPositiveCount(Map<String, EmotionSample> emotionSampleMap);
	double findTotalNegativeCount(Map<String, EmotionSample> emotionSampleMap);
	double findTotalKeyCount(Map<String, EmotionSample> emotionSampleMap);
	void getMotivation(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> motivation);
	void getTrending(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> trending);
	void getPrediction(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> prediction);
	void getDistinction(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> distinction);
	Map<String, EmotionSample> getEnvironmentSampleMap(Map<Integer, WordFrequency> wordFrequencyMap);
}