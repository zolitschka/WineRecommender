package rec.content;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import rec.GUI;
import rec.User;
import rec.Wine;

public class contentBuyHistory {
	// Hier später Anbindung an "echten" Warenkorb
	private static Vector<Wine> order;
	private User currentUser = GUI.getCurrentUser();

	static Wine averageWine = new Wine();
	static Vector<Double> acid = new Vector<Double>();
	static Vector<Double> alcohol = new Vector<Double>();
	static Vector<Integer> quality = new Vector<Integer>();
	static Vector<Integer> region = new Vector<Integer>();
	static Vector<Integer> vdp = new Vector<Integer>();
	static Vector<Integer> winery = new Vector<Integer>();
	static Vector<Integer> wineStyle = new Vector<Integer>();
	static Vector<Integer> year = new Vector<Integer>();
	static Vector<Double> price = new Vector<Double>();
	static Vector<Double> sweetness = new Vector<Double>();
	static Vector<int[]> aroma = new Vector<int[]>();
	static Vector<int[]> grape = new Vector<int[]>();

	public static Vector<Wine> getBuyHistory() {
		order = GUI.getCurrentOrder();
		empty();
		// alle Eigenschaften in einzelnen Vektoren sammeln
		for (int i = 0; i < order.size(); i++) {
			Wine tmp = order.elementAt(i);
			acid.add(tmp.getAcid());
			alcohol.add(tmp.getAlcohol());
			price.add(tmp.getPrice());
			sweetness.add(tmp.getSweetness());
			quality.add(tmp.getQuality());
			region.add(tmp.getRegion());
			vdp.add(tmp.getVdp());
			winery.add(tmp.getWinery());
			wineStyle.add(tmp.getWineStyle());
			year.add(tmp.getYear());
			aroma.add(tmp.getAroma());
			grape.add(tmp.getGrape());
		}
		// Durchschnittwein erstellen
		averageWine.setAcid(average(acid));
		averageWine.setAlcohol(average(alcohol));
		averageWine.setPrice(average(price));
		averageWine.setSweetness(average(sweetness));
		averageWine.setQuality(maxOccurInt(quality));
		averageWine.setRegion(maxOccurInt(region));
		averageWine.setVdp(maxOccurInt(vdp));
		averageWine.setWinery(maxOccurInt(winery));
		averageWine.setWineStyle(maxOccurInt(wineStyle));
		averageWine.setYear(maxOccurInt(year));
		averageWine.setGrape(maxOccurArray(grape));
		averageWine.setAroma(maxOccurArray(aroma));

		if (order.size() > 0) {
			Wine tmp = order.lastElement();
			System.out.println(tmp.getAcid() + " : " + tmp.getAlcohol() + " : "
					+ tmp.getPrice() + " : " + tmp.getSweetness() + " : "
					+ tmp.getQuality() + " : " + tmp.getRegion() + " : "
					+ tmp.getVdp() + " : " + tmp.getWinery() + " : "
					+ tmp.getWineStyle() + " : " + tmp.getYear() + " : "
					+ Arrays.toString(tmp.getGrape()));
			System.out.println(averageWine.getAcid() + " : "
					+ averageWine.getAlcohol() + " : " + averageWine.getPrice()
					+ " : " + averageWine.getSweetness() + " : "
					+ averageWine.getQuality() + " : "
					+ averageWine.getRegion() + " : " + averageWine.getVdp()
					+ " : " + averageWine.getWinery() + " : "
					+ averageWine.getWineStyle() + " : "
					+ averageWine.getYear() + " : "
					+ Arrays.toString(averageWine.getGrape()));
		}

		return SimilarityList.getSimilarityList(averageWine);
	}

	// Mittelwert
	private static double average(Vector<Double> input) {
		double result = 0;
		for (int i = 0; i < input.size(); i++) {
			result += input.elementAt(i);
		}

		return (result / input.size());
	}

	// häuftigstes Vorkommen bei Integer
	private static int maxOccurInt(Vector<Integer> input) {
		int count = -1;
		int maxCount = -1;
		int lastElement = -1;
		int currentElement = -1;
		int maxElement = -1;

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
		if (count > maxCount) {
			maxElement = lastElement;
			maxCount = count;
		}
		return maxElement;
	}

	// häuftigstes Vorkommen bei int[]
	private static int[] maxOccurArray(Vector<int[]> input) {
		int count = -1;
		int maxCount = -1;
		int[] lastElement = {};
		int[] currentElement = {};
		int[] maxElement = {};

		Collections.sort(input, new Comparator<int[]>() {
			@Override
			public int compare(int[] element1, int[] element2) {
				if (element2 != null) {
					if (element1[0] < element2[0]) {
						return -1;
					} else
						return 1;
				} else
					return 0;
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

	private static void empty() {
		acid.removeAllElements();
		alcohol.removeAllElements();
		price.removeAllElements();
		sweetness.removeAllElements();
		quality.removeAllElements();
		region.removeAllElements();
		vdp.removeAllElements();
		winery.removeAllElements();
		wineStyle.removeAllElements();
		year.removeAllElements();
		aroma.removeAllElements();
		grape.removeAllElements();
	}
}
