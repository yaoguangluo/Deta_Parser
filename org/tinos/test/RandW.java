//package org.tinos.test;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//public class RandW{
//	@SuppressWarnings("resource")
//	public static void main(String args[]) throws IOException{
//		String c = "C:/Users/Administrator/Desktop/txt/short.txt";
//		FileWriter fw = null;
//		BufferedReader br = null;
//		fw = new FileWriter(c, true);
//		br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\chinese_medcine\\NLP\\org\\tinos\\base\\words.lyg"), "UTF-8"));
//		// String line = null;
//		String ctempString;
//		while ((ctempString = br.readLine()) != null) {  
//			if(!ctempString.replace(" ", "").replace("\\?", "").equals("") && ctempString.replace(" ", "").length()>1) {
//				fw.write(ctempString.replace(" ", "").replace("\\?", ""));
//				fw.write("\r\n");
//			}
//		}
//		br.close();
//		fw.close();
//	}
//}