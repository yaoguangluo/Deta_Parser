package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.tinos.engine.Analyzer;
import org.tinos.engine.imp.BinaryForestAnalyzerImp;
import org.tinos.engine.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.engine.imp.BaseAnalyzerImp;
import org.tinos.engine.imp.PrettyAnalyzerImp;
@SuppressWarnings("unused")
public class PartOfSpeech{
	public static void main(String []args) throws IOException{
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();  //哈希森林索引 多核线程安全 支持并发
		//Analyzer analyzer = new BinaryForestAnalyzerImp();  //哈希森林索引 单线程
		//Analyzer analyzer = new FastAnalyzerImp();        //快速线性索引 单线程
		//Analyzer analyzer = new PrettyAnalyzerImp();      //线性森林索引 单线程
		//Analyzer analyzer = new BaseAnalyzerImp();        //一元线性索引
		//Analyzer analyzer = new ScoreAnalyzerImp();       //森林打分索引
		analyzer.init();
		List<String> sets = new ArrayList<>();
		String ss = "这d 和尚m 未x 结婚d 和 尚未x 结婚d 的s 和尚m 未x 到d"  ;	
		System.out.println("");
		System.out.println("计时开始");
		long c=0;
		for(int i = 0; i < 10; i++) {//重复执行 4万次 相当于800 万字分词
			sets = analyzer.parserString(ss); 
		}
		System.out.println("计时结束");
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