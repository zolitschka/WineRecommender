package rec.content;

import java.util.Collections;
import java.util.Comparator;
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

		// Durchschnittwein erstellen
		averageWine.setAcid(average(acid));
		averageWine.setAlcohol(average(alcohol));
		averageWine.setPrice(average(price));
		averageWine.setQuality(maxOccurInt(quality));
		averageWine.setRegion(maxOccurInt(region));
		averageWine.setVdp(maxOccurInt(vdp));
		averageWine.setWinery(maxOccurInt(winery));
		averageWine.setWineStyle(maxOccurInt(wineStyle));
		averageWine.setYear(maxOccurInt(year));

	}

	// Mittelwert
	private double average(Vector<Double> input) {
		double result = 0;
		for (int i = 0; i < input.size(); i++) {
			result += input.elementAt(i);
		}

		return (result / input.size());
	}

	// TODO häuftigstes Vorkommen bei Integer
	private int maxOccurInt(Vector<Integer> input) {
		int count = -1;
		int maxCount = -1;
		int lastElement = -1;
		int currentElement = -1;
		int maxElement = 0;

		Collections.sort(input);

		for (int i = 0; i < input.size(); i++) {
			currentElement = input.elementAt(i);
			if (lastElement != currentElement) {
				if (count > maxCount) {
					maxElement = lastElement;
					maxCount = count;
				}
				count = 1;
				lastElement = currentElement;
			} else {
				count++;
			}
		}
		return maxElement;
	}

	// TODO häuftigstes Vorkommen bei int[]
	private int[] maxOccurArray(Vector<int[]> input) {
		int count = -1;
		int maxCount = -1;
		int[] lastElement = {};
		int[] currentElement = {};
		int[] maxElement = {};

		Collections.sort(input, new Comparator<int[]>() {
			@Override
			public int compare(int[] element1, int[] element2) {
				if (element1[0] < element2[0]) {
					return -1;
				} else
					return 1;
			}
		});

		for (int i = 0; i < input.size(); i++) {
			currentElement = input.elementAt(i);
			if (lastElement != currentElement) {
				if (count > maxCount) {
					maxElement = lastElement;
					maxCount = count;
				}
				count = 1;
				lastElement = currentElement;
			} else {
				count++;
			}
		}
		if (count > maxCount) {
			maxElement = lastElement;
			maxCount = count;
		}
		return maxElement;
	}
}
