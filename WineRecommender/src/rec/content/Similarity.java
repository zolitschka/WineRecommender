package rec.content;

import java.util.Vector;

public class Similarity {

	public void similarityAcid() {

	}

	public void similarityAlcohol() {

	}

	public void similarityAroma() {

	}

	public void similarityGrape() {

	}

	public void similarityPrice() {

	}

	public void similarityQuality() {

	}

	public void similaritySweetness() {

	}

	public void similarityWineStyle() {

	}

	public static double similarityYear(int year1, int year2) {
		if (year1 == year2)
			return 1;
		else
			return 0;
	}

	public static double similarityWinery(int winery1, int winery2) {
		if (winery1 == winery2)
			return 1;
		else
			return 0;
	}

	public static double similarityVdp(int vdp1, int vdp2) {
		if (vdp1 == vdp2)
			return 1;
		else
			return 0;
	}

	public static double similarityRegion(int region1, int region2) {
		if (region1 == region2)
			return 1;
		else
			return 0;
	}

}
