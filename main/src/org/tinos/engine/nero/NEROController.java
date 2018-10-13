package org.tinos.engine.nero;

import org.tinos.view.stable.StableData;

import java.util.Map;

public interface NEROController {
    @SuppressWarnings(StableData.RAW_TYPES)
    StringBuilder getBinaryForestRecurWord(StringBuilder inputStringWordNode, String inputString, int charPosition
            , int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth);
}
