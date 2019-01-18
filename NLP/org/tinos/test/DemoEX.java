package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.view.obj.WordFrequency;
//import timeProcessor.TimeCheck;
public class DemoEX {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
		analyzer.init();
		Map<String, String> pos = analyzer.getPosCnToCn();
		List<String> sets = new ArrayList<>();
		Map<String, WordFrequency> seta = new ConcurrentHashMap<>();
//		TimeCheck t = new TimeCheck();
		String ss = "科学的发展是一种传承，每一个获得诺贝尔奖的科学家，都是通过长时间对问题的优化中不断总结和分化" +
				"，最终得到科学的成果痴呆鉴别";//65字
//		String ss = "科学，是方法论和逻辑观的具体称谓，是一hgjj345345种劳动传承，和尚未解开谜团的盒子一样，需要打"
//				  + "开。我们r不需fdfgsdsf要猜测，只要234324保持一颗严谨的心gfdgbdfgfd态。可以用榔头，也"
//				  + "可以用钥匙，甚至能躺着让它自rwerwer动打开，这就是方法论。持32423续的在某一个ewrwer问题上"
//				  + "，rewr进行反复的优化，细化，挖rewrwe掘，提纯，分化根据不同rewrewr的思考方式发展不同5435"
//				  + "345的研究路rwer线，究根掘底，千锤百炼，最后得到的东西，那就是rewrwer真理这就是逻辑观结婚的"
//				  + "和尚未结婚的等7dnvy之人都和尚未成佛的和尚未必一样和尚未来的和尚udbfj开始念经那和尚未进行"
//				  + "告别不显得从容易知和"
//				  + "尚nic8d主一样其"
//				  + "实都不和尚未成佛的心"
//				  + "态hdu72和尚未成佛";//341字
//		t.begin();
		for (int i = 0; i < 1000000; i++) { //重复100万次数 相当于处理 6500多万字
		//	seta = analyzer.parserStringByReturnFrequencyMap(ss);
			sets= analyzer.parserMixedString(ss);
		}

//		t.end();
		System.out.print("分析处理真实结果-->");
		for (int i = 0; i < sets.size(); i++) {
			if (sets.get(i) != null) {
				System.out.print(sets.get(i) + " ");
			}
		}
//		for (int i = 0; i < sets.size(); i++) {
//			if (!sets.get(i).equals("")) {
//				System.out.print(sets.get(i) + " ");
//			}
//		}
		System.out.println("");
//		t.duration();
		System.out.println("");
		System.out.println("词性分析-->");
//		t.begin();
		for (int j = 0; j < 1; j++) {
			for (int i = 0; i < sets.size(); i++) {
				if (!sets.get(i).replaceAll("\\s+", "").equals("")) {
					System.out.print(sets.get(i) + "/" + pos.get(sets.get(i)) + "----");
				}
			}
		}
//		t.end();
		System.out.println("");
//		t.duration();
		System.out.println("");
		System.out.println("词频分析-->");
//		t.begin();
		Map<Integer, WordFrequency> fwa = analyzer.getWordFrequencyByReturnSortMap(sets);
//		t.end();
		for (int i = fwa.size() - 1; i >= 0; i--) {
			System.out.print(fwa.get(i).getWord() + ":" + fwa.get(i).getFrequency() + "----");
		}
		System.out.println("");
//		t.duration();
	}
}