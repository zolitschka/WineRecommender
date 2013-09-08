package rec.content;

import java.util.Collections;
import java.util.Vector;

//Aehnlichkeitsmale einzelner Attribute
public class Similarity {
	public static Vector<WineStyle> wineStyleVector = new Vector<WineStyle>();
	public static Vector<Quality> qualityVector = new Vector<Quality>();

	// Aroma-Taxonomie initialisieren nach Morbach
	// TODO zuende machen
	Aroma aroma = new Aroma(null, 0);
	Aroma fruchtig = new Aroma(aroma, 0.1);
	Aroma wuerzig = new Aroma(aroma, 0.1);
	Aroma pflanzlich = new Aroma(aroma, 0.1);
	Aroma getrockneteFruechte = new Aroma(fruchtig, 0.3);
	Aroma trockenFruechte = new Aroma(fruchtig, 0.3);
	Aroma tropischeFruechte = new Aroma(fruchtig, 0.3);
	Aroma schwarzeFruechte = new Aroma(fruchtig, 0.3);
	Aroma roteFruechte = new Aroma(fruchtig, 0.3);
	Aroma gelbeFruechte = new Aroma(fruchtig, 0.3);
	Aroma grueneFruechte = new Aroma(fruchtig, 0.3);
	Aroma zitrusFruechte = new Aroma(fruchtig, 0.3);
	Aroma hefeartig = new Aroma(wuerzig, 0.3);
	Aroma schmelzig = new Aroma(wuerzig, 0.3);
	Aroma mineralisch = new Aroma(wuerzig, 0.3);
	Aroma animalisch = new Aroma(wuerzig, 0.3);
	Aroma pikant = new Aroma(wuerzig, 0.3);
	Aroma suesslich = new Aroma(wuerzig, 0.3);
	Aroma blumig = new Aroma(pflanzlich, 0.3);
	Aroma nussig = new Aroma(pflanzlich, 0.3);
	Aroma holzig = new Aroma(pflanzlich, 0.3);
	Aroma getrocknetePflanzen = new Aroma(pflanzlich, 0.3);
	Aroma Gemuese = new Aroma(pflanzlich, 0.3);
	Aroma Pflanzen = new Aroma(pflanzlich, 0.3);
	Aroma sirupHonig = new Aroma(getrockneteFruechte, 1);
	Aroma rosinen = new Aroma(trockenFruechte, 1);
	Aroma feigen = new Aroma(trockenFruechte, 1);
	Aroma mangoAnanasMelone = new Aroma(tropischeFruechte, 1);
	Aroma litschiPapayaBanane = new Aroma(tropischeFruechte, 1);
	Aroma brombeere = new Aroma(schwarzeFruechte, 1);
	Aroma kirsche = new Aroma(schwarzeFruechte, 1);
	Aroma schwarzeJohannisbeere = new Aroma(schwarzeFruechte, 1);
	Aroma pflaumeHolunder = new Aroma(roteFruechte, 1);
	Aroma erdbeere = new Aroma(roteFruechte, 1);
	Aroma roteJohannisebeere = new Aroma(roteFruechte, 1);
	Aroma birneQuitte = new Aroma(gelbeFruechte, 1);
	Aroma aprikosePfirsisch = new Aroma(gelbeFruechte, 1);
	Aroma mirabelle = new Aroma(gelbeFruechte, 1);
	Aroma stachelbeere = new Aroma(grueneFruechte, 1);
	Aroma gruenerApfel = new Aroma(grueneFruechte, 1);
	Aroma grapefruit = new Aroma(zitrusFruechte, 1);
	Aroma zitrone = new Aroma(zitrusFruechte, 1);
	Aroma brot = new Aroma(hefeartig, 1);
	Aroma toast = new Aroma(hefeartig, 1);
	Aroma hefe = new Aroma(hefeartig, 1);
	Aroma butter = new Aroma(schmelzig, 1);
	Aroma kaese = new Aroma(schmelzig, 1);
	Aroma karamell = new Aroma(schmelzig, 1);
	Aroma teer = new Aroma(animalisch, 1);

