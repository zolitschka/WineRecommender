package rec.content;

import java.util.Vector;

public class wine {
	private double acid;
	private double alcohol;
	private Vector<Integer> grape = new Vector<Integer>();
	private int id;
	private String name;
	private double price;
	private int quality;
	private int region;
	private double sweetness;
	private int taste;
	private int vdp;
	private int winery;
	private int wineStyle;
	private int year;

	public wine() {

	}

	public void print() {
		System.out.println("Name: " + name);
		System.out.println("ID: " + id);
		System.out.println("Säure: " + acid);
		System.out.println("Alkohol: " + alcohol);
		System.out.println("Rebsorte: " + grape);
		System.out.println("Preis: " + price);
		System.out.println("Qualität: " + quality);
		System.out.println("Region: " + region);
		System.out.println("Restzucker: " + sweetness);
		System.out.println("Geschmack: " + taste);
		System.out.println("VDP: " + vdp);
		System.out.println("Weingut: " + winery);
		System.out.println("Weinstil: " + wineStyle);
		System.out.println("Jahr: " + year);
		System.out.println();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
