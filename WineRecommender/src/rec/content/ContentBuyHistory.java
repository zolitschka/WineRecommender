package rec.content;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import rec.User;
import rec.Wine;
import rec.normalGUI;
import rec.orderGUI;

public class ContentBuyHistory {
	// Hier später Anbindung an "echten" Warenkorb
	private static Vector<Wine> order;
	private static User currentUser = normalGUI.getCurrentUser();
	// TODO ab wieviel Prozent sind Eigenschaften repraesentativ?
	private final static double border = 0.0;

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

	static double simAcid = 0;
	static double simAlcohol = 0;
	static double simPrice = 0;
	static double simSweetness = 0;
	static double simQuality = 0;
	static double simRegion = 0;
	static double simVdp = 0;
	static double simWinery = 0;
	static double simWineStyle = 0;
	static double simYear = 0;
	static double simAroma = 0;
	static double simGrape = 0;

	public static Vector<Wine> getBuyHistory() {
		order = orderGUI.getCurrentOrder();
		getAverageWine(order);

		return SimilarityList.getSimilarityList(averageWine);
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
		setAttributes();
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

	// Ähnlichkeit des Attributes Acid/Alcohol
	private static void setAttributes() {
		simAcid = 0;
		simAlcohol = 0;
		simPrice = 0;
		simSweetness = 0;
		simQuality = 0;
		simRegion = 0;
		simVdp = 0;
		simWinery = 0;
		simWineStyle = 0;
		simYear = 0;
		simAroma = 0;
		simGrape = 0;
		int sum;
		// TODO vervollstaendigen
		// Ähnlichkeit aller Acid-Werte
		sum = 0;
		int n = acid.size();
		for (int i = 0; i < n; i++) {
			double att1 = acid.elementAt(i);
			for (int j = 0; j < n; j++) {
				double att2 = acid.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.acid(att1, att2);
				}
			}
		}
		if (n > 1) {
			simAcid = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simAcid = 1;
		}
		// Ähnlichkeit aller Alcohol-Werte
		sum = 0;
		n = alcohol.size();
		for (int i = 0; i < n; i++) {
			double att1 = alcohol.elementAt(i);
			for (int j = 0; j < n; j++) {
				double att2 = alcohol.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.alcohol(att1, att2);
				}
			}
		}
		if (n > 1) {
			simAlcohol = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simAlcohol = 1;
		}
		// Ähnlichkeit aller Price-Werte
		sum = 0;
		n = price.size();
		for (int i = 0; i < n; i++) {
			double att1 = price.elementAt(i);
			for (int j = 0; j < n; j++) {
				double att2 = price.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.price(att1, att2);
				}
			}
		}
		if (n > 1) {
			simPrice = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simPrice = 1;
		}
		// Ähnlichkeit aller Sweetness-Werte
		sum = 0;
		n = sweetness.size();
		for (int i = 0; i < n; i++) {
			double att1 = sweetness.elementAt(i);
			for (int j = 0; j < n; j++) {
				double att2 = sweetness.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.sweetness(att1, att2);
				}
			}
		}
		if (n > 1) {
			simSweetness = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simSweetness = 1;
		}
		// Ähnlichkeit aller Quality-Werte
		sum = 0;
		n = quality.size();
		for (int i = 0; i < n; i++) {
			int att1 = quality.elementAt(i);
			for (int j = 0; j < n; j++) {
				int att2 = quality.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.quality(att1, att2);
				}
			}
		}
		if (n > 1) {
			simQuality = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simQuality = 1;
		}
		// Ähnlichkeit aller Region-Werte
		sum = 0;
		n = region.size();
		for (int i = 0; i < n; i++) {
			int att1 = region.elementAt(i);
			for (int j = 0; j < n; j++) {
				int att2 = region.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.binary(att1, att2);
				}
			}
		}
		if (n > 1) {
			simRegion = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simRegion = 1;
		}
		// Ähnlichkeit aller Vdp-Werte
		sum = 0;
		n = vdp.size();
		for (int i = 0; i < n; i++) {
			int att1 = vdp.elementAt(i);
			for (int j = 0; j < n; j++) {
				int att2 = vdp.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.binary(att1, att2);
				}
			}
		}
		if (n > 1) {
			simVdp = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simVdp = 1;
		}
		// Ähnlichkeit aller Winery-Werte
		sum = 0;
		n = winery.size();
		for (int i = 0; i < n; i++) {
			int att1 = winery.elementAt(i);
			for (int j = 0; j < n; j++) {
				int att2 = winery.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.binary(att1, att2);
				}
			}
		}
		if (n > 1) {
			simWinery = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simWinery = 1;
		}
		// Ähnlichkeit aller WineStyle-Werte
		sum = 0;
		n = wineStyle.size();
		for (int i = 0; i < n; i++) {
			int att1 = wineStyle.elementAt(i);
			for (int j = 0; j < n; j++) {
				int att2 = wineStyle.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.wineStyle(att1, att2);
				}
			}
		}
		if (n > 1) {
			simWineStyle = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simWineStyle = 1;
		}
		// Ähnlichkeit aller Year-Werte
		sum = 0;
		n = year.size();
		for (int i = 0; i < n; i++) {
			int att1 = year.elementAt(i);
			for (int j = 0; j < n; j++) {
				int att2 = year.elementAt(j);
				if (i != j && att1 != -1 && att2 != -1) {
					sum += Similarity.binary(att1, att2);
				}
			}
		}
		if (n > 1) {
			simYear = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simYear = 1;
		}
		// Ähnlichkeit aller Aroma-Werte
		sum = 0;
		n = aroma.size();
		for (int i = 0; i < n; i++) {
			int[] att1 = aroma.elementAt(i);
			for (int j = 0; j < n; j++) {
				int[] att2 = aroma.elementAt(j);
				if (i != j && att1 != null && att2 != null) {
					// TODO ergänzen
					Similarity.aroma();
				}
			}
		}
		if (n > 1) {
			simAroma = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simAroma = 1;
		}
		// Ähnlichkeit aller Grape-Werte
		sum = 0;
		n = grape.size();
		for (int i = 0; i < n; i++) {
			int[] att1 = grape.elementAt(i);
			for (int j = 0; j < n; j++) {
				int[] att2 = grape.elementAt(j);
				if (i != j && att1 != null && att2 != null) {
					sum += Similarity.grape(att1, att2);
				}
			}
		}
		if (n > 1) {
			simGrape = (sum / ((n * n) - n));
		}
		if (n == 1) {
			simGrape = 1;
		}

		if (simAcid >= border * 2) {
			averageWine.setAcid(average(acid));
		}
		if (simAlcohol >= border * 2) {
			averageWine.setAlcohol(average(alcohol));
		}
		if (simPrice >= border * 2) {
			averageWine.setPrice(average(price));
		}
		if (simSweetness >= border * 2.5) {
			averageWine.setSweetness(average(sweetness));
		}
		if (simQuality >= border * 2) {
			averageWine.setQuality(maxOccurInt(quality));
		}
		if (simRegion >= border * 3) {
			averageWine.setRegion(maxOccurInt(region));
		}
		if (simVdp >= border * 4) {
			averageWine.setVdp(maxOccurInt(vdp));
		}
		if (simWinery >= border * 3) {
			averageWine.setWinery(maxOccurInt(winery));
		}
		if (simWineStyle >= border * 2) {
			averageWine.setWineStyle(maxOccurInt(wineStyle));
		}
		if (simYear >= border * 3) {
			averageWine.setYear(maxOccurInt(year));
		}
		if (simAroma >= border) {
			averageWine.setGrape(maxOccurArray(grape));
		}
		if (simGrape >= border) {
			averageWine.setAroma(maxOccurArray(aroma));
		}
	}

	public static double getSimAcid() {
		return simAcid;
	}

	public static void setSimAcid(double simAcid) {
		ContentBuyHistory.simAcid = simAcid;
	}

	public static double getSimAlcohol() {
		return simAlcohol;
	}

	public static void setSimAlcohol(double simAlcohol) {
		ContentBuyHistory.simAlcohol = simAlcohol;
	}

	public static double getSimPrice() {
		return simPrice;
	}

	public static void setSimPrice(double simPrice) {
		ContentBuyHistory.simPrice = simPrice;
	}

	public static double getSimSweetness() {
		return simSweetness;
	}

	public static void setSimSweetness(double simSweetness) {
		ContentBuyHistory.simSweetness = simSweetness;
	}

	public static double getSimQuality() {
		return simQuality;
	}

	public static void setSimQuality(double simQuality) {
		ContentBuyHistory.simQuality = simQuality;
	}

	public static double getSimRegion() {
		return simRegion;
	}

	public static void setSimRegion(double simRegion) {
		ContentBuyHistory.simRegion = simRegion;
	}

	public static double getSimVdp() {
		return simVdp;
	}

	public static void setSimVdp(double simVdp) {
		ContentBuyHistory.simVdp = simVdp;
	}

	public static double getSimWinery() {
		return simWinery;
	}

	public static void setSimWinery(double simWinery) {
		ContentBuyHistory.simWinery = simWinery;
	}

	public static double getSimWineStyle() {
		return simWineStyle;
	}

	public static void setSimWineStyle(double simWineStyle) {
		ContentBuyHistory.simWineStyle = simWineStyle;
	}

	public static double getSimYear() {
		return simYear;
	}

	public static void setSimYear(double simYear) {
		ContentBuyHistory.simYear = simYear;
	}

	public static double getSimAroma() {
		return simAroma;
	}

	public static void setSimAroma(double simAroma) {
		ContentBuyHistory.simAroma = simAroma;
	}

	public static double getSimGrape() {
		return simGrape;
	}

	public static void setSimGrape(double simGrape) {
		ContentBuyHistory.simGrape = simGrape;
	}
}
