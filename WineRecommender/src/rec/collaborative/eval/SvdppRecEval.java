package rec.collaborative.eval;

import java.util.ArrayList;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;

/*
 * 
 * Evaluationsklasse für SVD++
 * 
 */

public class SvdppRecEval {
	final int WDH = 1000; // Anzahl der Wiederholungen
	final int MAXDIM = 60; // Maxinake Anzahl an Dimensionen die evaluiert
							// werden soll
	final int MAXITER = 60; // Maximlale Anzahl an Iterationen die evaluiert
							// werden soll

	DataModel dataModel = rec.database.MySQLConnection
			.getDatamodellFromDatabase(); //Datenmodell abrufen

	public void evalIterations() {
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		evaluator.setMaxPreference(5);
		evaluator.setMinPreference(1);
		ArrayList<String> ergebnisse = new ArrayList<String>();
		try {
			double rmse = 0;
			for (int j = 1; j <= MAXDIM; j++) {
				for (int i = 0; i < WDH; i++) {
					rmse = rmse
							+ evaluator.evaluate(createRecBuilder(j, 20), null,
									dataModel, 0.7, 1.0);
				}
				ergebnisse.add("" + rmse / WDH);
				rmse = 0;
			}
			for (String s : ergebnisse) {
				System.out.println(s);
			}
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}

	public void evalDimensions() {
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		evaluator.setMaxPreference(5);
		evaluator.setMinPreference(1);
		ArrayList<String> ergebnisse = new ArrayList<String>();
		try {
			double rmse = 0;
			for (int j = 1; j <= MAXITER; j++) {
				for (int i = 0; i < WDH; i++) {
					rmse = rmse
							+ evaluator.evaluate(createRecBuilder(20, j), null,
									dataModel, 0.7, 1.0);
				}
				ergebnisse.add("" + rmse / WDH);
				rmse = 0;
			}
			for (String s : ergebnisse) {
				System.out.println(s);
			}
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}

	public RecommenderBuilder createRecBuilder(final int factors,
			final int iterations) { // Konstanten wegen Zugriff aus der anonymen
									// Klasse
		return new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel dataModel)
					throws TasteException {
				return new SVDRecommender(dataModel, new SVDPlusPlusFactorizer(
						dataModel, factors, iterations));
			}
		};
	}

}
