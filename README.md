# Fast-Chinese-NeroParser
#
#版本号：1.0
#
#一种 基于 神经 森林 网络 欧几里德加权 的 中文分词 包 每秒400万中文简体字准确分词。大小90Kb，作者：罗瑶光
本人定义 协议 为 BSD, 可任意集成到任何公司组织个人。谢谢。
#
#使用方法：
#1 支持 java JDK 8 以上
#2 大家可以自由添加词汇，添加在 org/tinos/fhmm/words.lyg文件里。
#3 可以看下org/tinos/test里面的例子。
#
#使用如下：
 #   //1 实例化
    Analyzer analyzer=new PrettyAnalyzerImp() ;
#		//2初始
    analyzer.init();
#		//3 创建字符串 utf 8
#		String ss = "明月几时有，把酒问青天，不知天上宫阙，今夕是何年，"
 		    +"〔治疗〕户，照无眠，不应有恨，和是偏向别时圆，人有悲欢离合，月有阴晴圆缺"
				+ "此事古难全，但愿人常久，千里共婵娟， 从容易开始";
#		ss=ss.replace("\r\n", "");
 #   //4 执行
		List<String> sets sets = analyzer.parserString(ss); 
#	  //5 输出
    int j=0;
		for(int i = 0; i < sets.size(); i++){
			System.out.print(sets.get(i)+"-");
			j++;
			if(j>25) {
				j=0;
				System.out.println("");
			}
		}

#
#
#有疑问联系313699483@qq.com 罗瑶光
#谢谢！
#2018 09 26
