package org.tinos.test;
import java.util.HashMap;
import java.util.Map;
public class EffectionDemo{
	public static void main(String[] argv) {
		TimeCheck timeCheck = new TimeCheck();
		//titan sets
		System.out.println(1);
		Map<String,String> map = new HashMap<>();
		for(int i=0; i<100000; i++) {
			map.put(""+i, ""+i);
		}
		System.out.println(2);
		//subset
		Map<String,String> map1 = new HashMap<>();
		for(int i=0; i<30000; i++) {
			map1.put(""+i, ""+i);
		}
		System.out.println(3);
		Map<String,String> map2 = new HashMap<>();
		for(int i=0; i<30000; i++) {
			map2.put(""+i, ""+i);
		}
		System.out.println(4);
		Map<String,String> map3 = new HashMap<>();
		for(int i=0; i<30000; i++) {
			map3.put(""+i, ""+i);
		}
//		System.out.println(5);
//		Map<String,String> map4 = new HashMap<>();
//		for(int i=0; i<20000; i++) {
//			map4.put(""+i, ""+i);
//		}
//		System.out.println(6);
//		Map<String,String> map5 = new HashMap<>();
//		for(int i=0; i<20000; i++) {
//			map5.put(""+i, ""+i);
//		}
		System.out.println(7);
		//time check
		timeCheck.begin();
		function1(map);
		timeCheck.end();
		timeCheck.duration();
		//
		timeCheck.begin();
		function2(map1,map2,map3);
		timeCheck.end();
		timeCheck.duration();
	}

	private static void function2(Map<String, String> map1, Map<String, String> map2, Map<String, String> map3) {
		Here:
		for(int i=0; i<100000; i++) {
			int digits= (int)(Math.random()*30000);
			if(map1.containsKey(digits+"")) {	
				continue Here;
			}
			if(map2.containsKey(digits+"")) {	
				continue Here;
			}
			if(map3.containsKey(digits+"")) {	
				continue Here;
			}
		}
	}

	private static void function1(Map<String, String> map) {
		Here1:
		for(int i=0; i<100000; i++) {
			int digits= (int)(Math.random()*30000);
			if(map.containsKey(digits+"")) {
				continue Here1;
			}
		}
	}
}