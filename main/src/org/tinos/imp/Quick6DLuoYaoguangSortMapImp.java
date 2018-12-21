package org.tinos.engine.liner.imp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.tinos.engine.liner.Quick6DLuoYaoguangSort;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
public class Quick6DLuoYaoguangSortMapImp implements Quick6DLuoYaoguangSort {
	@Override
	public void quick6DLuoYaoGuangSortWordFrequency(Map<Integer, WordFrequency> map, int leftPosition,
			int rightPosition) {
		if (leftPosition < rightPosition) {
			int c = rightPosition - leftPosition + StableData.INT_ONE;
			if (c < StableData.INT_FOUR) {
				int j;
				for (int i = StableData.INT_ONE + leftPosition; i < leftPosition + c; i++) {
					j = i;
					while (j >= StableData.INT_ONE + leftPosition) {
						if (map.get(j).getFrequency() < map.get(j - StableData.INT_ONE).getFrequency()) {
							WordFrequency wordFrequency = map.get(j);
							map.put(j, map.get(j - StableData.INT_ONE));
							map.put(j - StableData.INT_ONE, wordFrequency);
						}
						j--;
					}
				}
				return;
			}
			int pos = partition(map, leftPosition, rightPosition);
			quick6DLuoYaoGuangSortWordFrequency(map, leftPosition, pos - StableData.INT_ONE);
			quick6DLuoYaoGuangSortWordFrequency(map, ++ pos, rightPosition);
		}	
	}

	@Override
	public int partition(Map<Integer, WordFrequency> map, int leftPosition, int rightPosition) {
		int rightPositionNew = rightPosition;
		int leftPositionNew = leftPosition;
		WordFrequency wordFrequencyX = map.get(leftPosition);
		WordFrequency wordFrequencyY = map.get(rightPosition);
		if (wordFrequencyX.getFrequency() <= wordFrequencyY.getFrequency()) {
			wordFrequencyY = wordFrequencyX;
		}
		while (leftPositionNew < rightPositionNew) {
			while ((map.get(leftPositionNew).getFrequency() <= wordFrequencyY.getFrequency())
					&& (leftPositionNew < rightPositionNew)) {
				leftPositionNew++;
			} 
			while (map.get(rightPositionNew).getFrequency() > wordFrequencyY.getFrequency()) {
				rightPositionNew--;
			}  
			if (leftPositionNew < rightPositionNew) {
				WordFrequency wordFrequency = map.get(rightPositionNew);
				map.put(rightPositionNew, map.get(leftPositionNew));
				map.put(leftPositionNew, wordFrequency);
			}
		}
		map.put(leftPosition, map.get(rightPositionNew));
		map.put(rightPositionNew, wordFrequencyY);
		return rightPositionNew;
	}

	@SuppressWarnings(StableData.RAW_TYPES)
	public Map<Integer, WordFrequency> frequencyWordMapToMap(Map<String, WordFrequency> map) {
		Map<Integer, WordFrequency> listMap = new ConcurrentHashMap<>();
		Iterator iterator = map.keySet().iterator();
		int c = 0;
		while (iterator.hasNext()) {
			listMap.put(c++,map.get(iterator.next()));
		}
		return listMap;
	}

	public void quick6DLuoYaoGuangSortWordFrequency(List<WordFrequency> list, int leftPosition, int rightPosition) {
	}

	public int partition(List<WordFrequency> list, int leftPosition, int rightPosition) {
		return StableData.INT_ZERO;
	}

	public List<WordFrequency> frequencyWordMapToList(Map<String, WordFrequency> map) {
		return null;
	}
}