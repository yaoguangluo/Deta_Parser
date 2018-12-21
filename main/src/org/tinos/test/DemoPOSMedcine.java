package org.tinos.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.view.obj.WordFrequency;

import static java.lang.System.*;

@SuppressWarnings("unused")
public class DemoPOSMedcine {
    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
        analyzer.init();
        Map<String, String> nlp = analyzer.getPosCnToCn();
        List<String> sets = new ArrayList<>();
        String[] ss = new String[1];
        String[] ss1 = new String[1];
       // ss1[0] = "制方 ： 红花 煎汁 和童便服。治 胞衣 不下。产后 血晕。同 当归、 生地 、 牛膝 、 白芍 、 益母 、 川芎 、 延胡索 。治产后 恶血 不尽";
        ss[0] = "、 益母 、结婚益母";
        ss1[0] = "、 益母 、结婚益母"; 

        for (int i = 0; i < ss.length; i++) {
            System.out.println("超级变态复杂病句-->" + ss[i]);
            Map<String, WordFrequency> map = analyzer.parserStringByReturnFrequencyMap(
            		ss[0]
					);
            out.print("分析处理真实结果-->");
            Iterator<String> it = map.keySet().iterator();
            while(it.hasNext()) {
            	 out.println(it.next());
            }
        }
    }
}