package org.tinos.behavior.ortho.fhmm;
import java.io.IOException;
import java.util.Map;
public interface BehaviorMap{
	Map<String, String> initBusinessMap() throws IOException;
	Map<String, String> initTradeMap() throws IOException;
	Map<String, String> initFacilityMap() throws IOException;
	Map<String, String> initAckuisitionMap() throws IOException;
	Map<String, String> initCoorpoerationMap() throws IOException;
	Map<String, String> initPromiseMap() throws IOException;
}