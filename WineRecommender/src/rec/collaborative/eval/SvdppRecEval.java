package rec.collaborative.eval;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class SvdppRecEval {
	
	DataModelBuilder dataModBuilder = new DataModelBuilder() {

		@Override
		public DataModel buildDataModel(FastByIDMap<PreferenceArray> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

	};

	RecommenderBuilder recBuilder = new RecommenderBuilder() {
		
		@Override
		public Recommender buildRecommender(DataModel dataModel)
				throws TasteException {
			return new SVDRecommender(dataModel, new SVDPlusPlusFactorizer(
					dataModel, 20, 5));
		}
	};
	
	

}
