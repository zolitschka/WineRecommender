package rec;

import org.apache.mahout.cf.taste.common.TasteException;

/*
 * 
 * Startet die grafische Oberflaeche
 * 
 */

public class main {
	// GUI skalierbar ueber Parameter im Konstruktor
	public static void main(String[] args) throws TasteException {
		new GUI(800);
	}
}
