package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import static java.lang.System.*;

public class DemoPOSforSpecial {
	public static void main(String[] args) throws IOException {
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
		analyzer.init();
		Map<String, String> nlp = analyzer.getPosCnToCn();
		List<String> sets = new ArrayList<>();
		String[] ss = new String[1];
		String[] ss1 = new String[1];
//		ss[0] = " 研究生命令本科生研究生命科学的问题的确定问题的确定不下来解放大道路面积水满了解放大道路面  ";
//		ss1[0] = " 研究 生 命令 本科 生 研究 生命 科学 的 问题 的 确定 问题 的确 定 不 下来 解放 大道 路面 积 水 满 了 解放 大道 路 面 ";
//		ss[0] = " 中国有的这些企业中国有企业方程的解除了零研究生命令本科生研究生命科学的问题的确定问题的确定不下来解放大道路面积水满了解放大道路面  ";
//		ss1[0]= " 中国 有的 这些 企业 中 国有 企业方程 的 解 除了 零 研究 生 命令 本科 生 研究 生命 科学 的 问题 的 确定 问题 的确 定 不 下来 解放 大道 路面 积 水 满 了 解放 大道 路面 ";
//		ss[0] = "老人家身体不错";
//		ss[1]= "老人家中很干净";
//		ss1[0] = "老 人家 身体 不错";
//		ss1[1]= "老人 家 中 很 干净";
		ss[0] = "沿海南方向逃跑";
		ss1[0] = " 有 用户 发现";
		for (int i = 0; i < ss.length; i++) {
			System.out.println("超级变态复杂病句-->" + ss[i]);
			sets = analyzer.parserString(ss[i].replace(" ", ""));//词性分析
			out.print("分析处理真实结果-->");
			for (int j = 0; j < sets.size(); j++) {
				if (!sets.get(j).replaceAll("\\s+", "").equals("")) {
					out.print(sets.get(j) + " ");
				}
			}
			out.println();
			out.println("期望得到分词效果-->" + ss1[i]);
			for (int k = 0; k < sets.size(); k++) {
				if (!sets.get(k).replaceAll("\\s+", "").equals("")) {
					nlp.get(sets.get(k));
					out.println(sets.get(k) + "/" + nlp.get(sets.get(k)) + "  ");
				}
			}
			out.println("");
		}
	}
}