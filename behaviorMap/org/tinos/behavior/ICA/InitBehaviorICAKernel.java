package org.tinos.behavior.ICA;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.tinos.emotion.engine.EmotionInit;
import org.tinos.emotion.engine.EnvironmentInit;
import org.tinos.emotion.estimation.EmotionSample;
import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.view.stable.StableData;

public class InitBehaviorICAKernel{
	private double[] kernel;
	public double[] getKernel() {
		return kernel;
	}

	public void setKernel(double[] kernel) {
		this.kernel = kernel;
	}

	public List<String> getForRestReturn() {
		return forRestReturn;
	}

	public void setForRestReturn(List<String> forRestReturn) {
		this.forRestReturn = forRestReturn;
	}

	private List<String> forRestReturn;
	public double getTrustRate(String text) throws IOException {
		EmotionInit emotionInitEnvironment = new EmotionInit();
		emotionInitEnvironment.init(text);
		//reduce
		double positiveCountEnvironment = emotionInitEnvironment.getPositiveCount();
		double negativeCountEnvironment = emotionInitEnvironment.getNegativeCount();
		double totalCountEnvironment = emotionInitEnvironment.getTotalCount();
		if(positiveCountEnvironment == StableData.INT_ZERO) {
			positiveCountEnvironment = StableData.INT_ONE;
		}
		if(negativeCountEnvironment == StableData.INT_ZERO) {
			negativeCountEnvironment = StableData.INT_ONE;
		}
		return positiveCountEnvironment/totalCountEnvironment;
	}
	
	public double getTrustRate(String text, Analyzer analyzer, EmotionMap emotionMap) throws IOException {
		EmotionInit emotionInitEnvironment = new EmotionInit();
		emotionInitEnvironment.initExcludeAnalyzer(text, analyzer, emotionMap);
		//reduce
		double positiveCountEnvironment = emotionInitEnvironment.getPositiveCount();
		double negativeCountEnvironment = emotionInitEnvironment.getNegativeCount();
		double totalCountEnvironment = emotionInitEnvironment.getTotalCount();
		if(positiveCountEnvironment == StableData.INT_ZERO) {
			positiveCountEnvironment = StableData.INT_ONE;
		}
		if(negativeCountEnvironment == StableData.INT_ZERO) {
			negativeCountEnvironment = StableData.INT_ONE;
		}
		return positiveCountEnvironment/totalCountEnvironment;
	}
	
	public double[] getBehaviorICAKernel(String text) throws IOException {
		forRestReturn = new LinkedList<>();
		kernel = new double[StableData.INT_SEVEN];	
		EmotionInit emotionInit = new EmotionInit();
		emotionInit.init(text);
		//reduce
		double positiveCount = emotionInit.getPositiveCount();
		double negativeCount = emotionInit.getNegativeCount();
		double totalCount = emotionInit.getTotalCount();
		//System.out.println("正面情感：" + positiveCount);
		forRestReturn.add("正面情感：" + positiveCount);
		
//		System.out.println("负面情感：" + negativeCount);
		forRestReturn.add("负面情感：" + negativeCount);
		if(positiveCount == StableData.INT_ZERO) {
			positiveCount = StableData.INT_ONE;
		}
		if(negativeCount == StableData.INT_ZERO) {
			negativeCount = StableData.INT_ONE;
		}
		double adjRatio = Math.abs(positiveCount/negativeCount-negativeCount/positiveCount);
//		System.out.println("渲染比率：" + adjRatio);
		forRestReturn.add("渲染比率：" + adjRatio);
		double phychologicRatio = (positiveCount + negativeCount)/totalCount;
//		System.out.println("情绪比率：" + phychologicRatio);
		forRestReturn.add("情绪比率：" + phychologicRatio);
		double infectionRatio = adjRatio*phychologicRatio;
//		System.out.println("感染比率：" + infectionRatio);	
		forRestReturn.add("感染比率：" + infectionRatio);
		kernel[StableData.INT_ZERO] = adjRatio;
		kernel[StableData.INT_ONE] = phychologicRatio;
		kernel[StableData.INT_TWO] = infectionRatio;
		//
		EnvironmentInit environmentInit = new EnvironmentInit();
		environmentInit.initFromEmotion(emotionInit.getWordFrequencyMap());
		Map<String, EmotionSample> environmentSampleMap = environmentInit.getEmotionSampleMap();
		//reduce
//		System.out.println("观测角度：");
		forRestReturn.add("观测角度：");
		String environmentText = "";
		Iterator<String> Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getDistinction()) {
				environmentText += emotionSample.getDistinction() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(environmentText);
		forRestReturn.add(environmentText);
		kernel[3] = getTrustRate(environmentText);
//		System.out.println(kernel[StableData.INT_THREE]);
		forRestReturn.add(""+kernel[StableData.INT_THREE]);
//		System.out.println("");
//		System.out.println("信任比率：");
		forRestReturn.add("信任比率：");
		String motivationText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getMotivation()) {
				motivationText += emotionSample.getMotivation() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(motivationText);
		forRestReturn.add(motivationText);
		kernel[4] = getTrustRate(motivationText);
//		System.out.println(kernel[StableData.INT_FOUR]);
		forRestReturn.add(""+kernel[StableData.INT_FOUR]);
//		System.out.println("");
//		System.out.println("执行比率：" );
		forRestReturn.add("执行比率：");
		String trendingText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getTrending()) {
				trendingText += emotionSample.getTrending() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(trendingText);
		forRestReturn.add(trendingText);
		kernel[5] = getTrustRate(trendingText);
//		System.out.println(kernel[StableData.INT_FIVE]);
		forRestReturn.add(""+kernel[StableData.INT_FIVE]);
		//reduce
//		System.out.println("");
//		System.out.println("成功比率：");
		forRestReturn.add("成功比率：");
		String predictionText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getPrediction()) {
				predictionText += emotionSample.getPrediction() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(predictionText);
		forRestReturn.add(predictionText);
		kernel[StableData.INT_SIX] = getTrustRate(predictionText);
//		System.out.println(kernel[StableData.INT_SIX]);
		forRestReturn.add(""+kernel[StableData.INT_SIX]);
		return kernel;
	}

