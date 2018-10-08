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
public class Demoex{
	public static void main(String args[]) throws IOException{
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp(); 
		analyzer.init();
		Map<String, String> nlp = analyzer.getWord();
		List<String> sets = new ArrayList<String>();
		String ss = "内科学作为临床医学的基础学科，重点论述人体各个系统各种疾病的病因、发病机" + 
				"制、临床表现、诊断、治疗与预防。编纂《内科学》作为医学教育的教材，其目的是引导" + 
				"医学生在已掌握基础医学、临床前期学科知识的基础上，从理论走向实践、从书本走向临" + 
				"床，帮助他们掌握为患者诊治疾病的实际本领。";
		long c=0;
		TimeCheck t= new TimeCheck();
		t.begin();
		for(int i = 0; i < 1; i++) { //600次 循环 相当于 210万字文章
			sets = analyzer.parserString(ss); 
		}
		t.end();
		int j=0;
		for(int i = 0; i < sets.size(); i++){
			System.out.print(sets.get(i)+"  ");
			j++;
			if(j>15) {
				j=0;
				System.out.println("");
			}
		}
		t.duration();
	}
}