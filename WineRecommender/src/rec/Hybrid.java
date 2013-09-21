package rec;

import java.util.Vector;

public class Hybrid {
	final static double KAUFHISTORIEN_SWITCH=2.5;  // Switching Schwellenwerte 
	final static double SVD_SWICTH=3.5; 
	final static double ORDER_SWITCH=1.5; 
	
	public static Vector<Wine> collSwitch(Vector<Wine> svdlist,
		Vector<Wine> kaufhistorie, Vector<Wine> gW) {
		
		
		
		Vector<Wine> collHybridList = new Vector();
		Vector<Wine> gekaufteWeine = gW;

		boolean dup = false;
		boolean dup2 = false;
		for (Wine wein : svdlist) {
			wein.setSvd(false);
			dup2 = false;
			if (wein.getRating() > SVD_SWICTH) {
				for (Wine wein3 : gekaufteWeine) {
					if (wein.getId() == wein3.getId()) {
						dup2 = true;
					}

				}
				if (!dup2) {
					wein.setSvd(true);
					collHybridList.add(wein);
				}
			}
		}

		for (Wine wein : kaufhistorie) {
			dup = false;
			wein.setKaufhistorie(false);
			if (wein.getWineScore() > KAUFHISTORIEN_SWITCH) {
				for (Wine wein2 : collHybridList) {
					if (wein.getId() == wein2.getId()) {
						dup = true;
					}
				}
				if (!dup) {
					wein.setKaufhistorie(true);
					collHybridList.add(wein);
				}

			}
		}

		return collHybridList;
	}

	public static Vector<Wine> normalHybrid(Vector<Wine> contList,
			Vector<Wine> collList) {
		Vector<Wine> hybridRec = new Vector<>();

		boolean gekauft = false;
		hybridRec = collList;

		if (hybridRec.size() < 10) {
			for (Wine wein : contList) {
				gekauft = false;
				wein.setContent(false);
				for (Wine wein2 : collList) {
					if (wein2.getId() == wein.getId()) {
						gekauft = true;
					}
				}
				if (!gekauft) {
					wein.setContent(true);
					hybridRec.add(wein);
				}
			}
		}

		return hybridRec;
	}

	public static Vector<Wine> warenkorbHybrid(Vector<Wine> contList,
			Vector<Wine> collList) {
		Vector<Wine> hybridOrderRec = new Vector();

		for (Wine wein : collList) {
			wein.setKaufhistorie(false);
			if (wein.getWineScore() >ORDER_SWITCH ) {
				wein.setKaufhistorie(true);
				hybridOrderRec.add(wein);
			}
		}

		for (Wine wein : contList) {
			wein.setContent(true);
			hybridOrderRec.add(wein);
		}
		return hybridOrderRec;

	}

}
