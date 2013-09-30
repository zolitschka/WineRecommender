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
	final int WDH = 1000; //Anzahl der Wiederholungen pro Iterationswert
	
	DataModel dataModel = rec.database.MySQLConnection.getDatamodellFromDatabase();
	
	public void eval(){
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		evaluator.setMaxPreference(5);
		evaluator.setMinPreference(1);
		try {
			double rmse = 0;
			for(int i=0; i< WDH; i++){
				rmse = rmse + evaluator.evaluate(createRecBuilder(20, 20), null, dataModel, 0.7, 1.0);
			}
			System.out.println(rmse / WDH);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RecommenderBuilder createRecBuilder(int factors, int iterations){
		//Konstanten für den Zugriff aus der anonymen Klasse
		final int fac = factors;
		final int iter = iterations;
		return new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel dataModel)
					throws TasteException {
				return new SVDRecommender(dataModel, new SVDPlusPlusFactorizer(
						dataModel, fac, iter));
			}
		};
	}

}
