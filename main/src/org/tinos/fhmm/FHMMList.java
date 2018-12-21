package org.tinos.ortho.fhmm;
import org.tinos.view.obj.FMHMMNode;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public interface FHMMList {
	void index() throws IOException;
	void indexPosEnToCn() throws IOException;  
	void indexPosEnToEn() throws IOException;  
	void indexEnToCn() throws IOException;  
	void indexCnToEn() throws IOException;  
	void indexFullEnToCn() throws IOException;
	void indexFullCnToEn() throws IOException;
	Map<String, FMHMMNode> getMap();
	Map<String, String> getPosEnToCn(); 
	Map<String, String> getPosEnToEn(); 
	Map<String, String> getPosCnToCn(); 
	Map<String, String> getEnToCn(); 
	Map<String, String> getCnToEn(); 
	Map<String, String> getFullEnToCn(); 
	Map<String, String> getFullCnToEn();
	List<String> englishStringToWordsList(String string); 
}