package rec.content;

import rec.Wine;

public class WeightedSimilarity {

	public static double getSimilarity(Wine wine1, Wine wine2) {
		Weighting weighting = new Weighting();
		double result;

		double simYear = Similarity.year(wine1.getYear(), wine2.getYear());
		double simWinery = Similarity.winery(wine1.getWinery(),
				wine2.getWinery());
		double simVdp = Similarity.vdp(wine1.getVdp(), wine2.getVdp());
		double simRegion = Similarity.region(wine1.getRegion(),
				wine2.getRegion());
		double simPrice = Similarity.price(wine1.getPrice(), wine2.getPrice());
		double simAlcohol = Similarity.alcohol(wine1.getAlcohol(),
				wine2.getAlcohol());
		double simSweetness = Similarity.sweetness(wine1.getSweetness(),
				wine2.getSweetness());
		double simAcid = Similarity.acid(wine1.getAcid(), wine2.getAcid());

		result = simYear * weighting.getYearWeight() + simWinery
				* weighting.getWineryWeight() + simVdp
				* weighting.getVdpWeight() + simRegion
				* weighting.getRegionWeight() + simPrice
				* weighting.getPriceWeight() + simAlcohol
				* weighting.getAlcoholWeight() + simSweetness
				* weighting.getSweetnessWeight() + simAcid
				* weighting.getAcidWeight();

		return result;
	}

	// private Weighting changeWeighting(Weighting weighting){
	//
	// }
}
