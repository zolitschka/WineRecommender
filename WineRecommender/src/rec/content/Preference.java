package rec.content;

import java.util.Vector;

import rec.User;
import rec.Wine;
import rec.database.GetBuyHistory;

//Präferenzprofil eines Users
public class Preference {
	private static Vector<User> userList = GetBuyHistory.getUserList();
	private Vector<Wine> history;

	private static test acidPreference = new test(
			"acid");
	private static test alcoholPreference = new test(
			"alcohol");
	private static test aromaPreference = new test(
			"aroma");
	private static test grapePreference = new test(
			"grape");
	private static test pricePreference = new test(
			"price");
	private static test qualityPreference = new test(
			"quality");
	private static test regionPreference = new test(
			"region");
	private static test sweetnessPreference = new test(
			"sweetness");
	private static test vdpPreference = new test("vdp");
	private static test wineryPreference = new test(
			"winery");
	private static test wineStylePreference = new test(
			"wineStyle");
	private static test yearPreference = new test(
			"year");

	private static Vector<test> preferenceProfil = new Vector<test>();

	public Preference(User user) {
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
		createProfil(user);
	}

	public void createProfil(User user) {

		User currentUser = search(user.getId());
		history = currentUser.getProducts();
		Wine averageWine = ContentBuyHistory.getAverageWine(history);

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
			double weight = preferenceProfil.elementAt(i).getWeight()
					+ difference;
			preferenceProfil.elementAt(i).setWeight(weight);
			for (int j = 0; j < preferenceProfil.size(); j++) {
				if (i != j) {
					double currentWeight = preferenceProfil.elementAt(j)
							.getWeight();
					preferenceProfil.elementAt(j).setWeight(
							currentWeight + (difference / 11));
				}
			}
		}

	}

	private void modulate(double sim) {

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

	public static double getYearWeight() {
		return preferenceProfil.elementAt(11).getWeight();
	}

	public static double getWineryWeight() {
		return preferenceProfil.elementAt(9).getWeight();
	}

	public static double getVdpWeight() {
		return preferenceProfil.elementAt(8).getWeight();
	}

	public static double getRegionWeight() {
		return preferenceProfil.elementAt(6).getWeight();
	}

	public static double getPriceWeight() {
		return preferenceProfil.elementAt(4).getWeight();
	}

	public static double getGrapeWeight() {
		return preferenceProfil.elementAt(3).getWeight();
	}

	public static double getSweetnessWeight() {
		return preferenceProfil.elementAt(7).getWeight();
	}

	public static double getAcidWeight() {
		return preferenceProfil.elementAt(0).getWeight();
	}

	public static double getAlcoholWeight() {
		return preferenceProfil.elementAt(1).getWeight();
	}

	public static double getWineStyleWeight() {
		return preferenceProfil.elementAt(10).getWeight();
	}

	public static double getQualityWeight() {
		return preferenceProfil.elementAt(5).getWeight();
	}

	public static double getAromaWeight() {
		return preferenceProfil.elementAt(2).getWeight();
	}
}
