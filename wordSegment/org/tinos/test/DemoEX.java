package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.view.obj.WordFrequency;
public class DemoEX {
	int a=0;
	int b=0;
	int c=0;
	int d=0;
	int e=0;
	int f=0;
	int g=0;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
		analyzer.init();
		Map<String, String> pos = analyzer.getPosCnToCn();
		List<String> sets = new ArrayList<>();
		Map<String, WordFrequency> seta = new ConcurrentHashMap<>();
		TimeCheck t = new TimeCheck();
		String ss = "科学的发展是一种市场和社会需求的传承，每一位获得诺贝尔奖的科学家";//32字
		DemoEX demoEX=new DemoEX();
		t.begin();
		for (int i = 0; i < 5000000; i++) { //重复500万次数 相当于处理 1.6亿字  耗费 时 7.280秒 
				sets= analyzer.parserString(ss);
		}
		t.end();
		t.duration();
//		System.out.println(StableCount.a1);
//		System.out.println(StableCount.a2);
//		System.out.println(StableCount.a3);
//		System.out.println(StableCount.a4);
//		System.out.println(StableCount.a5);
//		System.out.println(StableCount.a6);
//		System.out.println(StableCount.a7);
//		System.out.println(StableCount.a8);
//		System.out.println(StableCount.a9);
//		System.out.println(StableCount.a10);
//		System.out.println(StableCount.a11);
//		System.out.println(StableCount.a12);
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