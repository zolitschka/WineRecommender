package rec.collaborative;

import java.util.Vector;

import rec.Wine;

public class CollTest {

	public static void main(String[] args) {
		
		
		History h1 = new History(1); 
		History h2 = new History(2); 
		History h3 = new History(3); 
		History h4 = new History(4);
		History h5 = new History(5);
		
		Wine w1=new Wine();
		w1.setId(30);
		Wine w2=new Wine();
		w2.setId(40);
		Wine w3=new Wine();
		w3.setId(50);
		Wine w4=new Wine();
		w4.setId(20);
		Wine w5=new Wine();
		w5.setId(30);

		Wine w7=new Wine();
		w7.setId(90);
		
		Wine w12=new Wine();
		w12.setId(100);
		Wine w13=new Wine();
		w13.setId(10);
		Wine w14=new Wine();
		w14.setId(15);
	

		
		h1.wine.add(w1);
		h1.wine.add(w2);
		h1.wine.add(w3);
		

		h2.wine.add(w4);
		h2.wine.add(w1);
		h2.wine.add(w2);
		h2.wine.add(w7);
		
		h3.wine.add(w1);
		h3.wine.add(w2);
		h3.wine.add(w3);
		h3.wine.add(w7);
		h3.wine.add(w12);
		

		h4.wine.add(w13);
		h4.wine.add(w14);
		

		h5.wine.add(w4);
		h5.wine.add(w2);
		
		
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
		System.out.println();
		rc.printSim(b);
		

	}

}
