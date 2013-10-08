package rec.collaborative;

import java.util.List;
import java.util.Vector;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import rec.Wine;
import rec.content.SimilarityList;

/*
 * 
 * HIER FEHLT EINE KLASSENBESCHREIBUNG!!!
 * 
 */

public class SvdppRec {
	DataModel model;
	SVDRecommender svdppRec;

	public SvdppRec() {

		model = rec.database.MySQLConnection.getDatamodellFromDatabase();
		try {
			svdppRec = new SVDRecommender(model, new SVDPlusPlusFactorizer(model, 20, 7));
		} catch (TasteException e) {
			System.out.println("Erstellen des Recommenders fehlgeschlagen.");
			e.printStackTrace();
		}
	}

	public Vector<Wine> recommend(long userId, int rec_count) {
		Vector<Wine> weine = new Vector<Wine>();
		List<RecommendedItem> recommendations;
		float tmpRating = 0.0f;
		try {
			recommendations = svdppRec.recommend(userId, rec_count);
			for (RecommendedItem recommendedItem : recommendations) {
				Wine weinTmp = new Wine();
				weinTmp.setId((int) recommendedItem.getItemID());
				// Ratinglimit
				tmpRating = recommendedItem.getValue();
				if (tmpRating > 5.0) {
					tmpRating = 5.0f;
				} else {
					if (tmpRating < 1) {
						tmpRating = 1.0f;
					}

				}
				weinTmp.setRating(tmpRating);
				weinTmp.setName(SimilarityList.getWineWithID(
						(int) recommendedItem.getItemID()).getName());
				weine.add(weinTmp);
				// System.out.println(recommendedItem);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Keine Bewertungen fuer User: " + userId
					+ " vorhanden.");
		}
		return weine;
	}

}
