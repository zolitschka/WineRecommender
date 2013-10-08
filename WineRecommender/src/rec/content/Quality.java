package rec.content;

public class Quality {

	/*
	 * Die Klasse Aroma stellt ein Qualit�ts-Objekt f�r das Attribut Qualitaet
	 * dar
	 * 
	 * Quality-Objekt mit Verh�ltnissen zu allen 7 Qualit�ten und
	 * Vergleichsmethode
	 * 
	 * qw = Qualit�tswein qw1 = Qualit�tswein b.A. qw2 = Qualit�tswein m.A. k =
	 * Kabinett sl = Sp�tlese al = Auslese bal = Beerenauslese tbal =
	 * Trockenbeerenauslese ew = Eiswein
	 */

	final private int quality;
	final private double relationToQW;
	final private double relationToK;
	final private double relationToSL;
	final private double relationToAL;
	final private double relationToBAL;
	final private double relationToTBAL;
	final private double relationToEW;

	public Quality(int quality, double qw, double k, double sl, double al,
			double bal, double tbal, double ew) {
		this.quality = quality;
		this.relationToQW = qw;
		this.relationToK = k;
		this.relationToSL = sl;
		this.relationToAL = al;
		this.relationToBAL = bal;
		this.relationToTBAL = tbal;
		this.relationToEW = ew;

	}

	// Vergleichs-Methode fuer Qualitaets-Objekte
	public double compareTo(int compareQuality) {
		if (compareQuality == 6 || compareQuality == 118)
			return relationToQW;
		else if (compareQuality == 117)
			return relationToK;
		else if (compareQuality == 116)
			return relationToSL;
		else if (compareQuality == 14)
			return relationToAL;
		else if (compareQuality == 115)
			return relationToBAL;
		else if (compareQuality == 449)
			return relationToTBAL;
		else
			return relationToEW;
	}

	public int getQuality() {
		return quality;
	}

}
