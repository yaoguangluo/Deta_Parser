//package org.tinos.ortho.fhmm.imp;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import org.tinos.view.stable.StableData;
//public class Filter{
//	public static void main(String[] argv) throws IOException {
//		Filter filter = new Filter();
////		filter.filterADJ();	
//		filter.filterNegative();
//	}
//	
//	public void filterADJ() throws IOException {
//		FileOutputStream fileOutputStream = new FileOutputStream(("C:\\Users\\Administrator\\Desktop\\deta\\detasource\\pospn\\pos.lyg"));
//		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//		String readLine = "";
//		BufferedReader fileInputStream = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(StableData.WORDS_SOURSE_LINK_POS_CN_TO_CN)));
//		while((readLine = fileInputStream.readLine()) != null) {	
//			if(readLine.contains("形")||readLine.contains("副")) {
//				bufferedWriter.write(readLine.split("/")[0]);
//				bufferedWriter.write("\r\n");
//			}
//		}
//		bufferedWriter.close();
//		fileInputStream.close();
//	}
//	
//	public void filterNegative() throws IOException {
//		FileOutputStream fileOutputStream = new FileOutputStream(("C:\\Users\\Administrator\\Desktop\\deta\\detasource\\pospn\\posNegative.lyg"));
//		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//
//		Map<String, String> pos= new HashMap<>();
//		BufferedReader fileInputStream = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("pos.lyg")));
//		String readLine = "";
//		while((readLine = fileInputStream.readLine()) != null) {	
//			if(!pos.containsKey(readLine)) {
//				pos.put(readLine, "1");
//			}
//		}
//		fileInputStream.close();
//		Map<String, String> posPositive= new HashMap<>();
//		fileInputStream = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("posPositive.lyg")));
//		while((readLine = fileInputStream.readLine()) != null) {	
//			if(!posPositive.containsKey(readLine)) {
//				posPositive.put(readLine, "1");
//			}
//		}
//		fileInputStream.close();
//			
//		Iterator<String> iterator = pos.keySet().iterator();
//		while(iterator.hasNext()) {
//			readLine = iterator.next();
//			if(!posPositive.containsKey(readLine)) {
//				bufferedWriter.write(readLine);
//				bufferedWriter.write("\r\n");
//			}
//		}
//		bufferedWriter.close();	
//	}
//}