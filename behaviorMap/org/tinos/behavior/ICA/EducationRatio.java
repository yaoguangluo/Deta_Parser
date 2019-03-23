package org.tinos.behavior.ICA;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tinos.engine.analysis.Analyzer;
import org.tinos.engine.analysis.imp.CogsBinaryForestAnalyzerImp;
import org.tinos.view.obj.WordFrequency;
import org.tinos.view.stable.StableData;

public class EducationRatio{

	public double[] getEducationKernel(String text) throws IOException {
		Analyzer analyzer = new CogsBinaryForestAnalyzerImp();
		analyzer.init();
		Map<String, String> pos = analyzer.getPosCnToCn();
		List<String> sets = analyzer.parserString(text);
		Map<Integer, WordFrequency> map = analyzer.getWordFrequencyByReturnSortMap(sets);
		double[] output = new double[7];
		output[0] = sets.size();
		Iterator<Integer> iterator = map.keySet().iterator();
		Here:
		while(iterator.hasNext()) {
			WordFrequency wordFrequency = map.get(iterator.next());
			if(!pos.containsKey(wordFrequency.getWord())) {
				continue Here;
			}
			if(pos.get(wordFrequency.getWord()).contains(StableData.NLP_ZI_MING)){
				output[1]+=1;
				continue Here;
			}
			if(pos.get(wordFrequency.getWord()).contains(StableData.NLP_ZI_DONG)){
				output[2]+=1;
				continue Here;
			}
			if(pos.get(wordFrequency.getWord()).contains(StableData.NLP_ZI_WEI)){
				output[4]+=1;
				continue Here;
			}
			if(pos.get(wordFrequency.getWord()).contains(StableData.NLP_ZI_XING)){
				output[3]+=1;
				continue Here;
			}
			if(pos.get(wordFrequency.getWord()).contains(StableData.NLP_ZI_FU)){
				output[5]+=1;
				continue Here;
			}
			if(pos.get(wordFrequency.getWord()).contains(StableData.NLP_ZI_JIE)){
				output[6]+=1;
			}
		}	
		return output;
	}
	
	public double[] getEducationRatioKernel(double[] input) {
		double[] output=new double[input.length];
		for(int i=0;i<input.length;i++) {
			output[i]=input[i]/input[0];
		}
		return output;
	}
}