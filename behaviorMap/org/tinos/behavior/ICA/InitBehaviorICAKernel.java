package org.tinos.behavior.ICA;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.tinos.emotion.engine.EmotionInit;
import org.tinos.emotion.engine.EnvironmentInit;
import org.tinos.emotion.estimation.EmotionSample;

public class InitBehaviorICAKernel{
	private double[] kernel;
	
	public double getTrustRate(String text) throws IOException {
		EmotionInit emotionInitEnvironment = new EmotionInit();
		emotionInitEnvironment.init(text);
		//reduce
		double positiveCountEnvironment = emotionInitEnvironment.getPositiveCount();
		double negativeCountEnvironment = emotionInitEnvironment.getNegativeCount();
		double totalCountEnvironment = emotionInitEnvironment.getTotalCount();
		if(positiveCountEnvironment == 0) {
			positiveCountEnvironment = 1;
		}
		if(negativeCountEnvironment == 0) {
			negativeCountEnvironment = 1;
		}
		return positiveCountEnvironment/totalCountEnvironment;
	}
	
	public double[] getBehaviorICAKernel(String text) throws IOException {
		kernel = new double[7];	
		EmotionInit emotionInit = new EmotionInit();
		emotionInit.init(text);
		//reduce
		double positiveCount = emotionInit.getPositiveCount();
		double negativeCount = emotionInit.getNegativeCount();
		double totalCount = emotionInit.getTotalCount();
		System.out.println("正面情感：" + positiveCount);
		System.out.println("负面情感：" + negativeCount);
		if(positiveCount == 0) {
			positiveCount = 1;
		}
		if(negativeCount == 0) {
			negativeCount = 1;
		}
		double adjRatio = Math.abs(positiveCount/negativeCount-negativeCount/positiveCount);
		System.out.println("渲染比率：" + adjRatio);
		double phychologicRatio = (positiveCount + negativeCount)/totalCount;
		System.out.println("情绪比率：" + phychologicRatio);
		double infectionRatio = adjRatio*phychologicRatio;
		System.out.println("感染比率：" + infectionRatio);		
		kernel[0] = adjRatio;
		kernel[1] = phychologicRatio;
		kernel[2] = infectionRatio;
		//
		EnvironmentInit environmentInit = new EnvironmentInit();
		environmentInit.initFromEmotion(emotionInit.getWordFrequencyMap());
		Map<String, EmotionSample> environmentSampleMap = environmentInit.getEmotionSampleMap();
		//reduce
		System.out.println("观测角度：");
		String environmentText = "";
		Iterator<String> Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getDistinction()) {
				environmentText += emotionSample.getDistinction() + " ";
			}
		}
		System.out.println(environmentText);
		kernel[3] = getTrustRate(environmentText);
		System.out.println(kernel[3]);

		System.out.println("");
		System.out.println("信任比率：");
		String motivationText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getMotivation()) {
				motivationText += emotionSample.getMotivation() + " ";
			}
		}
		System.out.println(motivationText);
		kernel[4] = getTrustRate(motivationText);
		System.out.println(kernel[4]);
		
		System.out.println("");
		System.out.println("执行比率：" );
		String trendingText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getTrending()) {
				trendingText += emotionSample.getTrending() + " ";
			}
		}
		System.out.println(trendingText);
		kernel[5] = getTrustRate(trendingText);
		System.out.println(kernel[5]);
		
		//reduce
		System.out.println("");
		System.out.println("成功比率：");
		String predictionText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getPrediction()) {
				predictionText += emotionSample.getPrediction() + " ";
			}
		}
		System.out.println(predictionText);
		kernel[6] = getTrustRate(predictionText);
		System.out.println(kernel[6]);
		return kernel;
	}
}