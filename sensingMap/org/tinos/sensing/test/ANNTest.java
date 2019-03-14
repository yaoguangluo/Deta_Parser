package org.tinos.sensing.test;
import java.io.IOException;
import NLPProcessor.DETA_ANN_HMM;
public class ANNTest{
	public static void main(String[] argv) throws IOException, InstantiationException, IllegalAccessException {
		ANNTest ANNTest = new ANNTest();
		String[][] AnnMatrix = ANNTest.getANNMatrix();
		for(int j = 0; j < AnnMatrix.length; j++) {
			double sum = 0;
			for(int i = 7; i < AnnMatrix[0].length; i++) {
				sum += Double.valueOf(AnnMatrix[j][i]);
			}
			if(sum >= 0.6) {
				System.out.println(AnnMatrix[j][0]+AnnMatrix[j][1] + AnnMatrix[j][2]+AnnMatrix[j][3]+"ANN:" + sum);
			}
		}
	}
	
	public String[][] getANNMatrix() throws IOException, InstantiationException, IllegalAccessException{
		SensingTest sensingTest = new SensingTest();
		//SUM OF ANN MAP CULUMN KERNEL
		String[][] preAnnMatrix = sensingTest.getMatrix();
		String[][] AnnMatrix = new DETA_ANN_HMM().summingProcessor(preAnnMatrix);	
		for(int j = 0; j < AnnMatrix.length; j++) {
			for(int i = 0; i < AnnMatrix[0].length; i++) {
				System.out.print(" " + AnnMatrix[j][i]);
			}
			System.out.println("");
		}
		return AnnMatrix;
	}
}