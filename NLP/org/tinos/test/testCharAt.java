package org.tinos.test;

//import timeProcessor.TimeCheck;


@SuppressWarnings("unused")
public class testCharAt {
	public static void main(String[] args) throws InterruptedException {

		String aa="dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
//		TimeCheck t = new TimeCheck();
//		t.begin();
		for(int i=0;i<10000000;i++) {
			for(int j=0;j<aa.length();j++) {
				int k = aa.charAt(j);
			}	
		}
//		t.end();
//		t.duration();
	}
}