	// Weinstil-/Qualitaet-/Aroma-Vektor fuellen
	public Similarity() {
		initWineStyle();
		initQuality();
		aroma.setChilds(fruchtig, wuerzig, pflanzlich);
		fruchtig.setChilds(getrockneteFruechte, trockenFruechte,
				tropischeFruechte, schwarzeFruechte, roteFruechte,
				gelbeFruechte, grueneFruechte, zitrusFruechte);
		wuerzig.setChilds(hefeartig, schmelzig, mineralisch, animalisch,
				pikant, suesslich);
		pflanzlich.setChilds(blumig, nussig, holzig, getrocknetePflanzen,
				Gemuese, Pflanzen);
		getrockneteFruechte.setChilds(sirupHonig);
		trockenFruechte.setChilds(rosinen, feigen);
		tropischeFruechte.setChilds(mangoAnanasMelone, litschiPapayaBanane);
		schwarzeFruechte.setChilds(brombeere, kirsche, schwarzeJohannisbeere);
		roteFruechte.setChilds(pflaumeHolunder, erdbeere, roteJohannisebeere);
		gelbeFruechte.setChilds(birneQuitte, aprikosePfirsisch, mirabelle);
		grueneFruechte.setChilds(stachelbeere, gruenerApfel);
		zitrusFruechte.setChilds(grapefruit, zitrone);
		hefeartig.setChilds(brot, toast, hefe);
		schmelzig.setChilds(butter, kaese, karamell);

	}

	private void initQuality() {
		// Qualitaet-Tabellen initialisieren nach Morbach & Gessinger
		Quality qw1 = new Quality(6, 1, 0.8, 0, 0, 0, 0, 0);
		Quality qw2 = new Quality(118, 1, 0.8, 0, 0, 0, 0, 0);
		Quality k = new Quality(117, 0.8, 1, 0, 0, 0, 0, 0);
		Quality sl = new Quality(116, 0, 0, 1, 0.8, 0, 0, 0);
		Quality al = new Quality(14, 0, 0, 0.8, 1, 0.5, 0, 0.3);
		Quality bal = new Quality(115, 0, 0, 0, 0.5, 1, 0.8, 0.8);
		Quality tbal = new Quality(449, 0, 0, 0, 0, 0.8, 1, 0.8);
		Quality ew = new Quality(450, 0, 0, 0, 0.3, 0.5, 0.5, 1);
		Collections.addAll(qualityVector, qw1, qw2, k, sl, al, bal, tbal, ew);
	}

	private void initWineStyle() {
		// Weinstil-Tabellen initialisieren nach Morbach & Gessinger
		WineStyle ww1 = new WineStyle(357, 1, 0.5, 0.3, 0, 0, 0, 0, 0);
		WineStyle ww2 = new WineStyle(358, 0.5, 1, 0.3, 0.2, 0, 0, 0, 0);
		WineStyle ww3 = new WineStyle(359, 0.3, 0.3, 1, 0.3, 0, 0, 0, 0);
		WineStyle ww4 = new WineStyle(360, 0, 0.2, 0.3, 1, 0, 0, 0, 0);
		WineStyle rw1 = new WineStyle(361, 0, 0, 0, 0, 1, 0, 0, 0.2);
		WineStyle rw2 = new WineStyle(362, 0, 0, 0, 0, 0, 1, 0.3, 0.5);
		WineStyle rw3 = new WineStyle(363, 0, 0, 0, 0, 0, 0.3, 1, 0.3);
		WineStyle rw4 = new WineStyle(364, 0, 0, 0, 0, 0.2, 0.5, 0.3, 1);
		Collections.addAll(wineStyleVector, ww1, ww2, ww3, ww4, rw1, rw2, rw3,
				rw4);
	}

	// Binaere Attribute (Year, Winery, Vdp, Region, Grape)
	public static double binary(int faktor1, int faktor2) {
		if (faktor1 == faktor2)
			return 1;
		else
			return 0;
	}

	public static double grape(int[] ks1, int[] ks2) {
		double result = 0;
		for (int i = 0; i < ks1.length; i++) {
			for (int j = 0; j < ks2.length; j++) {
				if (ks1[i] == (ks2[j])) {
					result += 1;
				}
			}
		}
		return (result / ks1.length);
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

	// Eigenschaften mit Aehnlichkeitsmatrix (WineStyle, Quality)
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

	// TODO Taxonomien (Aroma)
	public void aroma() {

	}
}
