package rec.collaborative;

import java.util.Vector;

public class History {

	private int id;
	private int similairity;
	Vector<Integer> wine = new Vector<Integer>(); // Product_ID wird gespeichert

	public int getSimilairity() {
		return similairity;
	}

	public void setSimilairity(int similairity) {
		this.similairity = similairity;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
