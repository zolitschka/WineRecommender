package rec.content;

import java.util.Collections;
import java.util.Vector;

import rec.Wine;
import rec.database.MySQLConnection;

public class SimilarityList {
	private static Vector<Wine> wineList = MySQLConnection.getWineContent();

	public SimilarityList() {
		new Similarity();
		// f�r jeden Wein (wine1) eine Liste aller Weine mit ihrer �hnlichkeit
		// zu wine1
		for (int i = 0; i < wineList.size(); i++) {
			Wine wine1 = wineList.elementAt(i);
			Vector<Wine> similarityList = getSimilarityList(wine1);
			wine1.setSimilarityList(similarityList);
		}
	}

	public Vector<Wine> getSimilarityList(Wine wine1) {
		Vector<Wine> similarityList = new Vector<Wine>();
		for (int j = 0; j < wineList.size(); j++) {
			Wine wine2 = wineList.elementAt(j);
			double sim = WeightedSimilarity.getSimilarity(wine1, wine2);
			Wine tmp = new Wine();
			tmp.copyWine(wine2);
			tmp.setSimilarity(sim);
			similarityList.add(tmp);
		}
		// similarityList der Gr��e nach sortieren
		Collections.sort(similarityList);
		return similarityList;
	}

	public static Vector<Wine> getWineList() {
		return wineList;
	}
}