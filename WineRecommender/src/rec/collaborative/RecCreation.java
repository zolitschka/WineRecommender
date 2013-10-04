package rec.collaborative;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import rec.User;
import rec.Wine;
import rec.content.SimilarityList;
import rec.database.MySQLConnection;

public class RecCreation {

	final int TOP_K_WARENKOERBE = 3; // # �hnlichsten Kaufhistorien/Warenk�rben
	final int TOP_K_KAUFHISTORIEN = 5;

	Vector<History> orderHistories = new Vector<History>();
	Vector<History> buyhistories = new Vector<History>();
	History currentOrderHistory;
	History currentBuyHistory;
	Comparator<History> histComp = new HistoryComparator();
	Comparator<Wine> wineComp = new WineComparator();
	Vector<Wine> recWineList = new Vector<Wine>();

	public Vector<Wine> getRecWineList() {
		return recWineList;
	}

	public RecCreation(int userId) {

		InitBuyHistories();
		currentBuyHistory = getCurrentBuyHistorie(userId);

	}

	public void InitBuyHistories() {
		Vector<User> userList = MySQLConnection.getUser();
		Vector<History> orderHistory = MySQLConnection.getWarenkoerbe();

		orderHistories = orderHistory;

		for (int i = 0; i < orderHistories.size(); i++) {
			for (int k = 0; k < orderHistories.elementAt(i).wine.size(); k++) {
				orderHistories.elementAt(i).wine.elementAt(k)
						.setSimilarity(0.0);
			}
		}
		
		for (int i = 0; i < userList.size(); i++) {
			History h = new History(userList.elementAt(i).getId());
			h.wine = userList.elementAt(i).getProducts();
			buyhistories.add(h);

		}
		for (int i = 0; i < buyhistories.size(); i++) {
			for (int k = 0; k < buyhistories.elementAt(i).wine.size(); k++) {
				buyhistories.elementAt(i).wine.elementAt(k).setWineScore(0.0);
			}
		}
	}

	/*
	 * Gibt Matrix aus
	 */
	public void print(Vector<History> h) {
		for (int i = 0; i < h.size(); i++) {
			for (int j = 0; j < h.elementAt(i).wine.size(); j++) {
				 System.out.print(h.elementAt(i).wine.elementAt(j).getId() + " ");
			}
			
		}
	}

	/*
	 * Gibt �hnlichkeiten aus
	 */
	public void printSim(Vector<History> h) {
		for (int i = 0; i < h.size(); i++) {
			System.out.println(h.elementAt(i).getId() + " "
					+ h.elementAt(i).getSimilairity());

		}
		System.out.println();
	}

	public void printWine(Vector<Wine> w) {
		for (int i = 0; i < w.size(); i++) {
			System.out.println(w.elementAt(i).getId() + " "
					+ w.elementAt(i).getWineScore());
		}
	}

	/*
	 * Findet Kaufhistorie des aktuellen Nutzers
	 */
	public History getCurrentBuyHistorie(int userId) {
		History h = new History(userId);

		for (int i = 0; i < buyhistories.size(); i++) {
			if (buyhistories.elementAt(i).getId() == userId) {
				h = buyhistories.elementAt(i);
			}
		}
		return h;

	}

	/*
	 * Berechnet Cosinus-�hnlichkeit zwischen 2 Vektoren
	 */
	public double cosSimilairity(int a[]) {
		double sim = 0;
		int zaehler = 0;
		double nenner;
		double laenge1 = 0.0; // laenge von User Vektor (immer 1en)
		double laenge2 = 0.0;

		for (int i = 0; i < a.length; i++) {
			zaehler = zaehler + a[i];
		}
		laenge1 = Math.sqrt((double) a.length);
		laenge2 = Math.sqrt((double) zaehler);
		nenner = laenge1 * laenge2;
		sim = zaehler / nenner;

		return sim;
	}

