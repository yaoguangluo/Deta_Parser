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
import org.tinos.engine.imp.FastAnalyzerImp;
import org.tinos.engine.imp.PrettyAnalyzerImp;
import timeProcessor.TimeCheck;
@SuppressWarnings("unused")
public class DemoexCogs{
	static List<List<String>> sets ;
	static String ss;
	static int c=0;
	public static void main(String []args) throws IOException, InterruptedException{
		Analyzer analyzer = new BinaryForestAnalyzerImp();  //哈希森林索引
		//Analyzer analyzer = new FastAnalyzerImp();        //快速线性索引
		//Analyzer analyzer = new PrettyAnalyzerImp();      //线性森林索引
		//Analyzer analyzer = new BaseAnalyzerImp();        //一元线性索引
		//Analyzer analyzer = new ScoreAnalyzerImp();       //森林打分索引
		analyzer.init();
		//返回分词数据集合
		sets =new CopyOnWriteArrayList<>();
		TimeCheck tc = new TimeCheck();
		String ss = "如果从容易开始于是从容不迫天下等于是非常识时务必为俊杰沿海南方向逃跑他说的确实在"
				  + "结婚的和尚的提高产品质量中外科学名著内科学是临床医学的基础 内科学作为临床医未结婚"
				  +"如果从容易开始于是从容不迫天下等于是非常识时务必为俊杰沿海南方向逃跑他说的确实在"
				  + "结婚的和尚未结婚的提高产品质量中外科学名著内科学是临床医学的基础 内科学作为临床医"
				  + "结婚的和尚未结婚的提高产品质量中外科学名著内科学是临床医学的基础 内科学作为临床医"
				  +"如果从容易开始于是从容不迫天下等于是非常识时务必为俊杰沿海南方向逃跑他说的确实在"
				  + "结婚的和尚的提高产品质量中外科学名著内科学是临床医学的基础 内科学作为临床医未结婚"
				  +"如果从容易开始于是从容不迫天下等于是非常识时务必为俊杰沿海南方向逃跑他说的确实在"
				  + "结婚的和尚未结婚的提高产品质量中外科学名著内科学是临床医学的基础 内科学作为临床医"
				  + "结婚的和尚未结婚的提高产品质量中外科学名著内科学是临床医学的基础 内科学作为临床医"; //大概400字
		System.out.println("");
		System.out.println("计时开始");
		ExecutorService eXECUTOR_SERVICE = Executors.newFixedThreadPool(1);	
		tc.begin();	
		for(int i = 0; i < 25000; i++) { //重复执行 10万次 相当于1000 万字分词
			eXECUTOR_SERVICE.submit(new TaskWithResult(i, analyzer, ss));
		}
		while(sets.size() < 25000) {//拿到4000万总数线程跳出
			Thread.sleep(300);
		}
		tc.end();
		System.out.println("计时结束"+sets.size());
		tc.duration();
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