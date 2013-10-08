package rec.content;

import java.util.Collections;
import java.util.Vector;

import rec.User;
import rec.Wine;
import rec.database.MySQLConnection;

/*
 * 
 * Die Klasse SimilarityList berechnet f�r alle Weine �hnlichkeitslisten vor
 * 
 */

public class SimilarityList {
	private static Vector<Wine> wineList = MySQLConnection.getWineContent();

	// f�r jeden Wein wird eine Aenlichkeitsliste berechnet
	public SimilarityList(User user) {
		new Preference(user);
		new Similarity();
		for (int i = 0; i < wineList.size(); i++) {
			Wine wine1 = wineList.elementAt(i);
			Vector<Wine> similarityList = getSimilarityList(wine1);
			wine1.setSimilarityList(similarityList);
		}
	}

	// f�r gegebenen Wein wird eine Aenlichkeitsliste berechnet
	public static Vector<Wine> getSimilarityList(Wine wine1) {
		Vector<Wine> similarityList = new Vector<Wine>();
		for (int j = 0; j < wineList.size(); j++) {
			Wine wine2 = wineList.elementAt(j);
			double sim = WeightedSimilarity.getSimilarity(wine1, wine2);
			Wine tmp = new Wine();
			tmp.copyWine(wine2);
			tmp.setSimilarity(sim);
			similarityList.add(tmp);
		}
		Collections.sort(similarityList);
		return similarityList;
	}

	public static Vector<Wine> getWineList() {
		return wineList;
	}

	public static Wine getWineWithID(int id) {
		Wine result = null;

		for (int i = 0; i < wineList.size(); i++) {
			Wine tmp = wineList.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}
}