	/*
	 * Erzeugt Empfehlungen anhand der Kaufhistorie
	 */
	public Vector<Wine> createRecBuyHistory() {
		int vektor[] = new int[currentBuyHistory.wine.size()];

		setSimilairity(currentBuyHistory, buyhistories, vektor);

		Collections.sort(buyhistories, histComp); // Kaufhistorien werden
													// absteigend ihrer
													// �hnlichkeit sortier
		
		return this.topKRec(buyhistories, currentBuyHistory,
				TOP_K_KAUFHISTORIEN);
	}

	/*
	 * Erzeugt Empfehlungen anhand der Warenk�rbe
	 */
	public Vector<Wine> createRecOrderHistory(Vector<Wine> w) {

		History coh = new History(-1);
		coh.wine = w;
		currentOrderHistory = coh;
		if (w.size() == 0) {
			return SimilarityList.getWineList();
		} else {
			int vektor[] = new int[coh.wine.size()];

			setSimilairity(currentOrderHistory, orderHistories, vektor);
			Collections.sort(orderHistories, histComp); // Warenk�rbe werden
														// absteigend ihrer
														// �hnlichkeit sortier

			return this.topKRec(orderHistories, currentOrderHistory,
					TOP_K_WARENKOERBE);
		}

	}

	public Vector<Wine> topKRec(Vector<History> histories,
			History currentHistory, int TOP_K) {
		Vector<History> topKhistories = new Vector<History>();
		for (int i = 0; i < TOP_K; i++) {
			topKhistories.add(histories.elementAt(i));
		}
		removeBoughtWine(currentHistory, topKhistories);

		System.out.println();

	

		for (int i = 0; i < topKhistories.size(); i++) {
			for (int j = 0; j < topKhistories.elementAt(i).wine.size(); j++) {
				topKhistories.elementAt(i).wine.elementAt(j).setWineScore(
						topKhistories.elementAt(i).wine.elementAt(j)
								.getWineScore()
								+ topKhistories.elementAt(i).getSimilairity());
			}
		}

		Vector<Wine> wine = new Vector<Wine>();
		for (int i = 0; i < topKhistories.size(); i++) {
			for (int j = 0; j < topKhistories.elementAt(i).wine.size(); j++) {
				if (!wine
						.contains(topKhistories.elementAt(i).wine.elementAt(j))) {
					wine.add(topKhistories.elementAt(i).wine.elementAt(j));
				}
			}
		}
		Collections.sort(wine, wineComp);
		// jsdfiojfds
		// System.out.println();
		// this.printWine(wine);
		return wine;
	}

	private void removeBoughtWine(History currentHistory,
			Vector<History> topKhistories) {

		for (int i = 0; i < currentHistory.wine.size(); i++) {
			for (int j = 0; j < topKhistories.size(); j++) {

				topKhistories.elementAt(j).wine
						.removeElement(currentHistory.wine.elementAt(i));

			}

		}
	}

	/*
	 * Berechnet und setzt �hnlichkeiten
	 */
	private void setSimilairity(History currentHistory,
			Vector<History> histories, int[] vektor) {

		for (int i = 0; i < histories.size(); i++) {
			if (currentHistory.getId() != histories.elementAt(i).getId()) {
				for (int k = 0; k < currentHistory.wine.size(); k++) {

					if (histories.elementAt(i).wine
							.contains(currentHistory.wine.elementAt(k))) {
						vektor[k] = 1;
					} else
						vektor[k] = 0;

				}
				if (!this.isEmpty(vektor)) {

					histories.elementAt(i).setSimilairity(
							this.cosSimilairity(vektor));
				} else {
					histories.elementAt(i).setSimilairity(0.0);
				}
			}
		}
	}

	/*
	 * Testet ob ein Vektor leer ist, in diesem Fall nur aus 0en besteht
	 */

	public boolean isEmpty(int[] vektor) {
		boolean empty = true;

		for (int i = 0; i < vektor.length; i++) {
			if (vektor[i] == 1) {
				empty = false;
			}
		}
		return empty;

	}

}
