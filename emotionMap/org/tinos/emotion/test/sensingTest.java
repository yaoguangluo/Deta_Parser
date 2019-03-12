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

public class sensingTest{
	public static void main(String[] argv) throws IOException {
		//init
		EmotionMap emotionMap = new EmotionMapImp(); 
		emotionMap.initMotivationMap();
		emotionMap.initNegativeMap();
		emotionMap.initPositiveMap();
		emotionMap.initTrendingMap();
		emotionMap.initPredictionMap();
		
		//get sentence
		String text = "非物理模式电磁脑控的语言心里学读心术\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:06:05\r\n" + 
				"德塔已经开源\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:10:57\r\n" + 
				"不懂原理怎么能根据语言读心呢?\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:11:07\r\n" + 
				"他说了言不由衷的话怎么办呢\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:12:07\r\n" + 
				"启发语料库\r\n" + 
				"语境语料库\r\n" + 
				"猜测语料库\r\n" + 
				"错误集训练语料库\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:12:25\r\n" + 
				"通过对言不由衷的人一直聊天一直对话\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:12:34\r\n" + 
				"进行熵增打分\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:13:00\r\n" + 
				"图灵机的功能一部分\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:13:09\r\n" + 
				"他关键的某一句是言不由衷的呢?\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:13:10\r\n" + 
				"我一些东西闭源了\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:13:50\r\n" + 
				"当对话1万句，10万字的时候\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:14:00\r\n" + 
				"那一句言不由衷无法掩饰的\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:14:24\r\n" + 
				"警察从来不会跟你说三句话就定罪的\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:15:07\r\n" + 
				"肯定是一个月的对话录入，情景分析， 3个1小时以上的陪审团参议， 加上复合情景分析\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:15:41\r\n" + 
				"半年后才会给你一个答案\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:15:42\r\n" + 
				"文学家写了一遍文章,使用人工智能判断文章的中心大意和作者给出的答案能保证一致?\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:16:00\r\n" + 
				"误差有误差的分析方式\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:16:32\r\n" + 
				"高考选用的文章给出答案和作者本人给出答案可能差别很大\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:16:58\r\n" + 
				"高考给的答案是不是类似于人工智能给出的答案\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:17:13\r\n" + 
				"可是作者给出的答案才是最真实的\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:17:34\r\n" + 
				"那是计算逻辑\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:17:46\r\n" + 
				"算法，公式，定理，不是心理学\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:18:02\r\n" + 
				"心理学的主题是动机\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:18:23\r\n" + 
				"哦\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:19:08\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:19:20\r\n" + 
				"这是刚才我们的对话分析\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:19:45\r\n" + 
				"计算机没有欺骗你\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:19:56\r\n" + 
				"我的确对你有保留的隐藏了一些东西\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:20:22\r\n" + 
				"但是整体我的动机是正面的\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:21:18\r\n" + 
				"如果一直坚持说谎圆慌怎么办,\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:21:23\r\n" + 
				"能判断出来?\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:21:30\r\n" + 
				"那就一直对话\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:21:59\r\n" + 
				"有人的心理素质很强的,\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:22:02\r\n" + 
				"不同的角度，不同的语气，不同的语境，各种对象进行谈论\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:22:37\r\n" + 
				"再强 当面对每天10万句的问答，持续1年，也会崩溃的\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:22:43\r\n" + 
				"24小时出不来结果就得放人啊,不能一直拘留啊\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:22:54\r\n" + 
				"对话不是拘留\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:23:10\r\n" + 
				"拘留是已经有嫌疑的人才会拘留权利\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:23:21\r\n" + 
				"涉案人员不会拘留\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:23:26\r\n" + 
				"持续一年?,这怎么能实际用于办案测谎呢\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:24:10\r\n" + 
				"就是嫌疑人,但没有强有力证据,警方询问对话,来判断是否说谎\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:24:28\r\n" + 
				"对各种问题，各种对象，基于时间 地址 问题，空间 关系所有的关联点一次又一次的变换对象进行大面积询问，\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:24:46\r\n" + 
				"一直问 不停的问\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:25:00\r\n" + 
				"感觉距离实际应用好远好远\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:25:25\r\n" + 
				"现在最有效的测谎是通过电磁脑控读脑\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:26:12\r\n" + 
				"语言心理学是动机，警察使用不会很强烈，但是在心里学领域很有价值\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:26:19\r\n" + 
				"那就显得low,通过对话判定显得高端\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:27:19\r\n" + 
				"为什么你非要把一个动机分析的功能用在犯罪测谎的功能上呢？\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:27:29\r\n" + 
				"心理学怎么用?对话快速了解一个人吗?\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:27:41\r\n" + 
				"这价值是肯定的\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:27:51\r\n" + 
				"不是,我不懂,想知道具体的实际应用\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:28:20\r\n" + 
				"你可以通过长期的优质对话了解一个人的性格取向。和心里动机。\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:28:46\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:28:56\r\n" + 
				"这是我把我们刚才50行对话进行分析\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:29:25\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:29:30\r\n" + 
				"结果是我的还是你的\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:29:40\r\n" + 
				"我们2个是非常正面的对抗方式有隐藏的辩论\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:29:42\r\n" + 
				"我们2个\r\n" + 
				"\r\n" + 
				"li叶叶 2019/3/12 10:29:53\r\n" + 
				"看见java了\r\n" + 
				"\r\n" + 
				"li叶叶 2019/3/12 10:29:56\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:30:47\r\n" + 
				"我们两个的结果,显示我们都执着,虚伪?\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/12 10:30:58\r\n" + 
				"对话是双方的\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:31:08\r\n" + 
				"@li叶叶  快来免费学习啦\r\n" + 
				"\r\n" + 
				"li叶叶 2019/3/12 10:31:36\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"li叶叶 2019/3/12 10:31:41\r\n" + 
				"学习啥\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 10:31:59\r\n" + 
				"学习人工智能\r\n" + 
				"\r\n" + 
				"li叶叶 2019/3/12 10:37:56\r\n" + 
				"我是菜逼\r\n" + 
				"\r\n" + 
				"兴泽 2019/3/12 11:33:51\r\n" + 
				"我也是所以学习嘛\r\n" + 
				"";
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
		//double keyCount = rationMap.findTotalKeyCount(emotionSampleMap);
		
		
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
			
			if(null != emotionSample.getMotivation() || null != emotionSample.getTrending() || null != emotionSample.getPrediction()) {
				System.out.println(word);
				System.out.print("动机：" + emotionSample.getMotivation());
				System.out.print("倾向：" + emotionSample.getTrending());
				System.out.print("预测：" + emotionSample.getPrediction());
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