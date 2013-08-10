package rec.collaborative;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class RecCreation {

	Vector<History> orders = new Vector<History>();
	Vector<History> buyhistories = new Vector<History>();
	History currentOrder;
	History currentBuyHistory;
	Comparator<History> histComp = new HistoryComparator(); 

	public RecCreation(Vector<History> b,int userId) {
		//orders=or; 
		buyhistories=b; 
		//currentOrder = o;
		currentBuyHistory = getCurrentBuyHistorie(userId);
	}
	
	public void print (Vector<History>h){
		for (int i=0;i<h.size();i++){
			System.out.print(h.elementAt(i).getId()+" ");
			for (int j=0;j<h.elementAt(i).wine.size();j++){
				System.out.print(h.elementAt(i).wine.elementAt(j)+" ");
			}
			System.out.println();
		}
	}
	public void printSim (Vector<History>h){
		for (int i=0;i<h.size();i++){
			System.out.println(h.elementAt(i).getSimilairity());
		}
	}
	
	public History getCurrentBuyHistorie(int userId) {
		History h = new History(userId);

		for (int i = 0; i < buyhistories.size(); i++) {
			if (buyhistories.elementAt(i).getId() == userId) {
				h = buyhistories.elementAt(i);
			} 
		}
		return h;

	}

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

	public void createRecBuyHistory() {
		int vektor[] = new int[currentBuyHistory.wine.size()];

		setSimilairity(vektor);
		Collections.sort(buyhistories,histComp);
		
	}

	private void setSimilairity(int[] vektor) {
		
		for (int i = 0; i < buyhistories.size(); i++) {
			if (currentBuyHistory.getId() != buyhistories.elementAt(i).getId()) {
				for (int k = 0; k < currentBuyHistory.wine.size(); k++) {
					if (buyhistories.elementAt(i).wine
							.contains(currentBuyHistory.wine.elementAt(k))) {
						vektor[k] = 1;
					} else
						vektor[k] = 0;

				}
				if (!this.isEmpty(vektor)){
				buyhistories.elementAt(i).setSimilairity(
						this.cosSimilairity(vektor));}
				else {
					buyhistories.elementAt(i).setSimilairity(0.0); 
				}
			}
		}
	}
	
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
