package rec.content;

import java.util.Vector;

import rec.GUI;
import rec.User;
import rec.Wine;

public class contentBuyHistory {
	// Hier später Anbindung an "echten" Warenkorb
	private static Vector<Wine> order = GUI.getCurrentOrder();
	User currentUser = GUI.getCurrentUser();

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

	public contentBuyHistory() {
		// alle Eigenschaften in einzelnen Vektoren sammeln
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

		quality.add(40);
		quality.add(50);
		quality.add(20);
		quality.add(200);
		quality.add(30);
		quality.add(20);
		System.out.println(maxOccur(quality));
	}

	// Mittelwert
	private double average(Vector<Double> input) {
		double result = 0;
		System.out.println("Test");
		for (int i = 0; i < input.size(); i++) {
			result += input.elementAt(i);
		}

		return (result / input.size());
	}

	// TODO häuftigstes Vorkommen bei Integer
	private int maxOccur(Vector<Integer> input) {
		int count = -1;
		int maxCount = -1;
		int lastElement = -1;
		int maxElement = 0;

		for (int i = 0; i < input.size(); i++) {
			if (lastElement != input.elementAt(i)) {
				if (count>maxCount){
					maxElement = input.elementAt(i);
				}
			}
		}
		return maxElement;
	}

	public static void addWine(Wine newWine) {
		order.add(newWine);
	}

	public static void deleteWine(Wine oldWine) {
		order.remove(oldWine);
	}
}
