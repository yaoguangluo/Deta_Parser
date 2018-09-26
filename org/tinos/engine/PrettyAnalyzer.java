package org.tinos.engine;
import java.util.List;
public interface PrettyAnalyzer extends Analyzer{
	public List<String> parserString(String input);
	public void parserStringWithType(String[] types, String string);
}

 
