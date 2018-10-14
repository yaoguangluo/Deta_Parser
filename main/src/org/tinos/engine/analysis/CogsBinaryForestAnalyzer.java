package org.tinos.engine.analysis;

import java.io.IOException;
import java.util.List;

import org.tinos.view.obj.WordFrequency;

public interface CogsBinaryForestAnalyzer extends BinaryForestAnalyzer {
    List<WordFrequency> getWordFrequency(List<String> sets) throws IOException;
}