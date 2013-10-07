package rec.content;

import java.util.Collections;
import java.util.Vector;

import org.apache.commons.math3.geometry.euclidean.threed.Plane;

//Aehnlichkeitsmale einzelner Attribute
public class Similarity {
	public static Vector<WineStyle> wineStyleVector = new Vector<WineStyle>();
	public static Vector<Quality> qualityVector = new Vector<Quality>();

	// Aroma-Taxonomie initialisieren nach Morbach
	// TODO zuende machen
	static Aroma aromaTaxonomy = new Aroma(null, 0, 0);
	Aroma fruchtig = new Aroma(aromaTaxonomy, 0.1, 0);
	Aroma wuerzig = new Aroma(aromaTaxonomy, 0.1, 0);
	Aroma pflanzlich = new Aroma(aromaTaxonomy, 0.1, 0);

	Aroma gekochteFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma trockenFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma tropischeFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma schwarzeFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma roteFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma gelbeFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma grueneFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma zitrusFruechte = new Aroma(fruchtig, 0.3, 0);
	Aroma hefeartig = new Aroma(wuerzig, 0.3, 0);
	Aroma schmelzig = new Aroma(wuerzig, 0.3, 0);
	Aroma mineralisch = new Aroma(wuerzig, 0.3, 0);
	Aroma animalisch = new Aroma(wuerzig, 0.3, 0);
	Aroma pikant = new Aroma(wuerzig, 0.3, 0);
	Aroma suesslich = new Aroma(wuerzig, 0.3, 0);
	Aroma blumig = new Aroma(pflanzlich, 0.3, 0);
	Aroma nussig = new Aroma(pflanzlich, 0.3, 0);
	Aroma holzig = new Aroma(pflanzlich, 0.3, 0);
	Aroma getrocknetePflanzen = new Aroma(pflanzlich, 0.3, 0);
	Aroma gemuese = new Aroma(pflanzlich, 0.3, 0);
	Aroma pflanzen = new Aroma(pflanzlich, 0.3, 0);

