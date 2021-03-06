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
 * Evaluationsklasse fuer SVD++
 * 
 */

public class SvdppRecEval{

	DataModel dataModel;
	
	public SvdppRecEval(){
		dataModel = rec.database.MySQLConnection
				.getDatamodellFromDatabase(); //Datenmodell initialisieren
	}
	
	/*
	 * Methode zur Evaluation der optimalen Anzahl an Iterationen
	 * @param maxiter maximale Anzahl an iterationen die geprueft werden sollen
	 * @param fac Anzahl an Faktoren, fest fuer jeden Testdurchlauf
	 * @param Anzahl an Wiederholungen fuer jeden Iterationswert
	 */
	public void evalIterations(int maxiter, int fac,  int wdh) {
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		evaluator.setMaxPreference(5);
		evaluator.setMinPreference(1);
		ArrayList<Double> ergebnisse = new ArrayList<Double>();
		try {
			double rmse = 0;
			for (int j = 1; j <= maxiter; j++) {
				for (int i = 0; i < wdh; i++) {
					rmse = rmse
							+ evaluator.evaluate(createRecBuilder(fac, j), null,
									dataModel, 0.7, 1.0);
				}
				ergebnisse.add(rmse / wdh); //Durchschnittlichen RMSE fuer aktuellen Iterationswert berechnen und Ergebnisliste hinzufuegen
				rmse = 0;
			}
			for(int i=0; i < ergebnisse.size(); i++){
				System.out.println("Iterationen: " + (i+1) + " RMSE: " + ergebnisse.get(i));
			}
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Methode zur evaluation der optimalen Anzahl an Iterationen
	 * @param maxfac maximale Anzahl an Faktoren die geprueft werden sollen
	 * @param iter Anzahl an Iterationen, fest fuer jeden Testdurchlauf
	 * @param Anzahl an Wiederholungen fuer jeden Faktorenwert
	 */
	public void evalFaktors(int maxfac, int iter, int wdh) {
		RMSRecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		evaluator.setMaxPreference(5);
		evaluator.setMinPreference(1);
		ArrayList<Double> ergebnisse = new ArrayList<Double>();
		try {
			double rmse = 0;
			for (int j = 1; j <= maxfac; j++) {
				for (int i = 0; i < wdh; i++) {
					rmse = rmse
							+ evaluator.evaluate(createRecBuilder(j, iter), null,
									dataModel, 0.7, 1.0);
				}
				ergebnisse.add(rmse / wdh); //Durchschnittlichen RMSE fuer aktuellen Dimsensionswert berechnen und Ergebnisliste hinzufuegen
				rmse = 0;
			}
			for(int i=0; i < ergebnisse.size(); i++){
				System.out.println("Faktoren: " + (i+1) + " RMSE: " + ergebnisse.get(i));
			}
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}

	private RecommenderBuilder createRecBuilder(final int fac,
			final int iter) { // Konstanten wegen Zugriff aus der anonymen
									// Klasse
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
