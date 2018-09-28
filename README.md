# Fast-Chinese-NeroParser
#
#版本号：2.2.3
#
#一种 基于 《神经森林网络》《欧基里德加权》《2分法位运算》 《索引树木搜索》 的 中文分词包 每秒高达1050万中文简体字准确分词。大小55Kb，作者：罗瑶光
本人定义协议为BSD, 可任意集成到任何公司组织个人项目中。谢谢。
#
#使用方法：
#1 支持 java JDK 8 以上
#2 大家可以自由添加词汇，添加在 org/tinos/fhmm/imp/words.lyg文件里。
#2.1 大家可以自由添加 三连词 词汇的前后缀 动名与介词区分，添加在 org/tinos/fhmm/imp/ogld.lyg文件里。

#3 可以看下org/tinos/test里面的例子。
#
#使用如下：
#   //1 实例化
    //Analyzer analyzer=new PrettyAnalyzerImp() ;//线性森林加权分析
    Analyzer analyzer=new BinaryForestAnalyzerImp() ;//哈希森林加权分析
#   //2初始
    analyzer.init();
#   //3 创建字符串 utf 8
#   String ss = "如果从容易开始于是从容不迫天下等于是非常识时务必为俊杰沿海南方向逃跑他说的确实在理结婚的和尚未结婚的提高产品质量中外科学名著内科学是临床医学的基础    内科学作为临床医学的基础学科，重点论述人体各个系统各种疾病的病因、发病机制、临床表现、诊断、治疗与预防";
#
#   //4 执行
    List<String> sets = analyzer.parserString(ss); 
#   //5 输出
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
#效果：如果  |  从  |  容易  |  开始  |  于是  |  从容不迫  |  天下  |  等于  |  是非  |  常识  |  时务  |  必  |  为  |  俊杰  |  沿  |  海南  |  方向  |  逃跑  |  他  |  说的  |  确实  |  在理  |  结婚  |  的  |  和  |  尚未  |  结婚  |  的  |  提高  |  产品  |  质量  |  中外  |  
科学  |  名著  |  内科学  |  是  |  临床  |  医学  |  的  |  基础  |  内科学  |  作为  |  临床  |  医学  |  的  |  基础  |  学科  |  重点  |  
论述  |  人体  |  各个  |  系统  |  各  |  
#
#有疑问联系313699483@qq.com 罗瑶光
#谢谢！
#2018 09 29
