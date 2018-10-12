package org.tinos.engine.nero;

import org.tinos.view.obj.FMHMMNode;
import org.tinos.view.stable.StableData;

import java.util.Map;

public interface NEROController {
    @SuppressWarnings(StableData.RAW_TYPES)
    String getBinaryForestRecurWord(String inputStringWordNode, String inputString, int charPosition
            , int inputStringLength, Map<Integer, Map> forestRoots, int forestDepth);

    @SuppressWarnings(StableData.RAW_TYPES)
    String doBinaryForestRecurWordKernel(String inputString, FMHMMNode fFHMMNode, int length, String input
            , int i, Map<Integer, Map> roots, int forestDepth);
}
