package rec.content;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import rec.Wine;
import rec.orderGUI;

public class ContentBuyHistory {
	// Hier später Anbindung an "echten" Warenkorb
	private static Vector<Wine> order;

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
	static Vector<Wine> contentBuyHistory = new Vector<Wine>();

	public static Vector<Wine> createBuyHistory() {
		order = orderGUI.getCurrentOrder();
		getAverageWine(order);
		contentBuyHistory = SimilarityList.getSimilarityList(averageWine);

		for (Wine wine1 : order) {
			for (int i = 0; i < contentBuyHistory.size(); i++) {
				Wine wine2 = contentBuyHistory.elementAt(i);
				if (wine1.getId() == wine2.getId()) {
					contentBuyHistory.remove(wine2);
				}
			}
		}

		return contentBuyHistory;
	}

	public static Vector<Wine> getBuyHistory() {
		return contentBuyHistory;
	}

	public static Wine getAverageWine(Vector<Wine> history) {
		empty();
		// alle Eigenschaften in einzelnen Vektoren sammeln
		for (int i = 0; i < history.size(); i++) {
			Wine tmp = history.elementAt(i);
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
		return averageWine;
	}

	// Mittelwert
	private static double average(Vector<Double> input) {
		double result = 0;
		int size = input.size();
		for (int i = 0; i < input.size(); i++) {
			if (input.elementAt(i) == -1) {
				size--;
			} else {
				result += input.elementAt(i);
			}
		}

		return (result / size);
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
			if (lastElement != currentElement && currentElement != -1) {
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

	// alle Eigenschaften zurücksetzen
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
