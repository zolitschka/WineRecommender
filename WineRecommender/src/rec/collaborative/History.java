package rec.collaborative;

import java.util.Vector;

import rec.Wine;


public class History {

	private int id;
	private double similairity;
	Vector<Wine> wine = new Vector<Wine>(); 
	
	public History (int i){
		this.setId(i);
		similairity=0.0;
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
