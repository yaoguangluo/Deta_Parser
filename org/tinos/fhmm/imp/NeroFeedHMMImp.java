package org.tinos.fhmm.imp;
import java.util.List;
import java.util.Map;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.obj.FLHMMNode;
import org.tinos.obj.FHHMMNode;
import org.tinos.zabbi.DataString;
public class NeroFeedHMMImp implements NeroFeedHMM{
	@SuppressWarnings({DataString.RAW_TYPES, DataString.UNCHECKED})
	public String getPrettyRecurWord(String temp, String input, int i, int length, Map<Integer, Map> roots,
			int forestDepth) {
		if(forestDepth == DataString.INT_THREE) {
			return temp;
		}
		String charPosition = DataString.EMPTY_STRING + input.charAt(i);	
		int range = ((int)(charPosition.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
		int rangeHigh = range >> DataString.INT_FOUR; 
		if(roots.containsKey(rangeHigh)){
			Map <Integer, Map> root = roots.get(rangeHigh);
			if(root.containsKey(range)){
				Map<String, FLHMMNode> maps = root.get(range);
				FLHMMNode fDHMMNode = maps.get(charPosition);
				temp = doPrettyRecurWordKerner(temp, fDHMMNode, length, input, i, roots, forestDepth);
			}
		}
		return temp;
	}

	public String getFastRecurWord(String temp, Map<String, FLHMMNode> maps, String input, int i, int length) {
		String charPosition = DataString.EMPTY_STRING + input.charAt(i);
		if(maps.containsKey(charPosition)){
			FLHMMNode fDHMMNode = maps.get(charPosition);
			if(fDHMMNode.getNext() != null) {
				List<String> tempList = fDHMMNode.getNext();
				for(int j = DataString.INT_ZERO; j < tempList.size(); j++) {
					if(i + DataString.INT_ONE < length) {
						String charPostPosition = DataString.EMPTY_STRING + input.charAt(i + DataString.INT_ONE);
						if(tempList.get(j).equalsIgnoreCase(charPostPosition)){
							StringBuilder bld = new StringBuilder();
							bld.append(temp);
							bld.append(charPostPosition);
							temp = bld.toString();
							temp = getFastRecurWord(temp, maps, input, i + DataString.INT_ONE, length);
						}
					}
				}
			}
		}
		return temp;
	}
	
	@SuppressWarnings({DataString.RAW_TYPES, DataString.UNCHECKED})
	public String getBinaryForestRecurWord(String tempWordNode, String inputString, int charPosition, int inputStringLength, 
			Map<Integer, Map> forestRoots, int forestDepth) {
		if(forestDepth == DataString.INT_THREE) {
			return tempWordNode;
		}
		String StringPosition = DataString.EMPTY_STRING + inputString.charAt(charPosition);	
		int range = ((int)(StringPosition.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
		int rangeHigh = range >> DataString.INT_FOUR; 	
				if(forestRoots.containsKey(rangeHigh)){
					Map <Integer, Map> trees = forestRoots.get(rangeHigh);
					if(trees.containsKey(range)){
						Map<String, FHHMMNode> maps = trees.get(range);
						FHHMMNode fFHMMNode = maps.get(StringPosition);
						tempWordNode = doBinaryForestRecurWordKerner(tempWordNode, fFHMMNode, inputStringLength, inputString,
								charPosition, forestRoots, forestDepth);
					}
				}
				return tempWordNode;
	}
	
	@SuppressWarnings(DataString.RAW_TYPES)
	public String doBinaryForestRecurWordKerner(String temp, FHHMMNode fFHMMNode, int length, String input,
			int i, Map<Integer, Map> roots, int forestDepth) {
		if(fFHMMNode != null && fFHMMNode.getNext() != null) {
			Map<String, Integer> tempList = fFHMMNode.getNext();
			if(i + DataString.INT_ONE < length) {
				String charPostPosition = DataString.EMPTY_STRING + input.charAt(i + DataString.INT_ONE);
				if(tempList.containsKey(charPostPosition)){
					StringBuilder bld = new StringBuilder();
					bld.append(temp);
					bld.append(charPostPosition);
					temp = bld.toString();
					temp = getBinaryForestRecurWord(temp, input, i + DataString.INT_ONE, length, roots,
							forestDepth + DataString.INT_ONE);
				}
			}
		}
		return temp;
	}

	@SuppressWarnings(DataString.RAW_TYPES)
	public String doPrettyRecurWordKerner(String temp, FLHMMNode fDHMMNode, int length, String input, int i, 
			Map<Integer, Map> roots, int forestDepth) {
		if(fDHMMNode != null && fDHMMNode.getNext() != null) {
			List<String> tempList = fDHMMNode.getNext();
			for(int j = DataString.INT_ZERO; j < tempList.size(); j++) {
				if(i + DataString.INT_ONE < length) {
					String charPostPosition = DataString.EMPTY_STRING + input.charAt(i+DataString.INT_ONE);
					if(tempList.get(j).equalsIgnoreCase(charPostPosition)){
						StringBuilder bld = new StringBuilder();
						bld.append(temp);
						bld.append(charPostPosition);
						temp = bld.toString();
						temp = getPrettyRecurWord(temp, input, i + DataString.INT_ONE, length, roots, 
								forestDepth+DataString.INT_ONE);
					}
				}
			}
		}
		return temp;
	}
}
