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
		analyzer.initMixed();
		Map<String, String> nlp = analyzer.getPosCnToCn();
		List<String> sets = new ArrayList<>();
		String[] ss = new String[1];
		String[] ss1 = new String[1];
		ss[0] = "是否定的";
		ss1[0] = "나는 일을 준비 하고 있다 ";
		for (int i = 0; i < ss.length; i++) {
			System.out.println("超级变态复杂病句-->" + ss[i]);
			sets = analyzer.parserMixedString(ss[i].replace("  ", " "));//词性分析
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
		
		String vv="生肖兔之前生活虽然过得不是特别的富裕，但至少日子过得还算舒心，没遇到过什么较大的意外，这对于生肖的人而言也算是一个不错的结果。但生肖兔的人毕竟是一个很有福气的属相，所以在6月开始，生肖兔的人命理之中财运提升，到时候生肖兔的人只要抓住机会，自~!@#$%^&*()_+`1234567890-= {}:\"|?><[]\\';,./'。，、＇：∶；?‘’“”〝〞ˆˇ﹕︰﹔﹖﹑·¨….¸;！´？！～—ˉ｜‖＂〃｀@﹫¡¿﹏﹋﹌︴々﹟#﹩$﹠&﹪%*﹡﹢﹦﹤‐￣¯―﹨ˆ˜﹍﹎+=<­­＿_-\\ˇ~﹉﹊（）〈〉‹›﹛﹜『』〖〗［］《》〔〕{}「」【】︵︷︿︹︽_﹁﹃︻︶︸﹀︺︾ˉ﹂﹄︼";
		for(int i=0;i<vv.length();i++) {
			//out.println((int)(vv.charAt(i)));
		}
	}
}