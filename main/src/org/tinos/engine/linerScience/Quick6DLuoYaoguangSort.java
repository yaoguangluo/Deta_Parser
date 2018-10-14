package org.tinos.engine.linerScience;

import java.util.List;
import java.util.Map;

import org.tinos.view.obj.WordFrequency;

public interface Quick6DLuoYaoguangSort {

    void quick6DLuoYaoGuangSortWordFrequency(List<WordFrequency> list, int leftPosition, int rightPosition);

    int partition(List<WordFrequency> list, int leftPosition, int rightPosition);

    List<WordFrequency> frequencyWordMapToList(Map<String, WordFrequency> map);
}