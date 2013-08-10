package rec.collaborative;

import java.util.Vector;

public class BuyHistory {

	private int customerId;
	private int similairity; 
	Vector<Integer> wine = new Vector();

	
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getSimilairity() {
		return similairity;
	}
	public void setSimilairity(int similairity) {
		this.similairity = similairity;
	}
	
}
