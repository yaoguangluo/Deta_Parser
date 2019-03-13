package org.tinos.sensing.test;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.tinos.emotion.estimation.EmotionSample;
import org.tinos.emotion.estimation.RatioMap;
import org.tinos.emotion.estimation.imp.RatioMapImp;
import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.emotion.ortho.fhmm.imp.EmotionMapImp;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.sensing.ortho.fhmm.SensingMap;
import org.tinos.sensing.ortho.fhmm.imp.SensingMapImp;
import org.tinos.view.obj.WordFrequency;
public class LenovoTest{
	public static void main(String[] argv) throws IOException {
		//init
		EmotionMap emotionMap = new EmotionMapImp(); 
		emotionMap.initMotivationMap();
		emotionMap.initNegativeMap();
		emotionMap.initPositiveMap();
		emotionMap.initTrendingMap();
		emotionMap.initPredictionMap();
		emotionMap.initDistinctionMap();
		//get sentence
		String text = "这10年改变我的一些思维方法\r\n" + 
				"1 你越担心什么就越会来什么。\r\n" + 
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
		SensingMap sensingMap = new SensingMapImp();
		sensingMap.initLenovoMap(analyzer);	
		Map<String, Object> positive = emotionMap.getPositiveMap();
		Map<String, Object> negative = emotionMap.getNegativeMap();
		Map<String, Object> motivation = emotionMap.getMotivationMap();
		Map<String, Object> trending = emotionMap.getTrendingMap();
		Map<String, Object> prediction = emotionMap.getPredictionMap();
		Map<String, Object> distinction = emotionMap.getDistinctionMap();
		Map<String, Object> lenovo = sensingMap.getLenovoMap();
		//map
		List<String> sets = analyzer.parserString(text);
		Map<Integer, WordFrequency> wordFrequencyMap = analyzer.getWordFrequencyByReturnSortMap(sets);
		RatioMap rationMap = new RatioMapImp();
		Map<String, EmotionSample> emotionSampleMap = rationMap.getEmotionSampleMap(wordFrequencyMap, positive, negative);
		rationMap.getMotivation(emotionSampleMap, motivation);
		rationMap.getTrending(emotionSampleMap, trending);
		rationMap.getPrediction(emotionSampleMap, prediction);
		rationMap.getDistinction(emotionSampleMap, distinction);
		//reduce
		System.out.println("环    境：");
		Iterator<String> Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(null != emotionSample.getDistinction()) {
				if(lenovo.containsKey(emotionSample.getDistinction())) {
					System.out.print(lenovo.get(emotionSample.getDistinction()).toString()+" ");
				}else {
					System.out.print(emotionSample.getDistinction()+" ");
				}
			}
		}
		System.out.println("");
		System.out.println("动机联想：");
		Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(null != emotionSample.getMotivation()) {
				if(lenovo.containsKey(emotionSample.getMotivation())) {
					System.out.print(lenovo.get(emotionSample.getMotivation()).toString()+" ");
				}else {
					System.out.print(emotionSample.getMotivation()+" ");
				}
			}
		}
		System.out.println("");
		System.out.println("倾向探索：" );
		Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(null != emotionSample.getTrending()) {
				if(lenovo.containsKey(emotionSample.getTrending())) {
					System.out.print(lenovo.get(emotionSample.getTrending()).toString()+" ");
				}else {
					System.out.print(emotionSample.getTrending()+" ");
				}
			}
		}

		//reduce
		System.out.println("");
		System.out.println("决策挖掘：");
		Iterator = emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if(null != emotionSample.getPrediction()) {
				if(lenovo.containsKey(emotionSample.getPrediction())) {
					System.out.print(lenovo.get(emotionSample.getPrediction()).toString()+" ");
				}else {
					System.out.print(emotionSample.getPrediction()+" ");
				}
			}
		}
	}
}