package org.tinos.engine.analysis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.tinos.view.obj.WordFrequency;

public interface Analyzer {
    void init() throws IOException;

    List<String> parserString(String input);

    Map<String, String> getWord() throws IOException;
    
    List<WordFrequency> getWordFrequency(List<String> sets) throws IOException;
}