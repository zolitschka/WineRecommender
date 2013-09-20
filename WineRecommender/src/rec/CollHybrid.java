package rec;

import java.util.Vector;

public class CollHybrid {
	
	
	
	public static Vector <Wine> collSwitch (Vector<Wine> svdlist, Vector<Wine> kaufhistorie){
		Vector <Wine> collHybridList= new Vector(); 
		
		for(Wine wein : svdlist){
			if(wein.getRating() > 3.5){
				collHybridList.add(wein);
			}
		}
		for(Wine wein : kaufhistorie){
			if(wein.getWineScore() > 3.5){
				collHybridList.add(wein);
			}
		}
		
		return collHybridList; 
	}

	
	
}
