package rec.collaborative;

import java.util.Vector;

public class RecCreation {

	Vector<History> orders = new Vector<History>();
	Vector<History> buyhistories = new Vector<History>();
	History currentOrder;
	History currentBuyHistory;

	public RecCreation (int userId, History o){
		
		currentOrder=o; 
		currentBuyHistory=getCurrentBuyHistorie(userId);		
	}
	
	
	
	public History getCurrentBuyHistorie(int userId) {
		History h = new History();

		for (int i = 0; i < buyhistories.size(); i++) {
			if (buyhistories.elementAt(i).getId() == userId) {
				h = buyhistories.elementAt(i);
			} else {
				return null;
			}
		}
		return h;

	}
	
	public double cosSimilairity ( int a []){
		double sim=0; 
		int zaehler=0; 
		double nenner; 
		double laenge1 =0.0; // laenge von User Vektor  (immer 1en) 
		double laenge2 =0.0; 
		
		
		for (int i=0;i<a.length;i++){
			zaehler=zaehler+a[i];
		}
		laenge1=Math.sqrt((double)a.length);
		laenge2=Math.sqrt((double)zaehler);
		nenner=laenge1*laenge2; 
		sim=zaehler/nenner; 
		
		return sim ; 
	}
	
	
	public void createRecBuyHistory (){
		int vektor []= new int [currentBuyHistory.wine.size()];
		
		for (int i=0; i<buyhistories.size();i++){
			if (currentBuyHistory.getId()!=buyhistories.elementAt(i).getId()){
				for (int k=0; k<currentBuyHistory.wine.size();k++){
						if (buyhistories.elementAt(i).wine.contains(currentBuyHistory.wine.elementAt(k))){
							vektor[k]=1;
						}else vektor [k]=0;
					
					
				}
				buyhistories.elementAt(i).setSimilairity(this.cosSimilairity(vektor));
			}
		}
	}
	
	

}
