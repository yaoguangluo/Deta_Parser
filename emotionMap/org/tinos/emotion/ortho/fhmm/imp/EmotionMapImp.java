package org.tinos.emotion.ortho.fhmm.imp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.tinos.emotion.ortho.fhmm.EmotionMap;
import org.tinos.view.stable.StableData;

public class EmotionMapImp implements EmotionMap{
	private Map<String, Object> positiveMap;
	private Map<String, Object> negativeMap;
	private Map<String, Object> motivationMap;
	private Map<String, Object> trendingMap;
	
	public Map<String, Object> getPositiveMap() {
		return positiveMap;
	}

	public void setPositiveMap(Map<String, Object> positiveMap) {
		this.positiveMap = positiveMap;
	}

	public Map<String, Object> getNegativeMap() {
		return negativeMap;
	}

	public void setNegativeMap(Map<String, Object> negativeMap) {
		this.negativeMap = negativeMap;
	}

	public Map<String, Object> getMotivationMap() {
		return motivationMap;
	}

	public void setMotivationMap(Map<String, Object> motivationMap) {
		this.motivationMap = motivationMap;
	}

	public Map<String, Object> getTrendingMap() {
		return trendingMap;
	}

	public void setTrendingMap(Map<String, Object> trendingMap) {
		this.trendingMap = trendingMap;
	}

	public void initPositiveMap() throws IOException{
		positiveMap = new HashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_POSITIVE);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		while ((cInputString = cReader.readLine()) != null) {
			if(!positiveMap.containsKey(cInputString)) {
				positiveMap.put(cInputString, true);
			}
		}
		cReader.close();
	}
	
	public void initMotivationMap() throws IOException{
		motivationMap = new HashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_MOTIVATION);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		while ((cInputString = cReader.readLine()) != null) {
			String[] value = cInputString.split(StableData.NLP_SYMBO_SLASH);
			if(!motivationMap.containsKey(value[StableData.INT_ZERO])) {
				motivationMap.put(value[StableData.INT_ZERO], value[StableData.INT_ONE]);
			}
		}
		cReader.close();
	}
	
	public void initTrendingMap() throws IOException{
		trendingMap = new HashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_TRENDING);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		while ((cInputString = cReader.readLine()) != null) {
			String[] value= cInputString.split(StableData.NLP_SYMBO_SLASH);
			if(!trendingMap.containsKey(value[StableData.INT_ZERO])) {
				trendingMap.put(value[StableData.INT_ZERO], value[StableData.INT_ONE]);
			}
		}
		cReader.close();
	}
	
	public void initNegativeMap() throws IOException{
		negativeMap = new HashMap<>();
		InputStream in = getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_NEGATIVE);
		BufferedReader cReader = new BufferedReader(new InputStreamReader(in, StableData.UTF8_STRING));
		String cInputString;
		while ((cInputString = cReader.readLine()) != null) {
			if(!negativeMap.containsKey(cInputString)) {
				negativeMap.put(cInputString, true);
			}
		}
		cReader.close();
	}
}