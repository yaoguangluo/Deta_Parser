package org.tinos.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.view.obj.WordFrequency;
import timeProcessor.TimeCheck;

@SuppressWarnings("unused")
public class DemoEX {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
        analyzer.init();
        Map<String, String> pos = analyzer.getWord();
        List<String> sets = new ArrayList<>();
        TimeCheck t = new TimeCheck();
        // String ss = "和尚未出家前这个和尚未和尚未成家之人组成家庭过，各位和尚们不要怪这个和尚";

        String ss = "科学的发展是一种传承，每一个获得诺贝尔奖的科学家，都是通过长时间对问题的优化中不断总结和分化" +
                "，最终得到科学的成果";
        t.begin();
        for (int i = 0; i < 1000000; i++) { //重复100万次数 相当于处理 5700来万字
            sets = analyzer.parserString(ss);//词性分析
        }
        t.end();
        System.out.print("分析处理真实结果-->");
        for (int i = 0; i < sets.size(); i++) {
            if (!sets.get(i).equals("")) {
                System.out.print(sets.get(i) + " ");
            }
        }
        System.out.println("");
        t.duration();
        System.out.println("");
        System.out.println("词性分析-->");
        t.begin();
        for (int j = 0; j < 1; j++) {
            for (int i = 0; i < sets.size(); i++) {
                if (!sets.get(i).replaceAll("\\s+", "").equals("")) {
                    System.out.print(sets.get(i) + "/" + pos.get(sets.get(i)) + "----");
                }
            }
        }
        t.end();
        System.out.println("");
        t.duration();
        System.out.println("");
        System.out.println("词频分析-->");
        t.begin();
        List<WordFrequency> fwa = analyzer.getWordFrequency(sets);
        t.end();
        for (int i = fwa.size() - 1; i >= 0; i--) {
            System.out.print(fwa.get(i).getWord() + ":" + fwa.get(i).getFrequency() + "----");
        }
        System.out.println("");
        t.duration();
    }
}
