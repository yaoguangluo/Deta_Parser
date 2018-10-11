package org.tinos.ortho.fhmm;
import java.io.IOException;
import java.util.Map;
import org.tinos.view.stable.StableData;
public abstract interface FHMMList{
	public void index() throws IOException;
	public void setWords(Map<String, String> words);
	public Map<String, String> getWords();
	@SuppressWarnings(StableData.RAW_TYPES)
	public Map<Integer, Map> getRoot();
	@SuppressWarnings(StableData.RAW_TYPES)
	public Map<Integer, Map> getLinkedHashMapRoot();
}