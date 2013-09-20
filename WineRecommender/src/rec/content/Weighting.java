package rec.content;

//Gewichtung der einzelnen Weinattribute
public class Weighting {
	private static double acidWeight = 0.02;
	private static double alcoholWeight = 0.02;
	private static double aromaWeight = 0.14;
	private static double grapeWeight = 0.18;
	private static double priceWeight = 0.07;
	private static double qualityWeight = 0.07;
	private static double regionWeight = 0.09;
	private static double sweetnessWeight = 0.14;
	private static double vdpWeight = 0.02;
	private static double wineryWeight = 0.08;
	private static double wineStyleWeight = 0.1;
	private static double yearWeight = 0.07;

	public Weighting() {

	}

	public static double getAcidWeight() {
		return acidWeight;
	}

	public static double getAlcoholWeight() {
		return alcoholWeight;
	}

	public static double getAromaWeight() {
		return aromaWeight;
	}

	public static double getGrapeWeight() {
		return grapeWeight;
	}

	public static double getPriceWeight() {
		return priceWeight;
	}

	public static double getQualityWeight() {
		return qualityWeight;
	}

	public static double getRegionWeight() {
		return regionWeight;
	}

	public static double getSweetnessWeight() {
		return sweetnessWeight;
	}

	public static double getVdpWeight() {
		return vdpWeight;
	}

	public static double getWineryWeight() {
		return wineryWeight;
	}

	public static double getWineStyleWeight() {
		return wineStyleWeight;
	}

	public static double getYearWeight() {
		return yearWeight;
	}

	public static void setAcidWeight(double acidWeight) {
		Weighting.acidWeight = acidWeight;
	}

	public static void setAlcoholWeight(double alcoholWeight) {
		Weighting.alcoholWeight = alcoholWeight;
	}

	public static void setAromaWeight(double aromaWeight) {
		Weighting.aromaWeight = aromaWeight;
	}

	public static void setGrapeWeight(double grapeWeight) {
		Weighting.grapeWeight = grapeWeight;
	}

	public static void setPriceWeight(double priceWeight) {
		Weighting.priceWeight = priceWeight;
	}

	public static void setQualityWeight(double qualityWeight) {
		Weighting.qualityWeight = qualityWeight;
	}

	public static void setRegionWeight(double regionWeight) {
		Weighting.regionWeight = regionWeight;
	}

	public static void setSweetnessWeight(double sweetnessWeight) {
		Weighting.sweetnessWeight = sweetnessWeight;
	}

	public static void setVdpWeight(double vdpWeight) {
		Weighting.vdpWeight = vdpWeight;
	}

	public static void setWineryWeight(double wineryWeight) {
		Weighting.wineryWeight = wineryWeight;
	}

	public static void setWineStyleWeight(double wineStyleWeight) {
		Weighting.wineStyleWeight = wineStyleWeight;
	}

	public static void setYearWeight(double yearWeight) {
		Weighting.yearWeight = yearWeight;
	}

}
