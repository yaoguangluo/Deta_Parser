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
		String text = "雨\r\n" + 
				"浏阳这天气， 春雨间断不停。恰恰对我来说已有十年没有被这柔情所滋润。\r\n" + 
				"\r\n" + 
				"而父母抱怨的语气重重的闪了我对雨的渴望。\r\n" + 
				"\r\n" + 
				"肮脏的街角瞬间被雨刷的干干净净，行人的眼瞳犹如清澈的明镜。\r\n" + 
				"\r\n" + 
				"天色渐晚，夜市渐暄，绵绵细雨将这夜晚打扮的特别暖人。\r\n" + 
				"\r\n" + 
				"小雀轻甩着翅膀，互相嘲笑。家里的墨兰开花了，疯狂的吐露着汁蜜。\r\n" + 
				"\r\n" + 
				"漫步在细雨之中，肌肤不曾有吻痕，空气中尘埃渐渐沉淀，呼吸略带一丝甘甜。\r\n" + 
				"\r\n" + 
				"我问自己此时的雨是什么？\r\n" + 
				"\r\n" + 
				"雨越下越大，我回家静静的站在窗前凝望着，此时的雨如此挥洒有力。\r\n" + 
				"\r\n" + 
				"大雨拍打着枝叶，敲击着枝干，树木似乎更加挺直，从容面对一切。\r\n" + 
				"\r\n" + 
				"大雨扑向花蕊，侵湿了花萼，花朵抖了抖衣裳，依旧绽放。\r\n" + 
				"\r\n" + 
				"大雨淹没了草根，抽打着草茎， 嫩草秀出锋刃，硬的就像一把手术刀。\r\n" + 
				"\r\n" + 
				"我再问自己这雨是什么？\r\n" + 
				"\r\n" + 
				"雨停了，已是深夜。浏阳仍灯火阑珊，这星星之火可以燎原。";
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