package rec.content;


//Ähnlichkeitsmaße einzelner Attribute
public class Similarity {

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
	public void wineStyle() {

	}

	public void quality() {

	}

	// Taxonomien (Aroma, Grape)
	public void aroma() {

	}

	public void grape() {

	}

}
