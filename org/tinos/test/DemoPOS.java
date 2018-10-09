package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.Analyzer;
import org.tinos.engine.imp.CogsBinaryForestAnalyzerImp;
import timeProcessor.TimeCheck;
public class DemoPOS{
	public static void main(String []args) throws IOException{
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp(); 
		analyzer.init();
		Map<String, String> nlp = analyzer.getWord();
		List<String> sets = new ArrayList<>();
		String ss = "从容易开始念经的和尚未从容易知和尚"
				+ "未结婚的施主一样其实都不和尚未成佛的心态有关";//40字
		String ss1 = "从 容易 开始 念经 的 和尚 未 从容 易 知 和 尚未 结婚 的 施主 "
				+ "一样 其实 都 不和 尚未 成佛 的 心态 有关";//150字
		System.out.println("输入病句-->"+ss);
		System.out.println("预期结果-->"+ss1);
		TimeCheck t= new TimeCheck();
		t.begin();
		for(int i = 0; i < 1; i++) { //重复100万次数 相当于处理 4000万字
			sets = analyzer.parserString(ss);//词性分析
		}
		t.end();
		t.duration();
		System.out.print("真实结果-->");
		for(int i = 0; i < sets.size(); i++){
			if(!sets.get(i).replaceAll("\\s+", "").equals("")) {
				System.out.print(sets.get(i)+" ");
			}
		}
		System.out.println("");
		System.out.println("词性分析-->");
		t.begin();
		for(int i = 0; i < sets.size(); i++){
			if(!sets.get(i).replaceAll("\\s+", "").equals("")) {
				System.out.println(sets.get(i)+"/"+nlp.get(sets.get(i)) +"  ");
			}
		}
		t.end();
		System.out.println("");
		System.out.println("");
		t.duration();
	}
}