package rec.collaborative;

import java.util.Vector;

public class Order {

	private int orderId;
	private int similairity;
	Vector<Integer> wine = new Vector();

	public int getSimilairity() {
		return similairity;
	}

	public void setSimilairity(int similairity) {
		this.similairity = similairity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
