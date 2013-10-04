package rec.collaborative;

import java.util.Vector;

import rec.Wine;


/*
 * Datenstruktur für Kaufhistorie/Warenkorb 
 * 
 */

public class History {

	private int id; // für Kaufhistorie= UserID; Warenkorb= OrderID
	
	private double similairity; // Ähnlichkeit zu der aktuellen Kaufhistorie/ aktueller Warenkorb
	
	public Vector<Wine> wine = new Vector<Wine>(); // enthält alle gekauften Weine
	
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
