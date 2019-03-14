package org.tinos.sensing.ortho.fhmm;
import java.io.IOException;
import java.util.Map;
import org.tinos.engine.analysis.Analyzer;
public interface SensingMap{
	void initLenovoMap(Analyzer analyzer) throws IOException;
	public Map<String, Object> getLenovoMap();
	public void setLenovoMap(Map<String, Object> lenovoMap);
}