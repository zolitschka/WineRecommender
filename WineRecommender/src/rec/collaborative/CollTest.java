package rec.collaborative;

import java.util.Vector;

public class CollTest {

	public static void main(String[] args) {
		
		
		History h1 = new History(1); 
		History h2 = new History(2); 
		History h3 = new History(3); 
		History h4 = new History(4);
		History h5 = new History(5);
		
		h1.wine.add(30);
		h1.wine.add(40);
		h1.wine.add(50);
		
		h2.wine.add(20);
		h2.wine.add(30);
		h2.wine.add(40);
		h2.wine.add(90);
		
		h3.wine.add(30);
		h3.wine.add(40);
		h3.wine.add(50);
		h3.wine.add(90);
		h3.wine.add(100);
		
		
		h4.wine.add(10);
		h4.wine.add(15);
		
		h5.wine.add(20);
		h5.wine.add(40);
		
		
		Vector<History> b = new Vector<History>();
		b.add(h2);
		b.add(h3);
		b.add(h4);
		b.add(h5);
		
		RecCreation rc = new RecCreation(b, h1, 1);
		rc.print(b);
		System.out.println();
		rc.createRecOrderHistory();
		System.out.println();
		rc.printSim(b);
		

	}

}