	Aroma acatia = new Aroma(blumig, 1, 186);
	Aroma almonds = new Aroma(nussig, 1, 250);
	Aroma ananas = new Aroma(tropischeFruechte, 1, 225);
	Aroma anise = new Aroma(pikant, 1, 224);
	Aroma apple = new Aroma(roteFruechte, 1, 204);
	Aroma apple_green = new Aroma(grueneFruechte, 1, 465);
	Aroma apricot = new Aroma(gelbeFruechte, 1, 221);
	Aroma asparagus = new Aroma(gemuese, 1, 275);
	Aroma bacon = new Aroma(animalisch, 1, 276);
	Aroma banana = new Aroma(tropischeFruechte, 1, 220);
	Aroma bitter_chocolate = new Aroma(nussig, 1, 269);
	Aroma blackberry = new Aroma(schwarzeFruechte, 1, 218);
	Aroma black_cherries = new Aroma(schwarzeFruechte, 1, 273);
	Aroma black_currant = new Aroma(schwarzeFruechte, 1, 272);
	Aroma blueberry = new Aroma(schwarzeFruechte, 1, 466);
	Aroma bread = new Aroma(hefeartig, 1, 217);
	Aroma butter = new Aroma(schmelzig, 1, 216);
	Aroma cabbage = new Aroma(gemuese, 1, 188);
	Aroma cacao = new Aroma(nussig, 1, 193);
	Aroma caramel = new Aroma(suesslich, 1, 192);
	Aroma cedar = new Aroma(holzig, 1, 295);
	Aroma champignon = new Aroma(pflanzen, 1, 215);
	Aroma cheese = new Aroma(schmelzig, 1, 191);
	Aroma cherry = new Aroma(schwarzeFruechte, 1, 190);
	Aroma chocolate = new Aroma(nussig, 1, 268);
	Aroma cinnamon = new Aroma(suesslich, 1, 296);
	Aroma citrus_fruits = new Aroma(zitrusFruechte, 1, 350);
	Aroma cloves = new Aroma(suesslich, 1, 208);
	Aroma coconut = new Aroma(nussig, 1, 187);
	Aroma coffee = new Aroma(nussig, 1, 194);
	Aroma cranberries = new Aroma(roteFruechte, 1, 226);
	Aroma cream = new Aroma(schmelzig, 1, 266);
	Aroma elder = new Aroma(holzig, 1, 198);
	Aroma eucalyptus = new Aroma(pflanzen, 1, 211);
	Aroma fennel = new Aroma(pflanzen, 1, 304);
	Aroma figs = new Aroma(trockenFruechte, 1, 210);
	Aroma foliage = new Aroma(getrocknetePflanzen, 1, 257);
	Aroma geranium = new Aroma(pflanzen, 1, 209);
	Aroma ginger = new Aroma(suesslich, 1, 195);
	Aroma gooseberry = new Aroma(grueneFruechte, 1, 177);
	Aroma grapefuit = new Aroma(zitrusFruechte, 1, 207);
	Aroma gras = new Aroma(pflanzen, 1, 206);
	Aroma green_paprika = new Aroma(gemuese, 1, 205);
	Aroma hay = new Aroma(getrocknetePflanzen, 1, 200);
	Aroma hazelnut = new Aroma(nussig, 1, 203);
	Aroma herbs = new Aroma(getrocknetePflanzen, 1, 259);
	Aroma honey = new Aroma(gekochteFruechte, 1, 197);
	Aroma horseradish = new Aroma(pikant, 1, 305);
	Aroma hot_pepper = new Aroma(pikant, 1, 307);
	Aroma ivy = new Aroma(pflanzen, 1, 308);
	Aroma juniper = new Aroma(schwarzeFruechte, 1, 288);
	Aroma kiwi = new Aroma(tropischeFruechte, 1, 189);
	Aroma leather = new Aroma(animalisch, 1, 256);
	Aroma lemon = new Aroma(zitrusFruechte, 1, 297);
	Aroma licorice = new Aroma(pikant, 1, 258);
	Aroma lime = new Aroma(zitrusFruechte, 1, 255);
	Aroma lime_tree = new Aroma(zitrusFruechte, 1, 254);
	Aroma lovage = new Aroma(pflanzen, 1, 251);
	Aroma lychee = new Aroma(tropischeFruechte, 1, 253);
	Aroma mandarines = new Aroma(tropischeFruechte, 1, 252);
	Aroma mango = new Aroma(tropischeFruechte, 1, 249);
	Aroma maracuja = new Aroma(tropischeFruechte, 1, 248);
	Aroma mellow_apple = new Aroma(grueneFruechte, 1, 223);
	Aroma mellow_apples = new Aroma(roteFruechte, 1, 222);
	Aroma melon = new Aroma(tropischeFruechte, 1, 247);
	Aroma menthol = new Aroma(pflanzen, 1, 291);
	Aroma metal = new Aroma(mineralisch, 1, 246);
	Aroma mint = new Aroma(pflanzen, 1, 245);
	Aroma mirabelle = new Aroma(gelbeFruechte, 1, 244);
	Aroma mocha = new Aroma(nussig, 1, 351);
	Aroma moss = new Aroma(pflanzen, 1, 243);
	Aroma muscat = new Aroma(suesslich, 1, 241);
	Aroma musk = new Aroma(animalisch, 1, 242);
	Aroma muskmelon = new Aroma(suesslich, 1, 298);
	Aroma nuts = new Aroma(nussig, 1, 239);
	Aroma oak = new Aroma(holzig, 1, 303);
	Aroma olives = new Aroma(pikant, 1, 235);
	Aroma onion = new Aroma(pikant, 1, 306);
	Aroma orange = new Aroma(tropischeFruechte, 1, 237);
	Aroma orange_peel = new Aroma(suesslich, 1, 236);
	Aroma papaya = new Aroma(tropischeFruechte, 1, 234);
	Aroma paprika = new Aroma(gemuese, 1, 233);
	Aroma peach = new Aroma(gelbeFruechte, 1, 228);
	Aroma pear = new Aroma(gelbeFruechte, 1, 219);
	Aroma pepper = new Aroma(pikant, 1, 230);
	Aroma petrol = new Aroma(mineralisch, 1, 542);
	Aroma plum = new Aroma(roteFruechte, 1, 227);
	Aroma porcini = new Aroma(pflanzen, 1, 290);
	Aroma prune = new Aroma(trockenFruechte, 1, 214);
	Aroma punchbowl = new Aroma(gekochteFruechte, 1, 265);
	Aroma quince = new Aroma(gelbeFruechte, 1, 271);
	Aroma raised_pastry = new Aroma(hefeartig, 1, 201);
	Aroma raisins = new Aroma(trockenFruechte, 1, 261);
	Aroma raspberry = new Aroma(roteFruechte, 1, 199);
	Aroma red_currant = new Aroma(roteFruechte, 1, 264);
	Aroma roasted = new Aroma(holzig, 1, 263);
	Aroma roasted_nuts = new Aroma(nussig, 1, 238);
	Aroma rockmelon = new Aroma(tropischeFruechte, 1, 299);
	Aroma rose = new Aroma(blumig, 1, 260);
	Aroma rosemary = new Aroma(pflanzen, 1, 262);
	Aroma saffron = new Aroma(getrocknetePflanzen, 1, 380);
	Aroma smoke = new Aroma(holzig, 1, 270);
	Aroma soil = new Aroma(mineralisch, 1, 212);
	Aroma sour_cherries = new Aroma(schwarzeFruechte, 1, 355);
	Aroma straw = new Aroma(getrocknetePflanzen, 1, 279);
	Aroma strawberry = new Aroma(roteFruechte, 1, 213);
	Aroma syrup = new Aroma(gekochteFruechte, 1, 274);
	Aroma tar = new Aroma(mineralisch, 1, 282);
	Aroma tea = new Aroma(getrocknetePflanzen, 1, 281);
	Aroma toast = new Aroma(hefeartig, 1, 284);
	Aroma tobacco = new Aroma(holzig, 1, 280);
	Aroma toffee = new Aroma(schmelzig, 1, 267);
	Aroma truffles = new Aroma(animalisch, 1, 285);
	Aroma vanilla = new Aroma(suesslich, 1, 286);
	Aroma violets = new Aroma(blumig, 1, 287);
	Aroma walnut = new Aroma(nussig, 1, 289);
	Aroma wet_whool = new Aroma(animalisch, 1, 240);
	Aroma whitethorn = new Aroma(blumig, 1, 293);
	Aroma wood = new Aroma(holzig, 1, 196);
	Aroma yeast = new Aroma(hefeartig, 1, 202);

