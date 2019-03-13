package org.tinos.sensing.test;
import java.io.IOException;
import NLPProcessor.DETA_ANN_HMM;
public class ANNTest{
	public static void main(String[] argv) throws IOException, InstantiationException, IllegalAccessException {
		SensingTest sensingTest = new SensingTest();
		//DNN MAP
		String[][] preAnnMatrix = sensingTest.getMatrix();
		String[][] AnnMatrix = new DETA_ANN_HMM().summingProcessor(preAnnMatrix);	
		for(int j = 0; j < AnnMatrix.length; j++) {
			for(int i = 0; i < AnnMatrix[0].length; i++) {
				System.out.print(" " + AnnMatrix[j][i]);
			}
			System.out.println("");
		}
		//DNN REDUCE
		for(int j = 0; j < preAnnMatrix.length; j++) {
			double sum = 0;
			for(int i = 7; i < preAnnMatrix[0].length; i++) {
				sum += Double.valueOf(preAnnMatrix[j][i]);
			}
			//sum=(int)Math.log(sum);
			if(sum >= 0.6) {
				System.out.println(preAnnMatrix[j][0]+preAnnMatrix[j][1] + preAnnMatrix[j][2]+preAnnMatrix[j][3]+"ANN:" + sum);
			}
		}
	}
}