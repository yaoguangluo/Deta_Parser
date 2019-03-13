package org.tinos.emotion.ortho.fhmm;
import java.io.IOException;
import java.util.Map;
public interface EmotionMap{
	void initMotivationMap() throws IOException;
	void initPositiveMap() throws IOException;
	void initNegativeMap() throws IOException;
	void initTrendingMap() throws IOException;
	void initPredictionMap() throws IOException;
	void initDistinctionMap() throws IOException;
	public Map<String, Object> getPositiveMap();
	public void setPositiveMap(Map<String, Object> positiveMap);
	public Map<String, Object> getNegativeMap();
	public void setNegativeMap(Map<String, Object> negativeMap);
	public Map<String, Object> getMotivationMap() ;
	public void setMotivationMap(Map<String, Object> motivationMap);
	public Map<String, Object> getTrendingMap() ;
	public void setTrendingMap(Map<String, Object> trendingMap);
	public Map<String, Object> getPredictionMap() ;
	public void setPredictionMap(Map<String, Object> predictionMap);
	public Map<String, Object> getDistinctionMap() ;
	public void setDistinctionMap(Map<String, Object> distinctionMap);
}