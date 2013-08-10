package rec.content;


//Gewichtung der einzelnen Weinattribute
public class Weighting {
	private double acidWeight = 0.075;
	private double alcoholWeight = 0.075;
	private double aromaWeight = 0.15;
	private double grapeWeight = 0.1;
	private double priceWeight = 0.1;
	private double qualityWeight = 0.1;
	private double regionWeight = 0.0;
	private double sweetnessWeight = 0.1;
	private double vdpWeight = 0.05;
	private double wineryWeight = 0.05;
	private double wineStyleWeight = 0.15;
	private double yearWeight = 0.05;

	public Weighting() {

	}

	public double getAcidWeight() {
		return acidWeight;
	}

	public void setAcidWeight(double acidWeight) {
		this.acidWeight = acidWeight;
	}

	public double getAlcoholWeight() {
		return alcoholWeight;
	}

	public void setAlcoholWeight(double alcoholWeight) {
		this.alcoholWeight = alcoholWeight;
	}

	public double getAromaWeight() {
		return aromaWeight;
	}

	public void setAromaWeight(double aromaWeight) {
		this.aromaWeight = aromaWeight;
	}

	public double getGrapeWeight() {
		return grapeWeight;
	}

	public void setGrapeWeight(double grapeWeight) {
		this.grapeWeight = grapeWeight;
	}

	public double getPriceWeight() {
		return priceWeight;
	}

	public void setPriceWeight(double priceWeight) {
		this.priceWeight = priceWeight;
	}

	public double getQualityWeight() {
		return qualityWeight;
	}

	public void setQualityWeight(double qualityWeight) {
		this.qualityWeight = qualityWeight;
	}

	public double getRegionWeight() {
		return regionWeight;
	}

	public void setRegionWeight(double regionWeight) {
		this.regionWeight = regionWeight;
	}

	public double getSweetnessWeight() {
		return sweetnessWeight;
	}

	public void setSweetnessWeight(double sweetnessWeight) {
		this.sweetnessWeight = sweetnessWeight;
	}

	public double getVdpWeight() {
		return vdpWeight;
	}

	public void setVdpWeight(double vdpWeight) {
		this.vdpWeight = vdpWeight;
	}

	public double getWineryWeight() {
		return wineryWeight;
	}

	public void setWineryWeight(double wineryWeight) {
		this.wineryWeight = wineryWeight;
	}

	public double getWineStyleWeight() {
		return wineStyleWeight;
	}

	public void setWineStyleWeight(double wineStyleWeight) {
		this.wineStyleWeight = wineStyleWeight;
	}

	public double getYearWeight() {
		return yearWeight;
	}

	public void setYearWeight(double yearWeight) {
		this.yearWeight = yearWeight;
	}

}