	// Weinstil-/Qualitaet-/Aroma-Vektor fuellen
	public Similarity() {
		initWineStyle();
		initQuality();
		aromaTaxonomy.setChilds(fruchtig, wuerzig, pflanzlich);
		fruchtig.setChilds(gekochteFruechte, trockenFruechte,
				tropischeFruechte, schwarzeFruechte, roteFruechte,
				gelbeFruechte, grueneFruechte, zitrusFruechte);
		wuerzig.setChilds(hefeartig, schmelzig, mineralisch, animalisch,
				pikant, suesslich);
		pflanzlich.setChilds(blumig, nussig, holzig, getrocknetePflanzen,
				gemuese, pflanzen);
		gekochteFruechte.setChilds(honey, punchbowl, syrup);
		trockenFruechte.setChilds(figs, prune, raisins);
		tropischeFruechte.setChilds(ananas, banana, kiwi, lychee, mandarines,
				mango, maracuja, melon, orange, papaya, rockmelon);
		schwarzeFruechte.setChilds(blackberry, black_cherries, black_currant,
				blueberry, cherry, juniper, sour_cherries);
		roteFruechte.setChilds(apple, cranberries, mellow_apples, plum,
				raspberry, red_currant, strawberry);
		gelbeFruechte.setChilds(apricot, mirabelle, peach, pear, quince);
		grueneFruechte.setChilds(apple_green, gooseberry, mellow_apple);
		zitrusFruechte.setChilds(citrus_fruits, grapefuit, lemon, lime,
				lime_tree);
		hefeartig.setChilds(bread, raised_pastry, toast, yeast);
		schmelzig.setChilds(butter, cheese, cream, toffee);
		mineralisch.setChilds(metal, petrol, soil, tar);
		animalisch.setChilds(bacon, leather, musk, truffles, wet_whool);
		pikant.setChilds(anise, horseradish, hot_pepper, licorice, olives,
				onion, pepper);
		suesslich.setChilds(caramel, cinnamon, cloves, ginger, muscat,
				muskmelon, orange_peel, vanilla);
		blumig.setChilds(acatia, rose, violets, whitethorn);
		nussig.setChilds(almonds, bitter_chocolate, cacao, chocolate, coconut,
				walnut, coffee, hazelnut, mocha, nuts, roasted_nuts);
		holzig.setChilds(cedar, elder, oak, roasted, smoke, tobacco, wood);
		getrocknetePflanzen.setChilds(foliage, hay, herbs, saffron, straw, tea);
		gemuese.setChilds(asparagus, cabbage, green_paprika, paprika);
		pflanzen.setChilds(champignon, eucalyptus, fennel, geranium, gras, ivy,
				lovage, menthol, mint, moss, porcini, rosemary);
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
					break;
				}
				// Abfangen verschiedener Bezeichnungen für eine Rebsorte
				else if (ks1[i] == 171 && ks2[j] == 131 || ks1[i] == 131
						&& ks2[j] == 171) {
					result += 1;
					break;

				} else if (ks1[i] == 151 && ks2[j] == 152 || ks1[i] == 152
						&& ks2[j] == 151) {
					result += 1;
					break;
				} else if (ks1[i] == 139 && ks2[j] == 505 || ks1[i] == 505
						&& ks2[j] == 139) {
					result += 1;
					break;
				} else if (ks1[i] == 141 && ks2[j] == 169 || ks1[i] == 169
						&& ks2[j] == 141) {
					result += 1;
					break;
				} else if (ks1[i] == 150 && ks2[j] == 149 || ks1[i] == 149
						&& ks2[j] == 150) {
					result += 1;
					break;
				} else if (ks1[i] == 155 && ks2[j] == 157 || ks1[i] == 157
						&& ks2[j] == 155) {
					result += 1;
					break;
				} else if (ks1[i] == 159 && ks2[j] == 160 || ks1[i] == 160
						&& ks2[j] == 159) {
					result += 1;
					break;
				} else if (ks1[i] == 166 && ks2[j] == 167 || ks1[i] == 167
						&& ks2[j] == 166) {
					result += 1;
					break;
				} else if (ks1[i] == 512 && ks2[j] == 429 || ks1[i] == 429
						&& ks2[j] == 512) {
					result += 1;
					break;
				} else if (ks1[i] == 155 && ks2[j] == 156 || ks1[i] == 156
						&& ks2[j] == 155) {
					result += 1;
					break;
				} else if (ks1[i] == 156 && ks2[j] == 157 || ks1[i] == 157
						&& ks2[j] == 156) {
					result += 1;
					break;
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
	public static double aroma(int[] ks1, int[] ks2) {
		double result = 0;
		for (int i = 0; i < ks1.length; i++) {
			double maxSim = 0;
			for (int j = 0; j < ks2.length; j++) {
				double tmpSim = tax(ks1[i], ks2[j]);
				if (tmpSim > maxSim) {
					maxSim = tmpSim;
				}
				// if (ks1[i] == (ks2[j])) {
				// result += 1;
				// break;
				// }
			}
			result += maxSim;
		}
		return (result / ks1.length);
	}

	private static double tax(int i, int j) {
		Aroma a1 = searchAroma(i);
		Aroma a2 = searchAroma(j);

		while (a1 != a2) {
			a1 = a1.getParent();
			a2 = a2.getParent();
		}

		return a1.getSimilarity();
	}

	// TODO null abfangen
	private static Aroma searchAroma(int value) {
		Aroma level0 = aromaTaxonomy;
		Aroma tmp = null;
		for (int i = 0; i < level0.getChilds().size(); i++) {
			Aroma level1 = level0.getChilds().elementAt(i);
			for (int j = 0; j < level1.getChilds().size(); j++) {
				Aroma level2 = level1.getChilds().elementAt(i);
				for (int k = 0; k < level2.getChilds().size(); k++) {
					Aroma level3 = level1.getChilds().elementAt(i);
					if (level3.getNumber() == value) {
						tmp = level3;
					}
				}
			}
		}
		return tmp;
	}
}
