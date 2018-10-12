package org.tinos.engine.analysis;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Analyzer {
    void init() throws IOException;

    List<String> parserString(String input);

    Map<String, String> getWord() throws IOException;
}