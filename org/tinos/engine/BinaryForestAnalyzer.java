package org.tinos.engine;
import java.util.List;
public interface BinaryForestAnalyzer extends Analyzer{
	public List<String> parserString(String input);
	public void parserStringWithType(String[] types, String string);
}

 
