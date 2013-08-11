package rec.content;

import java.util.Collections;
import java.util.Vector;

//�hnlichkeitsma�e einzelner Attribute
public class Similarity {
	public static Vector<WineStyle> wineStyleVector = new Vector<WineStyle>();
	public static Vector<Quality> qualityVector = new Vector<Quality>();

	// Aroma-Taxonomie initialisieren nach Morbach
	Aroma aroma = new Aroma(null, 0);
	Aroma fruchtig = new Aroma(aroma, 0.1);
	Aroma w�rzig = new Aroma(aroma, 0.1);
	Aroma pflanzlich = new Aroma(aroma, 0.1);
	Aroma getrockneteFr�chte = new Aroma(fruchtig, 0.3);
	Aroma trockenFr�chte = new Aroma(fruchtig, 0.3);
	Aroma tropischeFr�chte = new Aroma(fruchtig, 0.3);
	Aroma schwarzeFr�chte = new Aroma(fruchtig, 0.3);
	Aroma roteFr�chte = new Aroma(fruchtig, 0.3);
	Aroma gelbeFr�chte = new Aroma(fruchtig, 0.3);
	Aroma gr�neFr�chte = new Aroma(fruchtig, 0.3);
	Aroma zitrusFr�chte = new Aroma(fruchtig, 0.3);
	Aroma hefeartig = new Aroma(w�rzig, 0.3);
	Aroma schmelzig = new Aroma(w�rzig, 0.3);
	Aroma mineralisch = new Aroma(w�rzig, 0.3);
	Aroma animalisch = new Aroma(w�rzig, 0.3);
	Aroma pikant = new Aroma(w�rzig, 0.3);
	Aroma s��lich = new Aroma(w�rzig, 0.3);
	Aroma blumig = new Aroma(pflanzlich, 0.3);
	Aroma nussig = new Aroma(pflanzlich, 0.3);
	Aroma holzig = new Aroma(pflanzlich, 0.3);
	Aroma getrocknetePflanzen = new Aroma(pflanzlich, 0.3);
	Aroma Gem�se = new Aroma(pflanzlich, 0.3);
	Aroma Pflanzen = new Aroma(pflanzlich, 0.3);
	Aroma sirupHonig = new Aroma(getrockneteFr�chte, 1);
	Aroma rosinen = new Aroma(trockenFr�chte, 1);
	Aroma feigen = new Aroma(trockenFr�chte, 1);
	Aroma mangoAnanasMelone = new Aroma(tropischeFr�chte, 1);
	Aroma litschiPapayaBanane = new Aroma(tropischeFr�chte, 1);
	Aroma brombeere = new Aroma(schwarzeFr�chte, 1);
	Aroma kirsche = new Aroma(schwarzeFr�chte, 1);
	Aroma schwarzeJohannisbeere = new Aroma(schwarzeFr�chte, 1);
	Aroma pflaumeHolunder = new Aroma(roteFr�chte, 1);
	Aroma erdbeere = new Aroma(roteFr�chte, 1);
	Aroma roteJohannisebeere = new Aroma(roteFr�chte, 1);
	Aroma birneQuitte = new Aroma(gelbeFr�chte, 1);
	Aroma aprikosePfirsisch = new Aroma(gelbeFr�chte, 1);
	Aroma mirabelle = new Aroma(gelbeFr�chte, 1);
	Aroma stachelbeere = new Aroma(gr�neFr�chte, 1);
	Aroma gr�nerApfel = new Aroma(gr�neFr�chte, 1);
	Aroma grapefruit = new Aroma(zitrusFr�chte, 1);
	Aroma zitrone = new Aroma(zitrusFr�chte, 1);
	Aroma brot = new Aroma(hefeartig, 1);
	Aroma toast = new Aroma(hefeartig, 1);
	Aroma hefe = new Aroma(hefeartig, 1);
	Aroma butter = new Aroma(schmelzig, 1);
	Aroma k�se = new Aroma(schmelzig, 1);
	Aroma karamell = new Aroma(schmelzig, 1);
	Aroma teer = new Aroma(animalisch, 1);

	// Weinstil-/Qualit�t-/Aroma-Vektor f�llen
	public Similarity() {
		initWineStyle();
		initQuality();
		aroma.setChilds(fruchtig, w�rzig, pflanzlich);
		fruchtig.setChilds(getrockneteFr�chte, trockenFr�chte,
				tropischeFr�chte, schwarzeFr�chte, roteFr�chte, gelbeFr�chte,
				gr�neFr�chte, zitrusFr�chte);
		w�rzig.setChilds(hefeartig, schmelzig, mineralisch, animalisch, pikant,
				s��lich);
		pflanzlich.setChilds(blumig, nussig, holzig, getrocknetePflanzen,
				Gem�se, Pflanzen);
		getrockneteFr�chte.setChilds(sirupHonig);
		trockenFr�chte.setChilds(rosinen, feigen);
		tropischeFr�chte.setChilds(mangoAnanasMelone, litschiPapayaBanane);
		schwarzeFr�chte.setChilds(brombeere, kirsche, schwarzeJohannisbeere);
		roteFr�chte.setChilds(pflaumeHolunder, erdbeere, roteJohannisebeere);
		gelbeFr�chte.setChilds(birneQuitte, aprikosePfirsisch, mirabelle);
		gr�neFr�chte.setChilds(stachelbeere, gr�nerApfel);
		zitrusFr�chte.setChilds(grapefruit, zitrone);
		hefeartig.setChilds(brot, toast, hefe);
		schmelzig.setChilds(butter, k�se, karamell);

	}

	private void initQuality() {
		// Qualit�t-Tabellen initialisieren nach Morbach & Gessinger
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

	// Bin�re Attribute (Year, Winery, Vdp, Region, Grape)
	public static double binary(int faktor1, int faktor2) {
		if (faktor1 == faktor2)
			return 1;
		else
			return 0;
	}

	public static double grape(Vector<Integer> grape1, Vector<Integer> grape2) {
		double result = 0;
		for (int i = 0; i < grape1.size(); i++) {
			for (int j = 0; j < grape2.size(); j++) {
				if (grape1.elementAt(i).equals(grape2.elementAt(j))) {
					result += 1;
				}
			}
		}
		return (result / grape1.size());
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

	// Eigenschaften mit �hnlichkeitsmatrix (WineStyle, Quality)
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

	// Taxonomien (Aroma)
	public void aroma() {

	}
}
