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
	DataModel model;
	SVDRecommender svdppRec;
	
	public SvdppRec() throws TasteException{
		model = rec.database.MySQLConnection.getDatamodellFromDatabase();
		svdppRec = new SVDRecommender(model,new SVDPlusPlusFactorizer(model, 20, 7));
	}
	
	public Vector<Wine> recommend(long userId) throws TasteException{
		Vector<Wine> weine = new Vector();
		List<RecommendedItem> recommendations = svdppRec.recommend(userId, 10);
		for (RecommendedItem recommendedItem : recommendations) {
			Wine weinTmp = new Wine();
			weinTmp.setId((int)recommendedItem.getItemID()); //TODO schöner machen
			weinTmp.setRating(recommendedItem.getValue());
			weinTmp.setName(SimilarityList.getWineWithID((int)recommendedItem.getItemID()).getName());
			weine.add(weinTmp);
//			System.out.println(recommendedItem);
		}
		return weine;
	}

}
