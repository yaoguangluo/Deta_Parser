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
		String text = "我是云游四海\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 21:43:12\r\n" + 
				"晚上好罗先生！我是凯文猎头高级顾问沈先生\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 21:45:27\r\n" + 
				"您好\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 21:46:07\r\n" + 
				"听朋友说您很优秀！可否看下您的简历。我们专做年薪百万以上的高端客户！\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 21:46:47\r\n" + 
				"https://www.linkedin.com/in/yaoguangluo/\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 21:47:19\r\n" + 
				"百万美金？\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 21:54:43\r\n" + 
				"人民币\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 21:56:52\r\n" + 
				"能直接发简历吗？在路上。\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 21:57:31\r\n" + 
				"https://www.linkedin.com/in/yaoguangluo/ 上面有我一切信息\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 21:59:23\r\n" + 
				"还要登记注册吗？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 21:59:48\r\n" + 
				"你可以免费注册一个账号\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 21:59:53\r\n" + 
				"就能看到所有信息\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 22:00:32\r\n" + 
				"可以先发个简单看看\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:00:58\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:01:20\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:01:35\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:01:48\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:02:38\r\n" + 
				"现在正在学 韩语和俄语，法语进阶\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:03:01\r\n" + 
				"，因为学习才几个月，所以没有添加到简历中抱歉。\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 22:08:53\r\n" + 
				"有没有一线知名企业的工作背景？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:18:26\r\n" + 
				"一线？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:19:08\r\n" + 
				"国内的1线 ，10年前在 上海保安公司和上海电气集团玩了1年\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:19:24\r\n" + 
				"5年前在蓝汛玩了段时间\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:19:56\r\n" + 
				"前年在英特尔福森总厂混了些日子\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:20:22\r\n" + 
				"玩的内容是 写软件\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:21:35\r\n" + 
				"在牛津大学和法国巴黎一大的 Petit frere 玩了一段时间\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:22:02\r\n" + 
				"内容是 医学数据软件分析\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:22:51\r\n" + 
				"国内的一线不好意思写进linkedin介绍 我就过滤了抱歉\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:23:38\r\n" + 
				"还有一些国内外价值低于1000万美金的企业，我也过滤了。\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 22:50:40\r\n" + 
				"目前国内从规模和效益上来看，科技界算BAT和华为，地产界碧桂圆、恒大、万科、泰禾等。这些企业开的工资高，当然放出的都是高端岗位。\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 22:55:20\r\n" + 
				"我对这些垃圾不感兴趣\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:00:21\r\n" + 
				"我是搞技术的，我仅仅对能指引我前进，开阔我眼界，提高我思想的人或物感兴趣。\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:03:29\r\n" + 
				"呵呵，有骨气！看来你对事业看的比金钱重要！\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:05:13\r\n" + 
				"现在很多刚成长的科创企业很适合。\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:07:48\r\n" + 
				"我手里有9个作品，世界59个国家 470家公司在使用，我的目的很明确，让他们更轻松更方便的使用我的持续优化作品。\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:08:54\r\n" + 
				"那您目前一年收入多少？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:09:42\r\n" + 
				"我的收入？   我现在需要2个亿的资金，正在进行天使融资计划\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:12:23\r\n" + 
				"2个亿属于您自己还是融资借的？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:12:37\r\n" + 
				"融资\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:12:59\r\n" + 
				"那您终归要还的\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:13:39\r\n" + 
				"我现在活着，再过几十年命也是要还的\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:15:01\r\n" + 
				"2个亿对一个好项目来说太少，是你一个人操盘？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:16:38\r\n" + 
				"你如果能融资20个亿，我可以销售20%德塔开源原始股份给你。\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:16:44\r\n" + 
				"我有个大设计\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:19:17\r\n" + 
				"呵呵，我可没那么大的本事！有好项目可以公开招标，吸引同行操盘！\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:20:51\r\n" + 
				"你会有的，别放弃自己\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:23:40\r\n" + 
				"如果你的项目有价值生命周期长，可以吸引有实力的企业入股合伙。\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:25:01\r\n" + 
				"你的项目有什么意义？\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:26:38\r\n" + 
				"改变世界\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:27:20\r\n" + 
				"太虚幻了，说实际点。\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:27:51\r\n" + 
				"你看过我的AOPM VPCS论文吗？\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/1 23:28:20\r\n" + 
				"没看\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:29:09\r\n" + 
				"https://github.com/yaoguangluo\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"Tin Royal 2019/3/1 23:29:48\r\n" + 
				"https://github.com/yaoguangluo/VPCS_Theroy/blob/master/VPCS-Method_V1.1.pdf\r\n" + 
				"\r\n" + 
				"云游四海 2019/3/3 17:32:06\r\n" + 
				"领英目前在维护当中。你面试的简历可以发一份！\r\n" + 
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