package org.tinos.test;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tinos.engine.Analyzer;
import org.tinos.engine.imp.BinaryForestAnalyzerImp;
import org.tinos.engine.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.engine.imp.BaseAnalyzerImp;
import org.tinos.engine.imp.PrettyAnalyzerImp;

import timeProcessor.TimeCheck;
@SuppressWarnings("unused")
public class DemoPOS{
	public static void main(String args[]) throws IOException{
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp(); 
		analyzer.init();
		Map<String, String> nlp = analyzer.getWord();
		List<String> sets = new ArrayList<String>();
		String ss = "如果从容易开始于是"
				+ "从容不迫天下等于是"
				+ "非常识时务必为俊杰"
				+ "沿海南方向逃跑他说"
				+ "的确实在理结婚的和"
				+ "尚未结婚的提高产品"
				+ "质量中外科学名著内"
				+ "沿海南方向逃跑他说"
				+ "的确实在理结婚的和"
				+ "尚未结婚的提高产品"
				+ "质量中外科学名著内"
				+ "科学是临床医学的基础"
				+ "内科学作为临床医学"
				+ "的确实在理结婚的和"
				+ "尚未结婚的提高产品"
				+ "质量中外科学名著内"
				+ "科学是临床医学的基础"
				+ "内科学作为临床医学"
				+ "的基础学科，重点论";//200字
		long c=0;
		TimeCheck t= new TimeCheck();
		t.begin();
		for(int i = 0; i < 100000; i++) { //次10万次 循环 相当于 2000万字文章
			sets = analyzer.parserString(ss); 
		}
		t.end();
		int j=0;
		for(int i = 0; i < sets.size(); i++){
			if(!sets.get(i).replaceAll("\\s+", "").equals("")) {
				System.out.print(sets.get(i)+"/"+nlp.get(sets.get(i)) +"  ");
				j++;
				if(j>8) {
					j=0;
					System.out.println("");
				}
			}
		}
		
		System.out.println("");
		System.out.println("");
		for(int i = 0; i < sets.size(); i++){
			if(!sets.get(i).replaceAll("\\s+", "").equals("")) {
				System.out.print(sets.get(i)+" ");
				j++;
				if(j>8) {
					j=0;
					System.out.println("");
				}
			}
		}
		System.out.println("");
		System.out.println("");
		t.duration();
	}
}