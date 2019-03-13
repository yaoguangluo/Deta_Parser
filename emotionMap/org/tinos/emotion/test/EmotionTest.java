package org.tinos.emotion.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.tinos.emotion.estimation.EmotionSample;
import org.tinos.emotion.estimation.RatioMap;
import org.tinos.emotion.estimation.imp.RatioMapImp;
import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.emotion.ortho.fhmm.imp.EmotionMapImp;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.view.obj.WordFrequency;

public class EmotionTest{
	public static void main(String[] argv) throws IOException {
		//init
		EmotionMap emotionMap = new EmotionMapImp(); 
		emotionMap.initMotivationMap();
		emotionMap.initNegativeMap();
		emotionMap.initPositiveMap();
		emotionMap.initTrendingMap();
		emotionMap.initPredictionMap();
		//get sentence
		String text = "关于成瘾性的戒除方式，上瘾在医学上普遍定义为一种具有精神依赖并长期导致健康危害性的行为。\r\n" + 
				"关于成瘾的溯源有很多因素，其中最重要的是依赖。因为长期的依赖导致自身某种缺陷逐渐丧失而\r\n" + 
				"对成瘾物体产生不可替代性。通过这个推论，可以初步来定义戒断瘾欲，最有效的方式是替代和引导。\r\n" + 
				"替代物，本身也是有强烈制瘾性，和危害性，但是危害要小于原物。通过替代和强制减少剂量和精洗\r\n" + 
				"脑教育，通过一个时间周期达到戒除。中间有戒断反应，需要观察。引导，是在对没有成瘾并属于易\r\n" + 
				"感染群体进行教育和传授方式，提高群体的免疫力和排斥力。上瘾不是欲望。欲望是生物的应激性进\r\n" + 
				"化的产物，是与生俱来的。上瘾是一种外力干涉造成的依赖。上瘾的级别有很多种。医学有相关严谨\r\n" + 
				"的打分段，其中毒瘾大于烟瘾大于网瘾。最有效的戒除手段就是环境和生活方式的选择。很多时候\r\n" + 
				"环境不是很美好，生活方式充满了隐患，人的精神会产生误差，这个时候受体为不稳定态，极易接触\r\n" + 
				"成瘾源。当环境无法改变的时候，我们需要改变自己，选择一个愉悦的生活方式，进行自我心里疏导，\r\n" + 
				"很容易排斥上瘾源。其中这些词汇是非常有价值的精神药物：自信，豁达，友善，分享 等等。\r\n" + 
				"一些成瘾的受体，普遍有某种倾向: 奢靡，闭塞，强迫，空虚 等等。这里不是贬义，只是因为长期的环境\r\n" + 
				"因素不是那么美好导致了一些思维误差。所以引导是非常重要的。改变人的不是能力，而是选择和环境。\r\n" + 
				"如果环境不是很完美，那么选择一个健康的生活方式，是非常重要的。";
		//parser sentence
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
		analyzer.init();
		Map<String, Object> positive = emotionMap.getPositiveMap();
		Map<String, Object> negative = emotionMap.getNegativeMap();
		Map<String, Object> motivation = emotionMap.getMotivationMap();
		Map<String, Object> trending = emotionMap.getTrendingMap();
		Map<String, Object> prediction = emotionMap.getPredictionMap();
		//map
		List<String> sets = analyzer.parserString(text);
		Map<Integer, WordFrequency> wordFrequencyMap = analyzer.getWordFrequencyByReturnSortMap(sets);
		RatioMap rationMap = new RatioMapImp();
		Map<String, EmotionSample> emotionSampleMap = rationMap.getEmotionSampleMap(wordFrequencyMap, positive, negative);
		double positiveCount = rationMap.findTotalPositiveCount(emotionSampleMap);
		double negativeCount = rationMap.findTotalNegativeCount(emotionSampleMap);
		double totalCount = rationMap.findTotalKeyCount(emotionSampleMap);
		rationMap.getMotivation(emotionSampleMap, motivation);
		rationMap.getTrending(emotionSampleMap, trending);
		rationMap.getPrediction(emotionSampleMap,prediction);
		//reduce
		System.out.println("正面数：" +positiveCount);
		System.out.println("负面数：" +negativeCount);
		if(positiveCount == 0) {
			positiveCount = 1;
		}
		if(negativeCount == 0) {
			negativeCount = 1;
		}
		double emotionRatio = Math.abs(positiveCount/negativeCount-negativeCount/positiveCount);
		System.out.println("情感比率：" + emotionRatio);
		double phychologicRatio = (positiveCount + negativeCount)/totalCount;
		System.out.println("情绪比率：" + phychologicRatio);
		double infectionRatio = emotionRatio*phychologicRatio;
		System.out.println("感染比率：" + infectionRatio);
	}
}