package rec.collaborative;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import rec.User;
import rec.Wine;
import rec.database.GetBuyHistory;

public class RecCreation {

	final int TOP_K = 3; // # ähnlichsten Kaufhistorien/Warenkörben

	Vector<History> orderHistories = new Vector<History>();
	Vector<History> buyhistories = new Vector<History>();
	History currentOrderHistory;
	History currentBuyHistory;
	Comparator<History> histComp = new HistoryComparator();
	Comparator<Wine> wineComp = new WineComparator();

	public RecCreation() {
		//orderHistories = or;
		// buyhistories=b;
		//currentOrderHistory = h;
		// currentBuyHistory = getCurrentBuyHistorie(userId);
		InitBuyHistories();
		this.print(buyhistories);
	
	}
	public void InitBuyHistories () {
		Vector<User> userList = GetBuyHistory.getUserList(); 
		for (int i = 0; i < userList.size(); i++) {
			History h = new History (userList.elementAt(i).getId()); 
			h.wine=userList.elementAt(i).getProducts(); 
			buyhistories.add(h);
			
		}
		
	}

	/*
	 * Gibt Matrix aus
	 */
	public void print(Vector<History> h) {
		for (int i = 0; i < h.size(); i++) {
			System.out.print(h.elementAt(i).getId() + " ");
			for (int j = 0; j < h.elementAt(i).wine.size(); j++) {
				System.out.print(h.elementAt(i).wine.elementAt(j).getId()+ " ");
			}
			System.out.println();
		}
	}

	/*
	 * Gibt Ähnlichkeiten aus
	 */
	public void printSim(Vector<History> h) {
		for (int i = 0; i < h.size(); i++) {
			System.out.println(h.elementAt(i).getSimilairity());
		}
	}

	
	public void printWine (Vector<Wine> w){
		for (int i = 0; i < w.size(); i++) {
			System.out.println(w.elementAt(i).getId()+" "+w.elementAt(i).getWineScore());
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
	 * Berechnet Cosinus-Ähnlichkeit zwischen 2 Vektoren
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
	public void createRecBuyHistory() {
		int vektor[] = new int[currentBuyHistory.wine.size()];

		setSimilairity(currentBuyHistory, buyhistories, vektor);
		Collections.sort(buyhistories, histComp); // Kaufhistorien werden
													// absteigend ihrer
													// Ähnlichkeit sortier

	}

	/*
	 * Erzeugt Empfehlungen anhand der Warenkörbe
	 */
	public void createRecOrderHistory() {
		int vektor[] = new int[currentOrderHistory.wine.size()];

		setSimilairity(currentOrderHistory, orderHistories, vektor);
		Collections.sort(orderHistories, histComp); // Warenkörbe werden
													// absteigend ihrer
													// Ähnlichkeit sortier

		this.topKRec(orderHistories, currentOrderHistory);
	}

	public void topKRec(Vector<History> histories, History currentHistory) {
		Vector<History> topKhistories = new Vector<History>();
		for (int i = 0; i < TOP_K; i++) {
			topKhistories.add(histories.elementAt(i));
		}
		removeBoughtWine(currentHistory, topKhistories);

		System.out.println();

		this.print(topKhistories);
		
		for (int i=0;i<topKhistories.size();i++){
			for (int j=0; j<topKhistories.elementAt(i).wine.size();j++){
				topKhistories.elementAt(i).wine.elementAt(j).
				setWineScore(topKhistories.elementAt(i).wine.elementAt(j).getWineScore()+topKhistories.elementAt(i).getSimilairity());
			}
		}
		
		Vector <Wine> wine = new Vector<Wine>();
		for (int i=0;i<topKhistories.size();i++){
			for (int j=0; j<topKhistories.elementAt(i).wine.size();j++){
				if (!wine.contains(topKhistories.elementAt(i).wine.elementAt(j))){
					wine.add(topKhistories.elementAt(i).wine.elementAt(j));
				}
			}
		}
		Collections.sort(wine, wineComp);
		System.out.println();
		this.printWine(wine);
	}

	private void removeBoughtWine(History currentHistory,
			Vector<History> topKhistories) {
		for (int i = 0; i < currentHistory.wine.size(); i++) {
			for (int j = 0; j < topKhistories.size(); j++) {
				if (topKhistories.elementAt(j).wine
						.contains(currentHistory.wine.elementAt(i))) {
					topKhistories.elementAt(j).wine
							.removeElement(currentHistory.wine.elementAt(i));
				}
			}

		}
	}

	/*
	 * Berechnet und setzt Ähnlichkeiten
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
