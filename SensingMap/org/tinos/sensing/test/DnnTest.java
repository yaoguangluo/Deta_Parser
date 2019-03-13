package org.tinos.sensing.test;
import java.io.IOException;
import NLPProcessor.DETA_DNN_HMM;
public class DnnTest{
	public static void main(String[] argv) throws IOException, InstantiationException, IllegalAccessException {
		SensingTest sensingTest = new SensingTest();
		//DNN MAP
		String[][] preDnnMatrix = sensingTest.getMatrix();
		String[][] DnnMatrix = new DETA_DNN_HMM().summingProcessor(preDnnMatrix);	
		for(int j = 0; j < DnnMatrix.length; j++) {
			for(int i = 0; i < DnnMatrix[0].length; i++) {
				System.out.print(" " + DnnMatrix[j][i]);
			}
			System.out.println("");
		}
		//DNN REDUCE
		for(int j = 0; j < preDnnMatrix.length; j++) {
			double sum = 0;
			for(int i = 7; i < preDnnMatrix[0].length; i++) {
				sum += Double.valueOf(preDnnMatrix[j][i]);
			}
			//sum=(int)Math.log(sum);
			if(sum >= 0.5) {
				System.out.println(preDnnMatrix[j][0]+preDnnMatrix[j][1] + preDnnMatrix[j][2]+preDnnMatrix[j][3]+"DNN:" + sum);
			}
		}
	}
}