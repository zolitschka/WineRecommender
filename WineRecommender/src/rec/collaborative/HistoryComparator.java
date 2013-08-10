package rec.collaborative;

import java.util.Comparator;

public class HistoryComparator implements Comparator<History> {

	@Override
	public int compare(History h1, History h2) {
		if (h1.getSimilairity()>=h2.getSimilairity()){
			return -1; 
		}
		else{
			if (h1.getSimilairity()<h2.getSimilairity()){
				return 1; 
				}else return 0; 
			} 
		
		
		
	}
}
