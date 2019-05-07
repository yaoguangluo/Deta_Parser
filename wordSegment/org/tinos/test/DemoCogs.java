package org.tinos.test;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
public class DemoCogs {
    static List<List<String>> sets;
    static String ss;
    static int c = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
        //Analyzer analyzer = new FastAnalyzerImp();
        //Analyzer analyzer = new PrettyAnalyzerImp();
        //Analyzer analyzer = new BaseAnalyzerImp();
        //Analyzer analyzer = new ScoreAnalyzerImp();
        analyzer.init();
        sets = new CopyOnWriteArrayList<>();
        String ss = "从容易开始从容易知";
        System.out.println("");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new TaskWithResult(i, analyzer, ss));
        }
        while (sets.size() < 100) {
            Thread.sleep(300);
        }
        int j = 0;
        for (int i = 0; i < sets.get(0).size(); i++) {
            System.out.print(sets.get(0).get(i) + "  |  ");
            j++;
            if (j > 15) {
                j = 0;
                System.out.println("");
            }
        }
    }

    public static class TaskWithResult implements Callable<String> {
        private int id;
        private Analyzer analyzer;
        private String sss;

        public TaskWithResult(int id, Analyzer analyzer, String sss) {
            this.id = id;
            this.analyzer = analyzer;
            this.sss = sss;
        }

        public String call() throws Exception {
            List<String> te = analyzer.parserString(sss);
            sets.add(te);
            return null;
        }
    }

}