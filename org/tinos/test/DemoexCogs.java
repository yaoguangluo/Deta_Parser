package org.tinos.test;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.tinos.engine.Analyzer;
import org.tinos.engine.imp.BinaryForestAnalyzerImp;
import org.tinos.engine.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.engine.imp.BaseAnalyzerImp;
import org.tinos.engine.imp.PrettyAnalyzerImp;
@SuppressWarnings("unused")
public class DemoexCogs{
	static List<List<String>> sets ;
	static String ss;
	static int c = 0;
	public static void main(String []args) throws IOException, InterruptedException{
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();  
		//Analyzer analyzer = new FastAnalyzerImp();      
		//Analyzer analyzer = new PrettyAnalyzerImp();    
		//Analyzer analyzer = new BaseAnalyzerImp();     
		//Analyzer analyzer = new ScoreAnalyzerImp();     
		analyzer.init();
		//返回分词数据集合
		sets =new CopyOnWriteArrayList<List<String>>();
		String ss =" 和尚未结婚"; //大概400字
		System.out.println("");
		System.out.println("计时开始");
		ExecutorService executorService = Executors.newFixedThreadPool(1);	

		for(int i = 0; i < 25000; i++) { //重复执行 10万次 相当于1000 万字分词
			executorService.submit(new TaskWithResult(i, analyzer, ss));
		}
		while(sets.size() < 25000) {//拿到4000万总数线程跳出
			Thread.sleep(300);
		}

		System.out.println("计时结束"+sets.size());

		System.out.println("分词效果如下");
		int j=0;
		for(int i = 0; i < sets.get(0).size(); i++){
			System.out.print(sets.get(0).get(i)+"  |  ");
			j++;
			if(j>15) {//每行15个词语输出
				j=0;
				System.out.println("");
			}
		}
	}

	public static class TaskWithResult implements Callable<String> {  
	    private int id;  
	    private Analyzer analyzer;  
	    private String sss;    
	    public TaskWithResult(int id, Analyzer analyzer, String sss) {  
	        this.id = id;  
	        this.analyzer = analyzer;  
	        this.sss = sss;  
	    } 
	    public String call() throws Exception {
	    	List<String> te = analyzer.parserString(sss);
	    	sets.add(te);
			return null;    
	    }  
	}  
	
}