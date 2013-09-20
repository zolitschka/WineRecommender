package rec;

import java.util.Vector;

public class Hybrid {

	public static Vector<Wine> collSwitch(Vector<Wine> svdlist, Vector<Wine> kaufhistorie, Vector<Wine> gW) {
		
		Vector<Wine> collHybridList = new Vector();
		Vector<Wine> gekaufteWeine = gW;

		boolean dup = false;
		boolean dup2 = false;
		for (Wine wein : svdlist) {
			dup2=false;
			if (wein.getRating() > 3.5) {
				for (Wine wein3 : gekaufteWeine) {
					if (wein.getId()==wein3.getId()){
						dup2=true; 
					}
					

			}
				if (!dup2) {
					collHybridList.add(wein);
				}
		} }

		for (Wine wein : kaufhistorie) {
			dup = false;
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
	public static Vector <Wine>  normalHybrid (Vector <Wine> contList, Vector <Wine> collList){
		Vector <Wine> hybridRec = new Vector<>(); 
	
		boolean gekauft = false; 
		hybridRec=collList; 
		
		if (hybridRec.size()<10){
			
			for ( Wine wein : contList){
				gekauft=false; 
				for (Wine wein2: collList){
					if (wein2.getId()==wein.getId()){
						gekauft=true; 
					}
				}
				if (!gekauft){
					hybridRec.add(wein);
				}
			}
		}
		
		
		return hybridRec; 
	}

}
