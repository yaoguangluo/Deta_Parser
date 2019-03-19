package org.tinos.test;

@SuppressWarnings("unused")
public class testCharAt {
	public static void main(String[] args) throws InterruptedException {

//		String aa="dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
//				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
//				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
//				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
//				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
//				+"dsadsadsaddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";
//		TimeCheck t = new TimeCheck();
//		t.begin();
//		for(int i=0;i<10000000;i++) {
//			for(int j=0;j<aa.length();j++) {
//				int k = aa.charAt(j);
//			}	
//		}
//		t.end();
//		t.duration();
		
//		char[] chars = new char[999999];
//		TimeCheck t = new TimeCheck();
//		t.begin();
//		for(int i=0; i<9000; i++) {
//			int c=0;
//			for(char a:chars) {
//				 chars[c++]=2;
//			}
//		}
//		t.end();
//		t.duration();
//		System.out.println(1+"-->"+chars[10000]);
		
		char[] chars = new char[999999];
		TimeCheck t = new TimeCheck();
		t.begin();
		for(int i=0;i<9000;i++) {
			for(int j=0;j<chars.length;j++) {
				chars[j]=3;
			}
		}
		t.end();
		t.duration();
		System.out.println(1+"-->"+chars[10000]);
	}
}