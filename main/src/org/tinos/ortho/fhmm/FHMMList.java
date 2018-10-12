package org.tinos.ortho.fhmm;

import org.tinos.view.stable.StableData;

import java.io.IOException;
import java.util.Map;

public interface FHMMList {
    void index() throws IOException;

    Map<String, String> getWords();

    @SuppressWarnings(StableData.RAW_TYPES)
    Map<Integer, Map> getRoot();

}