package rec.content;

import java.util.Collections;
import java.util.Vector;

import rec.User;
import rec.Wine;

//Präferenzprofil eines Users
public class Preference {
	private Vector<Wine> history;
	private static boolean preferenceBoolean = false;

	private StringDoubleObject acidPreference = new StringDoubleObject("acid",
			1);
	private StringDoubleObject alcoholPreference = new StringDoubleObject(
			"alcohol", 1);
	private StringDoubleObject aromaPreference = new StringDoubleObject(
			"aroma", 1);
	private StringDoubleObject grapePreference = new StringDoubleObject(
			"grape", 1);
	private StringDoubleObject pricePreference = new StringDoubleObject(
			"price", 1);
	private StringDoubleObject qualityPreference = new StringDoubleObject(
			"quality", 1);
	private StringDoubleObject regionPreference = new StringDoubleObject(
			"region", 1);
	private StringDoubleObject sweetnessPreference = new StringDoubleObject(
			"sweetness", 1);
	private StringDoubleObject vdpPreference = new StringDoubleObject("vdp", 1);
	private StringDoubleObject wineryPreference = new StringDoubleObject(
			"winery", 1);
	private StringDoubleObject wineStylePreference = new StringDoubleObject(
			"wineStyle", 1);
	private StringDoubleObject yearPreference = new StringDoubleObject("year",
			1);

	private static Vector<StringDoubleObject> preferenceProfil = new Vector<StringDoubleObject>();
	private User currentUser;

	public Preference(User user) {
		currentUser = user;
		preferenceProfil.removeAllElements();
		preferenceProfil.add(acidPreference);
		preferenceProfil.add(alcoholPreference);
		preferenceProfil.add(aromaPreference);
		preferenceProfil.add(grapePreference);
		preferenceProfil.add(pricePreference);
		preferenceProfil.add(qualityPreference);
		preferenceProfil.add(regionPreference);
		preferenceProfil.add(sweetnessPreference);
		preferenceProfil.add(vdpPreference);
		preferenceProfil.add(wineryPreference);
		preferenceProfil.add(wineStylePreference);
		preferenceProfil.add(yearPreference);

		if (preferenceBoolean) {
			createProfil();
		}
	}

	public void createProfil() {
		history = currentUser.getProducts();
		if (history != null) {
			ContentBuyHistory.getAverageWine(history);

			Vector<StringDoubleObject> simVector = new Vector<StringDoubleObject>();
			simVector.add(new StringDoubleObject("simAcid", ContentBuyHistory
					.getSimAcid()));
			simVector.add((new StringDoubleObject("simAlcoho",
					ContentBuyHistory.getSimAlcohol())));
			simVector.add((new StringDoubleObject("simAroma", ContentBuyHistory
					.getSimAroma())));
			simVector.add((new StringDoubleObject("simGrape", ContentBuyHistory
					.getSimGrape())));
			simVector.add((new StringDoubleObject("simPrice", ContentBuyHistory
					.getSimPrice())));
			simVector.add((new StringDoubleObject("simQuality",
					ContentBuyHistory.getSimQuality())));
			simVector.add((new StringDoubleObject("simRegion",
					ContentBuyHistory.getSimRegion())));
			simVector.add((new StringDoubleObject("simSweetness",
					ContentBuyHistory.getSimSweetness())));
			simVector.add((new StringDoubleObject("simVdp", ContentBuyHistory
					.getSimVdp())));
			simVector.add((new StringDoubleObject("simWinery",
					ContentBuyHistory.getSimWinery())));
			simVector.add((new StringDoubleObject("simWineStyle",
					ContentBuyHistory.getSimWineStyle())));
			simVector.add((new StringDoubleObject("simYear", ContentBuyHistory
					.getSimYear())));
			Collections.sort(simVector);
			System.out.println(simVector);

			// ******************************
			// ******NACHRICHT FÜR NICO******
			// ******************************
			// Vorschlag: die besten 3 hochbewerten, die schlechtesten 3 runter
			// ich glaube die similarity der Attribute (simRegion etc.) ist
			// falsch, da oft 0.

			// Preferencen anpassen
			// for (int i = 0; i < preferenceProfil.size(); i++) {
			// double difference = (simVector.elementAt(i) - 0.2) * 2;
			// double weight = preferenceProfil.elementAt(i) + difference;
			// preferenceProfil.setElementAt(weight, i);
			// for (int j = 0; j < preferenceProfil.size(); j++) {
			// if (i != j) {
			// double currentWeight = preferenceProfil.elementAt(j);
			// preferenceProfil.setElementAt(currentWeight
			// - (difference / 11), j);
			// }
			// }
			// }
		}
	}

	public static double getAcidWeight() {
		return preferenceProfil.elementAt(0).getDouble();
	}

	public static double getAlcoholWeight() {
		return preferenceProfil.elementAt(1).getDouble();
	}

	public static double getAromaWeight() {
		return preferenceProfil.elementAt(2).getDouble();
	}

	public static double getGrapeWeight() {
		return preferenceProfil.elementAt(3).getDouble();
	}

	public static double getPriceWeight() {
		return preferenceProfil.elementAt(4).getDouble();
	}

	public static double getQualityWeight() {
		return preferenceProfil.elementAt(5).getDouble();
	}

	public static double getRegionWeight() {
		return preferenceProfil.elementAt(6).getDouble();
	}

	public static double getSweetnessWeight() {
		return preferenceProfil.elementAt(7).getDouble();
	}

	public static double getVdpWeight() {
		return preferenceProfil.elementAt(8).getDouble();
	}

	public static double getWineryWeight() {
		return preferenceProfil.elementAt(9).getDouble();
	}

	public static double getWineStyleWeight() {
		return preferenceProfil.elementAt(10).getDouble();
	}

	public static double getYearWeight() {
		return preferenceProfil.elementAt(11).getDouble();
	}

	public static void setPreferenceBoolean(boolean bool) {
		preferenceBoolean = bool;
	}
}
