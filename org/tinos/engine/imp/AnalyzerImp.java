package org.tinos.engine.imp;
import java.io.IOException;
import java.util.List;
import org.tinos.engine.Analyzer;
import org.tinos.fhmm.FDHMMList;
import org.tinos.fhmm.NeroFeedHMM;
public abstract class AnalyzerImp implements Analyzer{
	public FDHMMList fDHMMList;
	public NeroFeedHMM neroFeedHMM;
	public void init() throws IOException {
	}
	
	public List<String> parserString(String input) {
		return null;
	}

	public void parserStringWithType(String[] types, String string) {
	}
}

 
