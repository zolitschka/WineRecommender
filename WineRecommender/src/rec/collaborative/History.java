package rec.collaborative;

import java.util.Vector;

public class History {

	private int id;
	private double similairity;
	Vector<Integer> wine = new Vector<Integer>(); // Product_ID wird gespeichert
	
	public History (int i){
		this.setId(i);
	}
	
	public double getSimilairity() {
		return similairity;
	}

	public void setSimilairity(double similairity) {
		this.similairity = similairity;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
