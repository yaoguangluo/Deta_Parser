*package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tinos.engine.Analyzer;
import org.tinos.engine.imp.BinaryForestAnalyzerImp;
import timeProcessor.TimeCheck;
public class demoex{
	public static void main(String args[]) throws IOException{
		Analyzer analyzer = new BinaryForestAnalyzerImp() ;
		analyzer.init();
		//返回分词数据集合
		List<String> sets = new ArrayList<String>();
		TimeCheck tc = new TimeCheck();
		String ss = "从容易开始能从容不迫" + 
				""; //大概10字
		//System.out.print("输入：" + ss);
		System.out.println("");
		System.out.println("计时开始");
		tc.begin();
		for(int i = 0; i < 1000000; i++) {//重复执行 100万次 相当于1000 万字分词
			sets = analyzer.parserString(ss); 
		}
		tc.end();;
		System.out.println("计时结束");
		tc.duration();
		System.out.println("分词效果如下");
		int j=0;
		for(int i = 0; i < sets.size(); i++){
			System.out.print(sets.get(i)+"  |  ");
			j++;
			if(j>15) {//每行15个词语输出
				j=0;
				System.out.println("");
			}
		}
	}
}
