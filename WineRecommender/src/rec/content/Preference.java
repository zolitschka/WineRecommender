package rec.content;

import java.util.Vector;

import rec.User;
import rec.Wine;

/*
 * 
 * Die Klasse Preference berechnet ein Praeferenzprofil für einen User anhand aller gekauften Weine des Users
 * 
 */

public class Preference {
	private Vector<Wine> history;
	private static boolean preferenceBoolean = false;

	private static int acidPreference = 1;
	private static int alcoholPreference = 1;
	private static int aromaPreference = 1;
	private static int grapePreference = 1;
	private static int pricePreference = 1;
	private static int qualityPreference = 1;
	private static int regionPreference = 1;
	private static int sweetnessPreference = 1;
	private static int vdpPreference = 1;
	private static int wineryPreference = 1;
	private static int wineStylePreference = 1;
	private static int yearPreference = 1;

	private User currentUser;

	public Preference(User user) {
		currentUser = user;

		if (preferenceBoolean) {
			createProfil();
		}
	}

	// erzeugt das Praeferenzprofil
	public void createProfil() {
		history = currentUser.getProducts();
		if (history != null) {
			ContentBuyHistory.getAverageWine(history);

			/*
			 * TODO Hier fehlt: -Ähnlichkeit einzelner Attribute zueinander
			 * -Regel ab welcher Ähnlichkeit Veränderung der Praeferenz -Stärke
			 * der Veränderung
			 */
		}
	}

	public static double getAcidWeight() {
		return acidPreference;
	}

	public static double getAlcoholWeight() {
		return alcoholPreference;
	}

	public static double getAromaWeight() {
		return aromaPreference;
	}

	public static double getGrapeWeight() {
		return grapePreference;
	}

	public static double getPriceWeight() {
		return pricePreference;
	}

	public static double getQualityWeight() {
		return qualityPreference;
	}

	public static double getRegionWeight() {
		return regionPreference;
	}

	public static double getSweetnessWeight() {
		return sweetnessPreference;
	}

	public static double getVdpWeight() {
		return vdpPreference;
	}

	public static double getWineryWeight() {
		return wineryPreference;
	}

	public static double getWineStyleWeight() {
		return wineStylePreference;
	}

	public static double getYearWeight() {
		return yearPreference;
	}

	public static void setPreferenceBoolean(boolean bool) {
		preferenceBoolean = bool;
	}
}
