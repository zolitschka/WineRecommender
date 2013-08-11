package rec.content;

import java.util.Collections;
import java.util.Vector;

//Ähnlichkeitsmaße einzelner Attribute
public class Similarity {

	// Weinstil-Tabellen initialisieren nach Morbach & Gessinger
	public static Vector<WineStyle> wineStyleVector = new Vector<WineStyle>();

	private WineStyle ww1 = new WineStyle(357, 1, 0.5, 0.3, 0, 0, 0, 0, 0);
	private WineStyle ww2 = new WineStyle(358, 0.5, 1, 0.3, 0.2, 0, 0, 0, 0);
	private WineStyle ww3 = new WineStyle(359, 0.3, 0.3, 1, 0.3, 0, 0, 0, 0);
	private WineStyle ww4 = new WineStyle(360, 0, 0.2, 0.3, 1, 0, 0, 0, 0);
	private WineStyle rw1 = new WineStyle(361, 0, 0, 0, 0, 1, 0, 0, 0.2);
	private WineStyle rw2 = new WineStyle(362, 0, 0, 0, 0, 0, 1, 0.3, 0.5);
	private WineStyle rw3 = new WineStyle(363, 0, 0, 0, 0, 0, 0.3, 1, 0.3);
	private WineStyle rw4 = new WineStyle(364, 0, 0, 0, 0, 0.2, 0.5, 0.3, 1);

	// Qualität-Tabellen initialisieren nach Morbach & Gessinger
	public static Vector<Quality> qualityVector = new Vector<Quality>();

	private Quality qw1 = new Quality(6, 1, 0.8, 0, 0, 0, 0, 0);
	private Quality qw2 = new Quality(118, 1, 0.8, 0, 0, 0, 0, 0);
	private Quality k = new Quality(117, 0.8, 1, 0, 0, 0, 0, 0);
	private Quality sl = new Quality(116, 0, 0, 1, 0.8, 0, 0, 0);
	private Quality al = new Quality(14, 0, 0, 0.8, 1, 0.5, 0, 0.3);
	private Quality bal = new Quality(115, 0, 0, 0, 0.5, 1, 0.8, 0.8);
	private Quality tbal = new Quality(449, 0, 0, 0, 0, 0.8, 1, 0.8);
	private Quality ew = new Quality(450, 0, 0, 0, 0.3, 0.5, 0.5, 1);

	// Weinstil-Vektor füllen
	public Similarity() {
		Collections.addAll(wineStyleVector, ww1, ww2, ww3, ww4, rw1, rw2, rw3,
				rw4);
		Collections.addAll(qualityVector, qw1, qw2, k, sl, al, bal, tbal, ew);
	}

	// Binäre Attribute (Year, Winery, Vdp, Region)
	public static double year(int year1, int year2) {
		if (year1 == year2)
			return 1;
		else
			return 0;
	}

	public static double winery(int winery1, int winery2) {
		if (winery1 == winery2)
			return 1;
		else
			return 0;
	}

	public static double vdp(int vdp1, int vdp2) {
		if (vdp1 == vdp2)
			return 1;
		else
			return 0;
	}

	public static double region(int region1, int region2) {
		if (region1 == region2)
			return 1;
		else
			return 0;
	}

	// Numerische Attribute (Price, Alcohol, Sweetness, Acid)
	public static double price(double price1, double price2) {
		double min = 1.0;
		double max = 10.0;
		double asymmetricFactor = 0.9;

		if (price1 < price2)
			max *= asymmetricFactor;

		double difference = Math.abs(price1 - price2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	public static double alcohol(double alcohol1, double alcohol2) {
		double min = 0.5;
		double max = 2.0;
		double difference = Math.abs(alcohol1 - alcohol2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	public static double sweetness(double sweetness1, double sweetness2) {
		double min = 3.0;
		double max = 10.0;
		double difference = Math.abs(sweetness1 - sweetness2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	public static double acid(double acid1, double acid2) {
		double min = 0.1;
		double max = 1.0;
		double difference = Math.abs(acid1 - acid2);

		if (difference < min)
			return 1;
		else if (difference > max)
			return 0;
		else
			return ((max - difference) / (max - min));
	}

	// Eigenschaften mit Ähnlichkeitsmatrix (WineStyle, Quality)
	public static double wineStyle(int wineStyle1, int wineStyle2) {
		double result = 0;
		for (int i = 0; i < wineStyleVector.size(); i++) {
			WineStyle tmp = wineStyleVector.elementAt(i);
			if (tmp.getWineStyle() == wineStyle1)
				result = tmp.compareTo(wineStyle2);
		}
		return result;

	}

	public static double quality(int quality1, int quality2) {
		double result = 0;
		for (int i = 0; i < qualityVector.size(); i++) {
			Quality tmp = qualityVector.elementAt(i);
			if (tmp.getQuality() == quality1)
				result = tmp.compareTo(quality2);
		}
		return result;
	}

	// Taxonomien (Aroma, Grape)
	public void aroma() {

	}

	public void grape() {

	}

}
