package org.tinos.engine;
import java.io.IOException;
import java.util.List;
public abstract interface Analyzer {
	public void init() throws IOException;
	public List<String> parserString(String input);
}

 
