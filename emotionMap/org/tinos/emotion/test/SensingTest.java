package org.tinos.emotion.test;

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
import org.tinos.view.obj.WordFrequency;

public class SensingTest{
	public static void main(String[] argv) throws IOException {
		//init
		EmotionMap emotionMap = new EmotionMapImp(); 
		emotionMap.initMotivationMap();
		emotionMap.initNegativeMap();
		emotionMap.initPositiveMap();
		emotionMap.initTrendingMap();
		emotionMap.initPredictionMap();
		
		//get sentence
		String text = "谈文艺社科工作\r\n" + 
				"\r\n" + 
				"一个国家、一个民族不能没有灵魂。\r\n" + 
				"\r\n" + 
				"人民是创作的源头活水，只有扎根人民，创作才能获得取之不尽、用之不竭的源泉。\r\n" + 
				"\r\n" + 
				"一切有价值、有意义的文艺创作和学术研究，都应该反映现实、观照现实，都应该有利于解决现实问题、回答现实课题。\r\n" + 
				"\r\n" + 
				"谈政治协商\r\n" + 
				"\r\n" + 
				"人心是最大的政治，共识是奋进的动力。\r\n" + 
				"\r\n" + 
				"谈生态文明建设\r\n" + 
				"\r\n" + 
				"不能因为经济发展遇到一点困难，就开始动铺摊子上项目、以牺牲环境换取经济增长的念头，甚至想方设法突破生态保护红线。要保持加强生态环境保护建设的定力，不动摇、不松劲、不开口子。\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"解决好人民群众反映强烈的突出环境问题，既是改善环境民生的迫切需要，也是加强生态文明建设的当务之急。\r\n" + 
				"\r\n" + 
				"t01380f473a6ff76c11.jpg?size=600x314\r\n" + 
				"\r\n" + 
				"谈艰苦奋斗\r\n" + 
				"\r\n" + 
				"不论我们国家发展到什么水平，不论人民生活改善到什么地步，艰苦奋斗、勤俭节约的思想永远不能丢。艰苦奋斗、勤俭节约，不仅是我们一路走来、发展壮大的重要保证，也是我们继往开来、再创辉煌的重要保证。\r\n" + 
				"\r\n" + 
				"谈脱贫攻坚\r\n" + 
				"\r\n" + 
				"现在距离2020年完成脱贫攻坚目标任务只有两年时间，正是最吃劲的时候，必须坚持不懈做好工作，不获全胜、决不收兵。\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"脱贫攻坚越到最后时刻越要响鼓重锤，决不能搞急功近利、虚假政绩的东西。\r\n" + 
				"\r\n" + 
				"要做好革命老区、中央苏区脱贫奔小康工作。今年是新中国成立70周年，要饮水思源，决不能忘了老区苏区人民。\r\n" + 
				"\r\n" + 
				"谈乡村振兴\r\n" + 
				"\r\n" + 
				"确保重要农产品特别是粮食供给，是实施乡村振兴战略的首要任务。\r\n" + 
				"\r\n" + 
				"耕地是粮食生产的命根子。要强化地方政府主体责任，完善土地执法监管体制机制，坚决遏制土地违法行为，牢牢守住耕地保护红线。\r\n" + 
				"\r\n" + 
				"t011097807208cbad42.jpg?size=1080x691\r\n" + 
				"\r\n" + 
				"谈实体经济\r\n" + 
				"\r\n" + 
				"做企业、做事业，不是仅仅赚几个钱的问题。做实体经济，要实实在在、心无旁骛地做一个主业，这是本分。\r\n" + 
				"\r\n" + 
				"谈对台工作\r\n" + 
				"\r\n" + 
				"要把工作做到广大台湾同胞的心里，增进台湾同胞对民族、对国家的认知和感情。对台湾同胞一视同仁，像为大陆百姓服务那样造福台湾同胞。要听取台湾同胞呼声，研究还可以推出哪些惠台利民的政策措施，只要能做到的都要尽力去做。";
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
		
		rationMap.getMotivation(emotionSampleMap, motivation);
		rationMap.getTrending(emotionSampleMap, trending);
		rationMap.getPrediction(emotionSampleMap,prediction);
		rationMap.getEmotionRatio(emotionSampleMap, positiveCount, negativeCount );
		rationMap.getMotivationRatio(emotionSampleMap, positiveCount + negativeCount);
		rationMap.getCorrelationRatio(emotionSampleMap, positiveCount + negativeCount);
		double emotionRatio = Math.abs(positiveCount/negativeCount - negativeCount/positiveCount);
		rationMap.getContinusRatio(emotionSampleMap, emotionRatio);
		rationMap.getTrendsRatio(emotionSampleMap);
		rationMap.getPredictionRatio(emotionSampleMap);
		rationMap.getGuessRatio(emotionSampleMap);
		rationMap.getSensingRatio(emotionSampleMap);
		
		//output
		System.out.println("正面：" + positiveCount);
		System.out.println("负面：" + negativeCount);
		System.out.println("情感比率：" + emotionRatio);
		
		Iterator<String> Iterator=emotionSampleMap.keySet().iterator();
		while(Iterator.hasNext()) {
			String word = Iterator.next();
			EmotionSample emotionSample = emotionSampleMap.get(word);
			if((null != emotionSample.getMotivation() || null != emotionSample.getTrending() || null != emotionSample.getPrediction())
					|| ( 0 != emotionSample.getPositiveCount()||0 != emotionSample.getNegativeCount())) {
				System.out.print(word+":");
				System.out.print("动机：" + emotionSample.getMotivation());
				System.out.print("倾向：" + emotionSample.getTrending());
				System.out.println("预测：" + emotionSample.getPrediction());
				System.out.print("正面：" + emotionSample.getPositiveCount());
				System.out.print("负面：" + emotionSample.getNegativeCount());
				System.out.print("情感：" + emotionSample.getEmotionRatio());
				System.out.print("动机：" + emotionSample.getMotivationRatio());
				System.out.print("关联：" + emotionSample.getCorrelationRatio());
				System.out.print("韧性：" + emotionSample.getContinusRatio());
				System.out.print("趋势：" + emotionSample.getTrendsRatio());
				System.out.print("预测：" + emotionSample.getPredictionRatio());
				System.out.print("猜测：" + emotionSample.getGuessRatio());
				System.out.println("冥想：" + emotionSample.getSensingRatio());
			}	 
		}
	}
}