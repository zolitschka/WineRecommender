package rec.content;

import java.util.Vector;

import rec.User;
import rec.Wine;
import rec.database.GetBuyHistory;

//Präferenzprofil eines Users
public class Preference {
	private static Vector<User> userList = GetBuyHistory.getUserList();
	private Vector<Wine> history;

	final private static double acidWeight = 1;
	final private static double alcoholWeight = 1;
	final private static double aromaWeight =1;
	final private static double grapeWeight = 1;
	final private static double priceWeight = 1;
	final private static double qualityWeight = 1;
	final private static double regionWeight = 1;
	final private static double sweetnessWeight = 1;
	final private static double vdpWeight = 1;
	final private static double wineryWeight = 1;
	final private static double wineStyleWeight = 1;
	final private static double yearWeight = 1;

	public void createProfil(User user) {
		User currentUser = search(user.getId());
		history = currentUser.getProducts();
		Wine averageWine = contentBuyHistory.getAverageWine(history);
	}

	// Suche nach User mit Hilfe der ID
	private static User search(int id) {
		User result = null;

		for (int i = 0; i < userList.size(); i++) {
			User tmp = userList.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}

	public static double getAcidWeight() {
		return acidWeight;
	}

	public static double getAlcoholWeight() {
		return alcoholWeight;
	}

	public static double getAromaWeight() {
		return aromaWeight;
	}

	public static double getGrapeWeight() {
		return grapeWeight;
	}

	public static double getPriceWeight() {
		return priceWeight;
	}

	public static double getQualityWeight() {
		return qualityWeight;
	}

	public static double getRegionWeight() {
		return regionWeight;
	}

	public static double getSweetnessWeight() {
		return sweetnessWeight;
	}

	public static double getVdpWeight() {
		return vdpWeight;
	}

	public static double getWineryWeight() {
		return wineryWeight;
	}

	public static double getWineStyleWeight() {
		return wineStyleWeight;
	}

	public static double getYearWeight() {
		return yearWeight;
	}
}
