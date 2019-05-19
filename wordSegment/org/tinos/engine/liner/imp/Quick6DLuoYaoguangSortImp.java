package org.tinos.engine.liner.imp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.tinos.engine.liner.Quick6DLuoYaoguangSort;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;
/*
** 快排6小高峰修正算法 作者 罗瑶光
*/
public class Quick6DLuoYaoguangSortImp implements Quick6DLuoYaoguangSort {
	public void quick6DLuoYaoGuangSortWordFrequency(List<WordFrequency> list, int leftPosition, int rightPosition) {
		if (leftPosition < rightPosition) {
			int c = rightPosition - leftPosition + StableData.INT_ONE;
			if (c < StableData.INT_FOUR) {
				int j;
				for (int i = StableData.INT_ONE + leftPosition; i < leftPosition + c; i++) {
					j = i;
					while (j >= StableData.INT_ONE + leftPosition) {
						if (list.get(j).getFrequency() < list.get(j - StableData.INT_ONE).getFrequency()) {
							WordFrequency wordFrequency = list.get(j);
							list.set(j, list.get(j - StableData.INT_ONE));
							list.set(j - StableData.INT_ONE, wordFrequency);
						}
						j--;
					}
				}
				return;
			}
			int pos = partition(list, leftPosition, rightPosition);
			quick6DLuoYaoGuangSortWordFrequency(list, leftPosition, pos - StableData.INT_ONE);
			quick6DLuoYaoGuangSortWordFrequency(list, ++ pos, rightPosition);
		}
	}

	public int partition(List<WordFrequency> list, int leftPosition, int rightPosition) {
		int rightPositionNew= rightPosition;
		int leftPositionNew= leftPosition;
		WordFrequency wordFrequencyX= list.get(leftPosition);
		WordFrequency wordFrequencyY= list.get(rightPosition);
		//小高峰修正边缘均衡开始
		if (wordFrequencyX.getFrequency()<= wordFrequencyY.getFrequency()) {
			wordFrequencyY= wordFrequencyX;
		}
		//小高峰修正边缘均衡结束
		while (leftPositionNew< rightPositionNew) {
			while ((list.get(leftPositionNew).getFrequency()<= wordFrequencyY.getFrequency())
					&& (leftPositionNew< rightPositionNew)){
				leftPositionNew++;
			} 
			while (list.get(rightPositionNew).getFrequency()> wordFrequencyY.getFrequency()) {
				rightPositionNew--;
			}  
			if (leftPositionNew< rightPositionNew){
				WordFrequency wordFrequency= list.get(rightPositionNew);
				list.set(rightPositionNew, list.get(leftPositionNew));
				list.set(leftPositionNew, wordFrequency);
			}
		}
		list.set(leftPosition, list.get(rightPositionNew));
		list.set(rightPositionNew, wordFrequencyY);
		return rightPositionNew;
	}

	@SuppressWarnings(StableData.RAW_TYPES)
	public List<WordFrequency> frequencyWordMapToList(Map<String, WordFrequency> map) {
		List<WordFrequency> list= new ArrayList<>();
		Iterator iterator= map.keySet().iterator();
		while (iterator.hasNext()) {
			list.add(map.get(iterator.next()));
		}
		return list;
	}

	@Override
	public void quick6DLuoYaoGuangSortWordFrequency(Map<Integer, WordFrequency> map, int leftPosition,
			int rightPosition) {
	}

	@Override
	public int partition(Map<Integer, WordFrequency> map, int leftPosition, int rightPosition) {
		return StableData.INT_ZERO;
	}

	@Override
	public Map<Integer, WordFrequency> frequencyWordMapToMap(Map<String, WordFrequency> map) {
		return null;
	}
}
