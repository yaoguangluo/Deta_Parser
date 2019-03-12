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
		String text = "少年哲学智慧启蒙教育丛书《我眼中的美与丑》里，蕴含着十分丰富的道理，每一章都告诉我们不一样的道理。\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"第一章告诉了我们什么是公平，什么是公正; 第二章告诉我们如何分别对与错；第三章让我们不能撒谎；第四章让"
				+ "我们认识什么是真善美，什么是假恶丑；第五章让我们知道外表与内在哪个重要；第六章教我们应该如何与他人相好；第七章使我们知道什么才是真正的尊重。\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"在这七章之中，最令我深有感触的，是第六章。有时，你自己的观点可能和别人的观点不一样。比如,你认为某个"
				+ "明星好看，而别人不一定这么认为。这个时候你不能说别人不对；又比如，有时候班委不懂得听取别人意见，总是让别人听他自己一个人的话，这时我们就得坚信自己的观"
				+ "点了，我们要根据实际情况来判断自己的对与错！\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"如果你觉得别人对待你态度不好，你就先想想自己是怎么对待别人的，你怎么样对待别人，别人就会怎么样对待"
				+ "你自己。\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"\\\\r\\\\n\\\" + \\r\\n\" + \r\n" + 
				"				\"				\\\"我们所生活的世界纷乱芜杂，其中有好与坏，有美与丑，有谎言也有真诚……那么，你知道怎么才能判断吗？那就"
				+ "来看《我眼中的美与丑》这本书吧!";
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