package rec.collaborative;

import java.util.Vector;

public class RecCreation {

	Vector<History> orders = new Vector<History>();
	Vector<History> buyhistories = new Vector<History>();
	History currentOrder;
	History currentBuyHistory;

	public RecCreation (int userId, History o){
		
		currentOrder=o; 
		currentBuyHistory=getCurrentBuyHistorie(userId);		
	}
	
	
	
	public History getCurrentBuyHistorie(int userId) {
		History h = new History();

		for (int i = 0; i < buyhistories.size(); i++) {
			if (buyhistories.elementAt(i).getId() == userId) {
				h = buyhistories.elementAt(i);
			} else {
				return null;
			}
		}
		return h;

	}
	
	

}
