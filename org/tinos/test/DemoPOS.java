package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import timeProcessor.TimeCheck;
@SuppressWarnings("unused")
public class DemoPOS{
	public static void main(String []args) throws IOException{
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp(); 
		analyzer.init();
		Map<String, String> nlp = analyzer.getWord();
		List<String> sets = new ArrayList<>();
String ss = "结婚的和尚未结婚的人都和尚未成佛的和尚未必一样";
		System.out.println("输入病句-->"+ss);
		//System.out.println("期望分词-->"+ss1);
		TimeCheck t= new TimeCheck();
		t.begin();
		for(int i = 0; i < 400000; i++){ //重复40万次数 相当于处理 1000万字
			sets = analyzer.parserString(ss.replace(" ", ""));//词性分析
		}
		t.end();
		t.duration();
		System.out.print("真实结果-->");
		for(int i = 0; i < sets.size(); i++){
			if(!sets.get(i).replaceAll("\\s+", "").equals("")){
				System.out.print(sets.get(i)+" ");
			}
		}
		System.out.println("");
		System.out.println("词性分析-->");
		t.begin();
		for(int j = 0; j < 1; j++){ 
			for(int i = 0; i < sets.size(); i++){
				if(!sets.get(i).replaceAll("\\s+", "").equals("")){
					nlp.get(sets.get(i));

					System.out.println(sets.get(i)+"/"+nlp.get(sets.get(i)) +"  ");
				}
			}
		}
		t.end();
		System.out.println("");
		System.out.println("");
		t.duration();
	}
}