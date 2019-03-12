package org.tinos.emotion.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		String text = "这10年改变我的一些思维方法\r\n" + 
				"1 你越担心什么 就越会来什么。\r\n" + 
				"2 一切都是你自己的错，找自己的原因。\r\n" + 
				"3 就差那么一点了。即使失败的仍很彻底。\r\n" + 
				"4 看法是一种概率论。\r\n" + 
				"5 尝试用数学去解释人生，人生会特别简单\r\n" + 
				"6 定下有价值的目标，做减法。\r\n" + 
				"7 敌人对你的狠说明你很有价值。\r\n" + 
				"8 敌人也是朋友，他们一直在挖掘你的价值。\r\n" + 
				"9 暴露自己的缺点，是一种减法，隐藏自己的优点是一种加法。\r\n" + 
				"10 罗瑶光价值定理 ：团队价值 = 个人价值 的平方 * 团队贡献量   类似 e=mc2\r\n" + 
				"11 想到什么，有没有必要做，能不能做，怎么做。 然后就去做，这就是执行力。\r\n" + 
				"12 善于思考以前的犯错误动机，做减法。\r\n" + 
				"13 被别人否定是一种大于50%的概率集，不要退却。\r\n" + 
				"14 世界大都是凡人，凡人只关心眼前利益和暂行思维，所以不要扯太高深，试图让凡人理解你是一种高效生活方式。";
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
		Map<Integer, WordFrequency> fwa = analyzer.getWordFrequencyByReturnSortMap(sets);
		double positiveCount = 0;
		double nagativeCount = 0;
		String motivationSION = "";
		String trendingSION = "";
		Map<String, Double> predictionSION = new HashMap<>(); 
		for (int i = fwa.size() - 1; i >= 0; i--) {
			if(fwa.get(i).getWord().length() > 1) {
				//frequency
				//System.out.print(fwa.get(i).getWord() + ":" + fwa.get(i).getFrequency() + "----");
				//negative
				if(positive.containsKey(fwa.get(i).getWord())) {
					//System.out.print("正面");
					positiveCount+=fwa.get(i).getFrequency();
				}
			 
				//positive
				if(negative.containsKey(fwa.get(i).getWord())) {
					//System.out.print("负面");
					nagativeCount+=fwa.get(i).getFrequency();
				}
				//motivation
				if(motivation.containsKey(fwa.get(i).getWord())) {
					//System.out.print(motivation.get(fwa.get(i).getWord()));
					motivationSION+=" "+motivation.get(fwa.get(i).getWord());
				}
				//trending
				if(trending.containsKey(fwa.get(i).getWord())) {
				//	System.out.print(trending.get(fwa.get(i).getWord()));
					String trend = trending.get(fwa.get(i).getWord()).toString();
					trendingSION += " " + trend;
					if(prediction.containsKey(trend)){
						String predictionWord = prediction.get(trend).toString();
						if(predictionSION.containsKey(predictionWord)) {
							double predictionSIONCount = predictionSION.get(predictionWord);
							predictionSION.put(predictionWord, predictionSIONCount + fwa.get(i).getFrequency());
						}else {
							predictionSION.put(predictionWord, 0.0 + fwa.get(i).getFrequency());
						}	
					}	
				}
				//System.out.println("");
			} 
		}
		//reduce
		System.out.println("正面数：" +positiveCount);
		System.out.println("负面数：" +nagativeCount);
		if(positiveCount==0) {
			positiveCount=1;
		}
		if(nagativeCount==0) {
			nagativeCount=1;
		}
		double emotionRatio = Math.abs(positiveCount/nagativeCount-nagativeCount/positiveCount);
		System.out.println("情感比率：" + emotionRatio);
		System.out.println("动    机：" + motivationSION);
		System.out.println("倾    向：" + trendingSION);
		
		//reduce
		System.out.println("决    策：");
		Iterator<String> iterator = predictionSION.keySet().iterator();
		while(iterator.hasNext()) {
			String predictionWord = iterator.next();
			System.out.println("概率：" + predictionWord+ "--比重：" + predictionSION.get(predictionWord));
		}
		
	}
}