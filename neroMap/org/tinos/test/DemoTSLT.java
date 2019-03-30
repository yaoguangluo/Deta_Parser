package org.tinos.test;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.engine.base.translator.Translator;
import org.tinos.engine.base.translator.imp.TranslatorImp;
//import org.tinos.freetts.thread.read.ReadEnglish;
import org.tinos.view.obj.Verbal;

//import timeProcessor.TimeCheck;
public class DemoTSLT {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
		analyzer.init();
		Map<String, String> ce = analyzer.getCnToEn();
		Map<String, String> ec = analyzer.getEnToCn();
		Map<String, String> poscc = analyzer.getPosCnToCn();
		Map<String, String> posec = analyzer.getPosEnToCn();
		Map<String, String> posee = analyzer.getPosEnToEn();
		Map<String, String> fce = analyzer.getFullCnToEn();
		Map<String, String> fec = analyzer.getFullEnToCn();
		System.out.println("输入");
		 String v = "如流枫之回雪，若浣花洗月";
////		 //String v = "数据一直在更新中";
//		String v = "中国正在崛起的道路上奔跑";
//		String v = "我一直在奔跑，我需要一双翅膀！";
//		String v = "他也一直在奔跑，他同样需要一双翅膀！";
		System.out.println(v);
		Translator ts = new TranslatorImp();
		ts.init(analyzer); 
//		TimeCheck t = new TimeCheck();
//		t.begin();
		List<Verbal> verbals = ts.index(analyzer, v);
		String ch = ts.getChineseSentenseFromVerbalList(verbals);
//		t.end();
		System.out.println("中文");
		System.out.println(ch);
		
		for(int i=0;i<verbals.size();i++) {
			System.out.print(verbals.get(i).getChinese());
			System.out.print(verbals.get(i).getEnglish());
			System.out.print(verbals.get(i).getExplain());
			System.out.println(verbals.get(i).getPartOfSpeech());
		}
		List<Verbal> verbalsFix = ts.fixPos(verbals);
		String en = ts.getEnglishSentenseFromVerbalFixList(verbalsFix);
		System.out.println("");
		System.out.println("英文");
		System.out.println(en);

		for(int i=0;i<verbalsFix.size();i++) {
			System.out.print(verbalsFix.get(i).getChinese());
			System.out.print(verbalsFix.get(i).getEnglish());
			System.out.print(verbalsFix.get(i).getExplain());
			System.out.println(verbalsFix.get(i).getPartOfSpeech());
		}
		
		System.out.println("");
		System.out.println("中文解释");
		for(int i=0;i<verbals.size();i++) {
			System.out.print(verbals.get(i).getExplain()==null?verbals.get(i).getChinese():verbals.get(i).getExplain());
		}
//		t.duration();
		
//		ReadEnglish readEnglish = new ReadEnglish();
//		readEnglish.setPreReadText(en);
//		readEnglish.start();
	}
}