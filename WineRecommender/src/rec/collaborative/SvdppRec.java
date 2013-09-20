package rec.collaborative;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Vector;
import java.io.IOException;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import rec.Wine;
import rec.content.SimilarityList;

public class SvdppRec {
	final int REC_COUNT=10; 
	DataModel model;
	SVDRecommender svdppRec;
	
	public SvdppRec() {

		model = rec.database.MySQLConnection.getDatamodellFromDatabase();
		try {
			svdppRec = new SVDRecommender(model,new SVDPlusPlusFactorizer(model, 20, 0.0001,0.002,0.0001, 5, 0.99));
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector<Wine> recommend(long userId){
		Vector<Wine> weine = new Vector();
		List<RecommendedItem> recommendations;
		try {
			recommendations = svdppRec.recommend(userId, REC_COUNT);
			for (RecommendedItem recommendedItem : recommendations) {
				Wine weinTmp = new Wine();
				weinTmp.setId((int)recommendedItem.getItemID()); //TODO schöner machen
				weinTmp.setRating(recommendedItem.getValue());
				weinTmp.setName(SimilarityList.getWineWithID((int)recommendedItem.getItemID()).getName());
				weine.add(weinTmp);
				//System.out.println(recommendedItem);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("Keine Bewertungen für User: " + userId + " vorhanden.");
		} 

		return weine;
	}

}
