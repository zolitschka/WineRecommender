package rec;

import java.util.Vector;

//Weinobjekt für die content-based Empfehlung und Basis für den Weinvektor
public class Wine {
	private double acid;
	private double alcohol;
	private Vector<Integer> aroma = new Vector<Integer>();
	private Vector<Integer> grape = new Vector<Integer>();
	private int wineId;
	private String wineName;
	private double wineScore; 
	

	private double price;
	private int quality;
	private int region;
	private double sweetness;
	private int taste;
	private int vdp;
	private int winery;
	private int wineStyle;
	private int year;

	public Wine() {

	}

	public int getId() {
		return wineId;
	}

	public void setId(int wineId) {
		this.wineId = wineId;
	}

	public String getName() {
		return wineName;
	}

	public void setName(String wineName) {
		this.wineName = wineName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getWinery() {
		return winery;
	}

	public void setWinery(int winery) {
		this.winery = winery;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public double getAcid() {
		return acid;
	}

	public void setAcid(double acid) {
		this.acid = acid;
	}

	public double getSweetness() {
		return sweetness;
	}

	public void setSweetness(double sweetness) {
		this.sweetness = sweetness;
	}

	public int getVdp() {
		return vdp;
	}

	public void setVdp(int vdp) {
		this.vdp = vdp;
	}

	public Vector<Integer> getGrape() {
		return grape;
	}

	public void addGrape(int grape) {
		this.grape.add(grape);
	}

	public Vector<Integer> getAroma() {
		return aroma;
	}

	public void addAroma(int aroma) {
		this.aroma.add(aroma);
	}

	public int getWineStyle() {
		return wineStyle;
	}

	public void setWineStyle(int wineStyle) {
		this.wineStyle = wineStyle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString() {
		return "ID " + getId() + ": " + getName();
	}
	public double getWineScore() {
		return wineScore;
	}

	public void setWineScore(double wineScore) {
		this.wineScore = wineScore;
	}

}
