package org.tinos.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import timeProcessor.TimeCheck;

@SuppressWarnings("unused")
public class DemoEX {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
        analyzer.init();
        Map<String, String> nlp = analyzer.getWord();
        List<String> sets = new ArrayList<>();
        TimeCheck t = new TimeCheck();
        String ss = "结婚的和尚未结婚的等和尚未成家之人都和尚未"
                + "成佛的和尚未必一样和尚未来的和尚未和从容"
                + "易开始念经那和尚未进行告别不显得从容易知"
                + "和尚未结婚的施主一样其实都不和尚未成佛的"
                + "心态有关因为这和尚未成佛虎头虎脑的虎头虎脑人";
//		     String ss = "杨过和小龙女重逢了";
        t.begin();
        String a = new String();

        for (int i = 0; i < 100000; i++) { //重复40万次数 相当于处理 1000万字
            sets = analyzer.parserString(ss);//词性分析
        }
//        for (int i = 0; i < 100000000; i++) { //重复40万次数 相当于处理 1000万字
//           // sets = analyzer.parserString(ss);//词性分析
////			a="sas";
////			a="";
//        }
        t.end();


        StringBuilder a2 = new StringBuilder();
        for (int i = 0; i < 100000000; i++) { //重复40万次数 相当于处理 1000万字
            // sets = analyzer.parserString(ss);//词性分析
            a2.append("sas");
            a2.delete(0, 3);
        }


        System.out.print("分析处理真实结果-->");
        for (int i = 0; i < sets.size(); i++) {
            if (!sets.get(i).equals("")) {
                System.out.print(sets.get(i) + " ");
            }
        }
        System.out.println("");
        System.out.println("词性分析-->");
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < sets.size(); i++) {
                if (!sets.get(i).replaceAll("\\s+", "").equals("")) {
                    nlp.get(sets.get(i));
                    System.out.println(sets.get(i) + "/" + nlp.get(sets.get(i)) + "  ");
                }
            }
        }
        t.duration();
    }
}
