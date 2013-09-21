package rec.content;

import java.util.Vector;

import rec.User;
import rec.Wine;

//Präferenzprofil eines Users
public class Preference {
	private Vector<Wine> history;
	private static boolean preferenceBoolean = false;

	private double acidPreference = 1;
	private double alcoholPreference = 1;
	private double aromaPreference = 1;
	private double grapePreference = 1;
	private double pricePreference = 1;
	private double qualityPreference = 1;
	private double regionPreference = 1;
	private double sweetnessPreference = 1;
	private double vdpPreference = 1;
	private double wineryPreference = 1;
	private double wineStylePreference = 1;
	private double yearPreference = 1;

	private static Vector<Double> preferenceProfil = new Vector<Double>();
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
			System.out.println("preference");
			createProfil();
		}
		System.out.println(preferenceProfil);
	}

	public void createProfil() {
		history = currentUser.getProducts();
		if (history != null) {
			ContentBuyHistory.getAverageWine(history);

			Vector<Double> simVector = new Vector<Double>();
			simVector.add(ContentBuyHistory.getSimAcid());
			simVector.add(ContentBuyHistory.getSimAlcohol());
			simVector.add(ContentBuyHistory.getSimAroma());
			simVector.add(ContentBuyHistory.getSimGrape());
			simVector.add(ContentBuyHistory.getSimPrice());
			simVector.add(ContentBuyHistory.getSimQuality());
			simVector.add(ContentBuyHistory.getSimRegion());
			simVector.add(ContentBuyHistory.getSimSweetness());
			simVector.add(ContentBuyHistory.getSimVdp());
			simVector.add(ContentBuyHistory.getSimWinery());
			simVector.add(ContentBuyHistory.getSimWineStyle());
			simVector.add(ContentBuyHistory.getSimYear());

			// Preferencen anpassen
			for (int i = 0; i < preferenceProfil.size(); i++) {
				double difference = (simVector.elementAt(i) - 0.2) * 2;
				double weight = preferenceProfil.elementAt(i) + difference;
				preferenceProfil.setElementAt(weight, i);
				for (int j = 0; j < preferenceProfil.size(); j++) {
					if (i != j) {
						double currentWeight = preferenceProfil.elementAt(j);
						preferenceProfil.setElementAt(currentWeight
								- (difference / 11), j);
					}
				}
			}
		}
	}

	public static double getAcidWeight() {
		return preferenceProfil.elementAt(0);
	}

	public static double getAlcoholWeight() {
		return preferenceProfil.elementAt(1);
	}

	public static double getAromaWeight() {
		return preferenceProfil.elementAt(2);
	}

	public static double getGrapeWeight() {
		return preferenceProfil.elementAt(3);
	}

	public static double getPriceWeight() {
		return preferenceProfil.elementAt(4);
	}

	public static double getQualityWeight() {
		return preferenceProfil.elementAt(5);
	}

	public static double getRegionWeight() {
		return preferenceProfil.elementAt(6);
	}

	public static double getSweetnessWeight() {
		return preferenceProfil.elementAt(7);
	}

	public static double getVdpWeight() {
		return preferenceProfil.elementAt(8);
	}

	public static double getWineryWeight() {
		return preferenceProfil.elementAt(9);
	}

	public static double getWineStyleWeight() {
		return preferenceProfil.elementAt(10);
	}

	public static double getYearWeight() {
		return preferenceProfil.elementAt(11);
	}

	public static void setPreferenceBoolean(boolean bool) {
		preferenceBoolean = bool;
	}
}
