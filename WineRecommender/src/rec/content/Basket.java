package rec.content;

import java.util.Vector;

import rec.GUI;
import rec.Wine;

public class Basket {
	// Hier später Anbindung an "echten" Warenkorb
	Vector<Wine> order = GUI.getOrder();
	String user = GUI.getUser();
	Wine averageWine = new Wine();
	Vector<Double> acid = new Vector<Double>();
	Vector<Double> alcohol = new Vector<Double>();
	Vector<Integer> quality = new Vector<Integer>();
	Vector<Integer> region = new Vector<Integer>();
	Vector<Integer> vdp = new Vector<Integer>();
	Vector<Integer> winery = new Vector<Integer>();
	Vector<Integer> wineStyle = new Vector<Integer>();
	Vector<Integer> year = new Vector<Integer>();
	Vector<Double> price = new Vector<Double>();
	Vector<Double> sweetness = new Vector<Double>();
	Vector<int[]> aroma = new Vector<int[]>();
	Vector<int[]> grape = new Vector<int[]>();

	public Basket() {
		for (int i = 0; i < order.size(); i++) {
			Wine tmp = order.elementAt(i);
			acid.add(tmp.getAcid());
			alcohol.add(tmp.getAlcohol());
			quality.add(tmp.getQuality());
			region.add(tmp.getRegion());
			vdp.add(tmp.getVdp());
			winery.add(tmp.getWinery());
			wineStyle.add(tmp.getWineStyle());
			year.add(tmp.getYear());
			price.add(tmp.getPrice());
			sweetness.add(tmp.getSweetness());
			aroma.add(tmp.getAroma());
			grape.add(tmp.getGrape());
		}
	}

	public void average(Vector<?> input) {
		
	}
}
