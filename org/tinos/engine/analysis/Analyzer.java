package org.tinos.engine.analysis;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public abstract interface Analyzer {
	public void init() throws IOException;
	public List<String> parserString(String input);
	public Map <String, String> getWord() throws IOException;
}