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
//		String ss = "和尚未来的和尚未和从容易开始念经那和尚未进行告别不显得从容易知和尚未结婚的施主一样其实都不和尚未成佛的心态有关因为这和尚未成佛"; //66字		
//		
		String ss = "如果从容易开始于是从容不迫天下等于是非常识时务必为俊杰沿海南方向逃跑他说的确实在理结婚的和尚未结婚的提高产品质量中外科学名著内科学是临床医学的基础    内科学作为临床医学的基础学科，重点论述人体各个系统各种疾病的病因、发病机制、临床表现、诊断、治疗与预防"; //66字		
		
		String ss1 = "和 尚未 来 的 和尚 未 和 从 容易 开始 念经 那 和尚 未 进行 告别 不 显得 从容 易 知 和 尚未 结婚 的 施主 一样 其实 都 不 和 尚未 成佛 的 心态 有关 因为 这 和尚 未 成佛";				
		System.out.println("输入病句-->"+ss);
		System.out.println("期望分词-->"+ss1);
		TimeCheck t= new TimeCheck();
		t.begin();
		for(int i = 0; i < 1; i++) { //重复1万次数 相当于处理 66字
			sets = analyzer.parserString(ss.replace(" ", ""));//词性分析
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
		for(int j = 0; j < 1; j++) { 
			for(int i = 0; i < sets.size(); i++){
				if(!sets.get(i).replaceAll("\\s+", "").equals("")) {
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