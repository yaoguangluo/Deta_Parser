package org.tinos.fhmm.imp;
import java.util.List;
import java.util.Map;
import org.tinos.fhmm.NeroFeedHMM;
import org.tinos.obj.FDHMMNode;
import org.tinos.obj.FFHMMNode;
import org.tinos.zabbi.DataString;
public class NeroFeedHMMImp implements NeroFeedHMM{
	@SuppressWarnings({DataString.RAW_TYPES, DataString.UNCHECKED})
	public String getPrettyRecurWord(String temp, String input, int i, int length, Map<Integer,Map> roots, int depth) {
		if(depth == DataString.INT_THREE) {
			return temp;
		}
		String charPosition = DataString.EMPTY_STRING + input.charAt(i);	
		int range = ((int)(charPosition.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
		int rangeHigh = range >> DataString.INT_FOUR; 
		if(roots.containsKey(rangeHigh)){
			Map <Integer, Map> root = roots.get(rangeHigh);
			if(root.containsKey(range)){
				Map<String, FDHMMNode> maps = root.get(range);
				FDHMMNode fDHMMNode = maps.get(charPosition);
				temp = doPrettyRecurWordKerner(temp,fDHMMNode,length,input,i,roots,depth);
			}
		}
		return temp;
	}

	public String getFastRecurWord(String temp, Map<String, FDHMMNode> maps, String input, int i, int length) {
		String charPosition = DataString.EMPTY_STRING + input.charAt(i);
		if(maps.containsKey(charPosition)){
			FDHMMNode fDHMMNode = maps.get(charPosition);
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
							temp = getFastRecurWord(temp,maps, input,i+DataString.INT_ONE, length);
						}
					}
				}
			}
		}
		return temp;
	}
	
	@Override
	@SuppressWarnings({DataString.RAW_TYPES, DataString.UNCHECKED})
	public String getBinaryForestRecurWord(String temp, String input, int i, int length, Map<Integer, Map> roots, int depth) {
		if(depth == DataString.INT_THREE) {
			return temp;
		}
		String charPosition = DataString.EMPTY_STRING + input.charAt(i);	
		int range = ((int)(charPosition.charAt(DataString.INT_ZERO)) >> DataString.INT_SIX);
		int rangeHigh = range >> DataString.INT_FOUR; 	
				if(roots.containsKey(rangeHigh)){
					Map <Integer,Map> root = roots.get(rangeHigh);
					if(root.containsKey(range)){
						Map<String, FFHMMNode> maps = root.get(range);
						FFHMMNode fFHMMNode = maps.get(charPosition);
						temp = doBinaryForestRecurWordKerner(temp,fFHMMNode,length,input,i,roots,depth);
					}
				}
				return temp;
	}
	
	@SuppressWarnings("rawtypes")
	public String doBinaryForestRecurWordKerner(String temp, FFHMMNode fFHMMNode, int length, String input, int i, Map<Integer, Map> roots,int depth) {
		if(fFHMMNode != null && fFHMMNode.getNext() != null) {
			Map<String, Integer> tempList = fFHMMNode.getNext();
			if(i + DataString.INT_ONE < length) {
				String charPostPosition = DataString.EMPTY_STRING + input.charAt(i + DataString.INT_ONE);
				if(tempList.containsKey(charPostPosition)){
					StringBuilder bld = new StringBuilder();
					bld.append(temp);
					bld.append(charPostPosition);
					temp = bld.toString();
					temp = getBinaryForestRecurWord(temp, input,i + DataString.INT_ONE, 
							length, roots, depth + DataString.INT_ONE);
				}
			}
		}
		return temp;
	}

	@SuppressWarnings("rawtypes")
	public String doPrettyRecurWordKerner(String temp, FDHMMNode fDHMMNode, int length, String input, int i, Map<Integer, Map> roots, int depth) {
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
						temp = getPrettyRecurWord(temp, input,i+DataString.INT_ONE, length, roots,
								depth+DataString.INT_ONE);
					}
				}
			}
		}
		return temp;
	}
}
