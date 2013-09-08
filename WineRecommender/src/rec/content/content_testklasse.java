package rec.content;

import java.util.Vector;

import rec.Wine;

public class content_testklasse {

	public static void main(String[] args) {
		final Vector<Wine> wineList = SimilarityList.getWineList();
		new SimilarityList();
		Wine wine1 = wineList.elementAt(228);
		Wine wine2 = wineList.elementAt(228);
		System.out.println(wine1.getId());

		System.out.println("Region: "
				+ Similarity.binary(wine1.getRegion(), wine2.getRegion()));
		System.out.println("Jahr: "
				+ Similarity.binary(wine1.getYear(), wine2.getYear()));
		System.out.println("VDP: "
				+ Similarity.binary(wine1.getVdp(), wine2.getVdp()));
		System.out.println("Weingut: "
				+ Similarity.binary(wine1.getWinery(), wine2.getWinery()));
		System.out.println("Alkohol: "
				+ Similarity.alcohol(wine1.getAlcohol(), wine2.getAlcohol()));
		System.out.println("Restzucker: "
				+ Similarity.sweetness(wine1.getSweetness(),
						wine2.getSweetness()));
		System.out.println("Säure: "
				+ Similarity.acid(wine1.getAcid(), wine2.getAcid()));
		System.out.println("Preis: "
				+ Similarity.price(wine1.getPrice(), wine2.getPrice()));
		System.out.println("Weinstil: "
				+ Similarity.wineStyle(wine1.getWineStyle(),
						wine2.getWineStyle()));
		System.out.println("Qualität: "
				+ Similarity.quality(wine1.getQuality(), wine2.getQuality()));
		System.out.println("Rebsorte/n: "
				+ Similarity.grape(wine1.getGrape(), wine2.getGrape()));

		System.out.println();
		System.out.println("Gesamt: "
				+ WeightedSimilarity.getSimilarity(wine1, wine2));
	}

}
