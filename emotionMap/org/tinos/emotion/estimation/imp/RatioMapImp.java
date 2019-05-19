package org.tinos.emotion.estimation.imp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.tinos.emotion.estimation.EmotionSample;
import org.tinos.emotion.estimation.RatioMap;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;

public class RatioMapImp implements RatioMap{

	@Override
	public Map<String, EmotionSample> getEmotionSampleMap(Map<Integer, WordFrequency> wordFrequencyMap,
			Map<String, Object> positive, Map<String, Object> negative) {
		Map<String, EmotionSample> output = new HashMap<>();
		for(int i = wordFrequencyMap.size() - StableData.INT_ONE; i >= StableData.INT_ZERO; i--) {
			if(wordFrequencyMap.get(i).getWord().length() > StableData.INT_ONE) {
				EmotionSample emotionSample;
				if(output.containsKey(wordFrequencyMap.get(i).getWord())) {
					emotionSample = output.get(wordFrequencyMap.get(i).getWord());
				}else {
					emotionSample = new EmotionSample();
				}
				if(positive.containsKey(wordFrequencyMap.get(i).getWord())) {
					emotionSample.setPositiveCount(wordFrequencyMap.get(i).getFrequency());
				}else if(negative.containsKey(wordFrequencyMap.get(i).getWord())) {
					emotionSample.setNegativeCount(wordFrequencyMap.get(i).getFrequency());
				}else {
					emotionSample.setMedCount(wordFrequencyMap.get(i).getFrequency());
				}
				output.put(wordFrequencyMap.get(i).getWord(), emotionSample);
			}
		}
		return output;
	}

	@Override
	public void getMotivationRatio(Map<String, EmotionSample> emotionSampleMap, double sumOfEmotion) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			emotionSample.setMotivationRatio(emotionSample.getEmotionRatio()/sumOfEmotion);
			emotionSampleMap.put(word, emotionSample);
		}

	}

	@Override
	public void getCorrelationRatio(Map<String, EmotionSample> emotionSampleMap, double sumOfEmotion) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			emotionSample.setCorrelationRatio((emotionSample.getPositiveCount()
					+ emotionSample.getNegativeCount() + emotionSample.getMedCount())/sumOfEmotion);
			emotionSampleMap.put(word, emotionSample);
		}
	}

	@Override
	public void getContinusRatio(Map<String, EmotionSample> emotionSampleMap, double emotionRatio) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			emotionSample.setContinusRatio((emotionSample.getPositiveCount()
					+ emotionSample.getNegativeCount() + emotionSample.getMedCount()) * emotionRatio);
			emotionSampleMap.put(word, emotionSample);
		}
	}

	@Override
	public void getTrendsRatio(Map<String, EmotionSample> emotionSampleMap) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			emotionSample.setTrendsRatio(emotionSample.getEmotionRatio() * emotionSample.getContinusRatio() 
					* emotionSample.getCorrelationRatio());
			emotionSampleMap.put(word, emotionSample);
		}

	}

	@Override
	public void getPredictionRatio(Map<String, EmotionSample> emotionSampleMap) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			emotionSample.setPredictionRatio(emotionSample.getMotivationRatio()*emotionSample.getCorrelationRatio());
			emotionSampleMap.put(word, emotionSample);
		}

	}

	@Override
	public void getGuessRatio(Map<String, EmotionSample> emotionSampleMap) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			emotionSample.setGuessRatio(emotionSample.getPredictionRatio()*emotionSample.getTrendsRatio());
			emotionSampleMap.put(word, emotionSample);
		}
	}

	@Override
	public void getSensingRatio(Map<String, EmotionSample> emotionSampleMap) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(0==emotionSample.getTrendsRatio()) {
				emotionSample.setSensingRatio(0);
			}else {
				emotionSample.setSensingRatio(emotionSample.getPredictionRatio()/emotionSample.getTrendsRatio());
			}
			emotionSampleMap.put(word, emotionSample);
		}
	}

	@Override
	public double findTotalPositiveCount(Map<String, EmotionSample> emotionSampleMap) {
		double output = StableData.INT_ONE;
		Iterator<String> Iterator=emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			output += emotionSample.getPositiveCount();
		}
		return output;
	}

	@Override
	public double findTotalNegativeCount(Map<String, EmotionSample> emotionSampleMap) {
		double output = StableData.INT_ONE;
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			output += emotionSample.getNegativeCount();
		}
		return output;
	}

	@Override
	public void getEmotionRatio(Map<String, EmotionSample> emotionSampleMap, double positiveCount,
			double negativeCount, double medCount) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			double negRate = emotionSample.getNegativeCount()/negativeCount;
			double posRate = emotionSample.getPositiveCount()/positiveCount;
			double medRate = emotionSample.getMedCount()/medCount;
			emotionSample.setEmotionRatio(negRate + posRate + medRate);
			emotionSampleMap.put(word, emotionSample);
		}
	}

	@Override
	public double findTotalKeyCount(Map<String, EmotionSample> emotionSampleMap) {
		double output = StableData.INT_ONE;
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			output += emotionSample.getNegativeCount() + emotionSample.getPositiveCount()
			+ emotionSample.getMedCount();
		}
		return output;
	}

	@Override
	public void getMotivation(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> motivation) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(motivation.containsKey(word)) {
				emotionSample.setMotivation(motivation.get(word).toString());
			}  
			emotionSampleMap.put(word, emotionSample);
		}	
	}

	@Override
	public void getTrending(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> trending) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(trending.containsKey(word)) {
				emotionSample.setTrending(trending.get(word).toString());
			}  
			emotionSampleMap.put(word, emotionSample);
		}		
	}

	@Override
	public void getPrediction(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> prediction) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(prediction.containsKey(emotionSample.getTrending())) {
				emotionSample.setPrediction(prediction.get(emotionSample.getTrending()).toString());
			} else if(prediction.containsKey(emotionSample.getMotivation())) {
				emotionSample.setPrediction(prediction.get(emotionSample.getMotivation()).toString());
			} 
			emotionSampleMap.put(word, emotionSample);
		}	
	}

	@Override
	public void getDistinction(Map<String, EmotionSample> emotionSampleMap, Map<String, Object> distinction) {
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(distinction.containsKey(word)) {
				emotionSample.setDistinction(distinction.get(word).toString());
			}  
			emotionSampleMap.put(word, emotionSample);
		}	
	}

	@Override
	public Map<String, EmotionSample> getEnvironmentSampleMap(Map<Integer, WordFrequency> wordFrequencyMap) {
		Map<String, EmotionSample> output = new HashMap<>();
		for (int i = wordFrequencyMap.size() - StableData.INT_ONE; i >= StableData.INT_ZERO; i--) {
			if(wordFrequencyMap.get(i).getWord().length() > StableData.INT_ONE) {
				EmotionSample emotionSample = new EmotionSample();
				if(!output.containsKey(wordFrequencyMap.get(i).getWord())) {
					output.put(wordFrequencyMap.get(i).getWord(), emotionSample);
				}
			}
		}
		return output;
	}
}
