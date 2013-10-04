package rec.collaborative;

import java.util.Comparator;

/*
 *  Vergelicht Kafistorien/Warenkörbe anhand ihrer Kosinus-Ähnlichtkeit 
 */

public class HistoryComparator implements Comparator<History> {

	@Override
	public int compare(History h1, History h2) {

		if (h1.getSimilairity() > h2.getSimilairity()) {
			return -1;
		}

		if (h1.getSimilairity() == h2.getSimilairity()) {
			return 0;
		} else
			return 1;

	}
}
