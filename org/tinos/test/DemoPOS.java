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
//		String ss = "东方海上路上尚未和尚"; //10字		

		String ss = "不能把青少年当屌丝一了百了这样社会就不能进步了";
			//	+   "知和尚未结婚的施主一样其实都不和尚未成佛的心态有关因为这和尚未成佛"; //63字		
		
		//String ss1 = "你 和 尚未 来 的 和尚 未 和 从 容易 开始 念经 那 和尚 未 进行 告别 不 显得 从容 易 知 和 尚未 结婚 的 施主 一样 其实 都 不 和 尚未 成佛 的 心态 有关 因为 这 和尚 未 成佛";				
		System.out.println("输入病句-->"+ss);
		//System.out.println("期望分词-->"+ss1);
		TimeCheck t= new TimeCheck();
		t.begin();
		for(int i = 0; i < 1000000; i++){ //重复100万次数 相当于处理 2300万字
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