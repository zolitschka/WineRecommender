package rec.collaborative;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class RecCreation {

	final int TOP_K=2;  // # ähnlichsten Kaufhistorien/Warenkörben
	
	Vector<History> orderHistory = new Vector<History>();
	Vector<History> buyhistories = new Vector<History>();
	History currentOrderHistory;
	History currentBuyHistory;
	Comparator<History> histComp = new HistoryComparator(); 

	public RecCreation(Vector<History> or,History h, int userId) {
		orderHistory=or; 
		//buyhistories=b; 
		currentOrderHistory = h;
		//currentBuyHistory = getCurrentBuyHistorie(userId);
	}
	
	/*
	 * Gibt Matrix aus 
	 */
	public void print (Vector<History>h){
		for (int i=0;i<h.size();i++){
			System.out.print(h.elementAt(i).getId()+" ");
			for (int j=0;j<h.elementAt(i).wine.size();j++){
				System.out.print(h.elementAt(i).wine.elementAt(j)+" ");
			}
			System.out.println();
		}
	}
	/*
	 * Gibt Ähnlichkeiten aus
	 */
	public void printSim (Vector<History>h){
		for (int i=0;i<h.size();i++){
			System.out.println(h.elementAt(i).getSimilairity());
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
 *  Berechnet Cosinus-Ähnlichkeit zwischen 2 Vektoren
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
		Collections.sort(buyhistories,histComp); //Kaufhistorien werden absteigend ihrer Ähnlichkeit sortier
		
	}
	
	public void createRecOrderHistory (){
		int vektor []= new int [currentOrderHistory.wine.size()]; 
		
		setSimilairity(currentOrderHistory, orderHistory, vektor);
		Collections.sort(orderHistory, histComp);  //Warenkörbe werden absteigend ihrer Ähnlichkeit sortier
	}
	/*
	 * Berechnet und setzt Ähnlichkeiten
	 */
	private void setSimilairity(History currentHistory, Vector <History> histories, int[] vektor) {
		
		for (int i = 0; i < histories.size(); i++) {
			if (currentHistory.getId() != histories.elementAt(i).getId()) {
				for (int k = 0; k < currentHistory.wine.size(); k++) {
					if (histories.elementAt(i).wine
							.contains(currentHistory.wine.elementAt(k))) {
						vektor[k] = 1;
					} else
						vektor[k] = 0;

				}
				if (!this.isEmpty(vektor)){
				histories.elementAt(i).setSimilairity(
						this.cosSimilairity(vektor));}
				else {
					histories.elementAt(i).setSimilairity(0.0); 
				}
			}
		}
	}
	
	/*
	 *  Testet ob ein Vektor leer ist, in diesem Fall nur aus 0en besteht
	 */
	
	public boolean isEmpty (int [] vektor){
		boolean empty=true; 
		
		for (int i=0;i<vektor.length;i++){
			if (vektor[i]==1){
				empty=false; 
			}
		}
		return empty; 
		
	}

}
