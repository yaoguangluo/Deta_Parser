package org.tinos.utils.imp;
import java.util.List;
import java.util.Map;
import org.tinos.utils.EngineUtils;
import org.tinos.zabbi.DataString;
public class EngineUtilsImp implements EngineUtils{
	public List<String> doSlangCheck(List<String> output, Map<String, String> words, String temp) {
		if(words.containsKey(temp)){
			output.add(temp);
		}else {
			output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO) + 
					temp.charAt(DataString.INT_ONE));
			output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO) +
					temp.charAt(DataString.INT_THREE));
		}
		return output;
	}

	public List<String> doEuclidCheck(List<String> output, String euclid, String temp) {
		if(euclid.contains(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO))){
			String []temps = temp.split(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO));
			output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_ZERO));
			output.add(DataString.EMPTY_STRING + temps[DataString.INT_ONE]);
		}else if(euclid.contains(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO))){
			String []temps = temp.split(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO));
			output.add(DataString.EMPTY_STRING + temps[DataString.INT_ZERO]);
			output.add(DataString.EMPTY_STRING + temp.charAt(DataString.INT_TWO));
		}else {
			output.add(DataString.EMPTY_STRING + temp);
		}
		return output;
	}
}