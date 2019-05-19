package org.tinos.sensing.test;
import java.io.IOException;

import org.tinos.engine.analysis.Analyzer;

import NLPProcessor.DETA_DNN;
public class DNNTest{
	public static void main(String[] argv) throws IOException, InstantiationException, IllegalAccessException {
		DNNTest dNNTest=new DNNTest();
		ANNTest aNNTest = new ANNTest();
		String[][] ann = aNNTest.getANNMatrix();
		String[][] dnn = dNNTest.getDNNMatrix(ann);
//		String[][] ann = aNNTest.getANNMatrix(string, analyzer);
//		String[][] dnn = dNNTest.getDNNMatrix(ann, analyzer, string);
		for(int i=0;i<dnn.length;i++) {
			double dnn_lwa = Double.parseDouble(dnn[i][3]);
			if(dnn_lwa>100) {
				System.out.print(ann[i][0] + ":");
				System.out.print(ann[i][1] + ":");
				System.out.print(ann[i][2] + ":");
				System.out.print(ann[i][3] + ":");
				System.out.print(dnn[i][0] + ":");
				System.out.print(dnn[i][3] + ":");
				System.out.println("");
			}
		}
	}

	public String[][] getDNNMatrix() throws IOException, InstantiationException, IllegalAccessException{
		ANNTest aNNTest = new ANNTest();
		RNN_IDETest rNN_IDETest = new RNN_IDETest();
		String[][] dNNMatrix = new DETA_DNN().summingProcessor(aNNTest.getANNMatrix(), rNN_IDETest.getIDEMatrix());	
		return dNNMatrix;
	}
	
	public String[][] getDNNMatrix(String[][] ann) throws IOException, InstantiationException, IllegalAccessException{
		RNN_IDETest rNN_IDETest = new RNN_IDETest();
		String[][] dNNMatrix = new DETA_DNN().summingProcessor(ann, rNN_IDETest.getIDEMatrix());	
		return dNNMatrix;
	}
	public String[][] getDNNMatrix(String[][] ann, Analyzer analyzer, String string) throws IOException, InstantiationException
	, IllegalAccessException{
		RNN_IDETest rNN_IDETest = new RNN_IDETest();
		String[][] dNNMatrix = new DETA_DNN().summingProcessor(ann, rNN_IDETest.getIDEMatrixExcludeAnalyzer(analyzer, string));	
		return dNNMatrix;
	}
}