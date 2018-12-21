package org.tinos.ortho.fhmm;
import java.util.Map;
import org.tinos.view.stable.StableData;
public interface FMHMMList extends FHMMList {
	@SuppressWarnings(StableData.RAW_TYPES)
	Map<Integer, Map> getRoot();
}