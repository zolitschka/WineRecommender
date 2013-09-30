package rec.collaborative.eval;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class SvdppRecEval {

	RecommenderBuilder recBuilder = new RecommenderBuilder() {
		@Override
		public Recommender buildRecommender(DataModel dataModel)
				throws TasteException {
			return new SVDRecommender(dataModel, new SVDPlusPlusFactorizer(
					dataModel, 20, 40));
		}
	};
	
	DataModel dataModel = rec.database.MySQLConnection.getDatamodellFromDatabase();
	
	public void eval(){
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		evaluator.setMaxPreference(5);
		evaluator.setMinPreference(1);
		try {
			System.out.println(evaluator.evaluate(recBuilder, null, dataModel, 0.7, 1.0));
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
