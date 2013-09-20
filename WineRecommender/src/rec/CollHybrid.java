package rec;

import java.util.Vector;

public class CollHybrid {

	public static Vector<Wine> collSwitch(Vector<Wine> svdlist,
			Vector<Wine> kaufhistorie) {
		Vector<Wine> collHybridList = new Vector();
		boolean dup = false;

		for (Wine wein : svdlist) {
			if (wein.getRating() > 3.5) {
				collHybridList.add(wein);
			}
		}

		for (Wine wein : kaufhistorie) {
			dup=false; 
			if (wein.getWineScore() > 3.5) {
				for (Wine wein2 : collHybridList) {
					if (wein.getId() == wein2.getId()) {
						dup = true;
					}
				}
				if (!dup) {
					collHybridList.add(wein);
				}

			}
		}

		return collHybridList;
	}

}
