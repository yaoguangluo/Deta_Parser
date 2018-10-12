package org.tinos.test;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import timeProcessor.TimeCheck;
public class Demoex {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
        analyzer.init();
        Map<String, String> nlp = analyzer.getWord();
        List<String> sets = new ArrayList<>();
        String ss = "内科学是临床医学的基础\r\n" +
                "内科学作为临床医学的基础学科，重点论述人体各个系统各种疾病的病因、发病机\r\n" +
                "基础，既不可能成为一名好的内科医生，更不可能成为优秀的专科医生。";//40字
        System.out.println("输入病句-->" + ss);
//		TimeCheck t= new TimeCheck();
//		t.begin();
        for (int i = 0; i < 1; i++) { //重复1次数 相当于处理0万字
            sets = analyzer.parserString(ss);//词性分析
        }
//		t.end();
//		t.duration();
        System.out.print("真实结果-->");
        for (int i = 0; i < sets.size(); i++) {
            if (!sets.get(i).replaceAll("\\s+", "").equals("")) {
                System.out.print(sets.get(i) + " ");
            }
        }
        System.out.println("");
        System.out.println("词性分析-->");
//		t.begin();
        for (int i = 0; i < sets.size(); i++) {
            if (!sets.get(i).replaceAll("\\s+", "").equals("")) {
                System.out.println(sets.get(i) + "/" + nlp.get(sets.get(i)) + "  ");
            }
        }
//		t.end();
//		System.out.println("");
//		System.out.println("");
//		t.duration();
    }
}