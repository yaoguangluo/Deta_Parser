package org.tinos.fhmm.imp;
import java.util.List;
import java.util.LinkedHashMap;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.obj.FDHMMNode;
import org.tinos.zabbi.DataString;

public class NeroFeedHMMImp implements NeroFeedHMM{
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED})
	public String getPrettyRecurWord(String temp, String input, int i, int length, LinkedHashMap<Integer, LinkedHashMap> roots,int depth) {
		if(depth == DataString.INT_THREE) {
			return temp;
		}
		String char_i = DataString.EMPTY_STRING + input.charAt(i);
		int range = ((int)(char_i.charAt(DataString.INT_ZERO))/DataString.INT_SIXTY_FOUR);
		int rangeHigh = range / DataString.INT_SIXTEEN;
		if(roots.containsKey(rangeHigh)){
			LinkedHashMap <Integer,LinkedHashMap> root = roots.get(rangeHigh);
			if(root.containsKey(range)){
				LinkedHashMap<String, FDHMMNode> maps =root.get(range);
				FDHMMNode fDHMMNode = maps.get(char_i);
				if(fDHMMNode != null) {
					if(fDHMMNode.next != null) {
						List<String> tempList = fDHMMNode.next;
						for(int j = DataString.INT_ZERO; j < tempList.size(); j++) {
							if(i + DataString.INT_ONE < length) {
								String char_iAddOne = DataString.EMPTY_STRING + input.charAt(i+DataString.INT_ONE);
								if(tempList.get(j).equalsIgnoreCase(char_iAddOne)){
									temp += char_iAddOne;
									temp = getPrettyRecurWord(temp, input,i+DataString.INT_ONE, length, roots, depth+DataString.INT_ONE);
								}
							}
						}
					}
				}
			}
		}
		return temp;
	}

	public String getFastRecurWord(String temp, LinkedHashMap<String, FDHMMNode> maps, String input, int i, int length) {
		String char_i = DataString.EMPTY_STRING+input.charAt(i);
		if(maps.containsKey(char_i)){
			FDHMMNode fDHMMNode = maps.get(char_i);
			if(fDHMMNode.next != null) {
				List<String> tempList = fDHMMNode.next;
				for(int j = DataString.INT_ZERO; j < tempList.size(); j++) {
					if(i + DataString.INT_ONE < length) {
						String char_iAddOne = DataString.EMPTY_STRING + input.charAt(i+DataString.INT_ONE);
						if(tempList.get(j).equalsIgnoreCase(char_iAddOne)){
							temp += char_iAddOne;
							temp = getFastRecurWord(temp,maps,input,i+DataString.INT_ONE, length);
						}
					}
				}
			}
		}
		return temp;
	}
	
	@SuppressWarnings({ DataString.RAW_TYPES, DataString.UNCHECKED})
	public String getPrettyRecurWord1(String temp, String input, int i, int length, LinkedHashMap<Integer, LinkedHashMap> roots) {
		String char_i = DataString.EMPTY_STRING + input.charAt(i);
		int range = ((int)(char_i.charAt(DataString.INT_ZERO))/DataString.INT_SIXTY_FOUR);
		int rangeHigh = range / DataString.INT_SIXTEEN;
		if(roots.containsKey(rangeHigh)){
			LinkedHashMap <Integer,LinkedHashMap> root = roots.get(rangeHigh);
			if(root.containsKey(range)){
				LinkedHashMap<String, FDHMMNode> maps =root.get(range);
				FDHMMNode fDHMMNode = maps.get(char_i);
				if(fDHMMNode != null) {
					if(fDHMMNode.next != null) {
						List<String> tempList = fDHMMNode.next;
						for(int j = DataString.INT_ZERO; j < tempList.size(); j++) {
							if(i + DataString.INT_ONE < length) {
								String char_iAddOne = DataString.EMPTY_STRING + input.charAt(i+DataString.INT_ONE);
								if(tempList.get(j).equalsIgnoreCase(char_iAddOne)){
									temp += char_iAddOne;
									temp = getPrettyRecurWord1(temp, input,i+DataString.INT_ONE, length, roots);
								}
							}
						}
					}
				}
			}
		}
		return temp;
	}
	
}
