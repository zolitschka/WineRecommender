package rec.collaborative;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;


public class SvdppRec {
	public static void recommend() throws TasteException{
		DataModel model = rec.database.MySQLConnection.getDatamodellFromDatabase();
		SVDRecommender svdppRec = new SVDRecommender(model,new SVDPlusPlusFactorizer(model, 20, 7));
		List<RecommendedItem> recommendations = svdppRec.recommend(4, 10);
		for (RecommendedItem recommendedItem : recommendations) {
			System.out.println(recommendedItem);
		}package rec.collaborative;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;


public class SvdppRec {
	public static void recommend() throws TasteException{
		DataModel model = rec.database.MySQLConnection.getDatamodellFromDatabase();
		SVDRecommender svdppRec = new SVDRecommender(model,new SVDPlusPlusFactorizer(model, 20, 7));
		List<RecommendedItem> recommendations = svdppRec.recommend(4, 10);
		for (RecommendedItem recommendedItem : recommendations) {
			System.out.println(recommendedItem);
		}
	}

}
		
	}

}
