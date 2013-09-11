package rec.collaborative;

import rec.Wine;
import java.util.Comparator;

public class WineComparator implements Comparator<Wine> {

	@Override
	public int compare(Wine w1, Wine w2) {
		if (w1.getWineScore()>w2.getWineScore()){
			return -1;
		}else {
			if (w1.getWineScore()==w2.getWineScore()){
				return 0; 
			}else return 1;
		}
	}

}
