package org.tinos.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tinos.engine.Analyzer;
import org.tinos.engine.imp.PrettyAnalyzerImp;

import timeProcessor.TimeCheck;
public class demoex{
	public static void main(String args[]) throws IOException{
		Analyzer analyzer=new PrettyAnalyzerImp() ;
		analyzer.init();
		//返回分词数据集合
		List<String> sets = new ArrayList<String>();
		TimeCheck tc= new TimeCheck();
		String ss = "明月几时有，把酒问青天，不知天上宫阙，今夕是何年，"
 		+"    〔治疗〕户，照无眠，不应有恨，和是偏向别时圆，人有悲欢离合，月有阴晴圆缺"
				+ "此事古难全，但愿人常久，千里共婵娟， 从容易开始";
		ss=ss.replace("\r\n", "");
		tc.begin();
		for(int i=0;i<400;i++) {
			sets = analyzer.parserString(ss); 
		}
		tc.end();;
		int j=0;
		for(int i = 0; i < sets.size(); i++){
			System.out.print(sets.get(i)+"-");
			j++;
			if(j>25) {
				j=0;
				System.out.println("");
			}
		}
		tc.duration();
	}
}