	public double[] getBehaviorICAKernel(String text, Analyzer analyzer, EmotionMap emotionMap) throws IOException {
		forRestReturn = new LinkedList<>();
		kernel = new double[StableData.INT_SEVEN];	
		EmotionInit emotionInit = new EmotionInit();
		emotionInit.initExcludeAnalyzer(text, analyzer, emotionMap);
		//reduce
		double positiveCount = emotionInit.getPositiveCount();
		double negativeCount = emotionInit.getNegativeCount();
		double totalCount = emotionInit.getTotalCount();
		//System.out.println("正面情感：" + positiveCount);
		forRestReturn.add("正面情感：" + positiveCount);
		
//		System.out.println("负面情感：" + negativeCount);
		forRestReturn.add("负面情感：" + negativeCount);
		if(positiveCount == StableData.INT_ZERO) {
			positiveCount = StableData.INT_ONE;
		}
		if(negativeCount == StableData.INT_ZERO) {
			negativeCount = StableData.INT_ONE;
		}
		double adjRatio = Math.abs(positiveCount/negativeCount-negativeCount/positiveCount);
//		System.out.println("渲染比率：" + adjRatio);
		forRestReturn.add("渲染比率：" + adjRatio);
		double phychologicRatio = (positiveCount + negativeCount)/totalCount;
//		System.out.println("情绪比率：" + phychologicRatio);
		forRestReturn.add("情绪比率：" + phychologicRatio);
		double infectionRatio = adjRatio*phychologicRatio;
//		System.out.println("感染比率：" + infectionRatio);	
		forRestReturn.add("感染比率：" + infectionRatio);
		kernel[StableData.INT_ZERO] = adjRatio;
		kernel[StableData.INT_ONE] = phychologicRatio;
		kernel[StableData.INT_TWO] = infectionRatio;
		//
		EnvironmentInit environmentInit = new EnvironmentInit();
		environmentInit.initFromEmotionExcludeEmotion(emotionInit.getWordFrequencyMap(), emotionMap);
		Map<String, EmotionSample> environmentSampleMap = environmentInit.getEmotionSampleMap();
		//reduce
//		System.out.println("观测角度：");
		forRestReturn.add("观测角度：");
		String environmentText = "";
		Iterator<String> Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getDistinction()) {
				environmentText += emotionSample.getDistinction() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(environmentText);
		forRestReturn.add(environmentText);
		kernel[3] = getTrustRate(environmentText, analyzer, emotionMap);
//		System.out.println(kernel[StableData.INT_THREE]);
		forRestReturn.add(""+kernel[StableData.INT_THREE]);
//		System.out.println("");
//		System.out.println("信任比率：");
		forRestReturn.add("信任比率：");
		String motivationText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getMotivation()) {
				motivationText += emotionSample.getMotivation() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(motivationText);
		forRestReturn.add(motivationText);
		kernel[4] = getTrustRate(motivationText, analyzer, emotionMap);
//		System.out.println(kernel[StableData.INT_FOUR]);
		forRestReturn.add(""+kernel[StableData.INT_FOUR]);
//		System.out.println("");
//		System.out.println("执行比率：" );
		forRestReturn.add("执行比率：");
		String trendingText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getTrending()) {
				trendingText += emotionSample.getTrending() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(trendingText);
		forRestReturn.add(trendingText);
		kernel[5] = getTrustRate(trendingText, analyzer, emotionMap);
//		System.out.println(kernel[StableData.INT_FIVE]);
		forRestReturn.add(""+kernel[StableData.INT_FIVE]);
		//reduce
//		System.out.println("");
//		System.out.println("成功比率：");
		forRestReturn.add("成功比率：");
		String predictionText = "";
		Iterator = environmentSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = environmentSampleMap.get(word);
			if(null != emotionSample.getPrediction()) {
				predictionText += emotionSample.getPrediction() + StableData.SPACE_STRING;
			}
		}
//		System.out.println(predictionText);
		forRestReturn.add(predictionText);
		kernel[StableData.INT_SIX] = getTrustRate(predictionText, analyzer, emotionMap);
//		System.out.println(kernel[StableData.INT_SIX]);
		forRestReturn.add(""+kernel[StableData.INT_SIX]);
		return kernel;
	}
}