package rec.content;

import rec.Wine;

public class content_testklasse {

	public static void main(String[] args) {
		Wine wein1 = new Wine();
		Wine wein2 = new Wine();

		wein1.setName("2011 Riesling WILLI HAAG QbA lieblich");
		wein1.setId(1);
		wein1.setAcid(8.8);
		wein1.setAlcohol(8.0);
		wein1.setPrice(6.0);
		wein1.setQuality(6);
		wein1.setRegion(13);
		wein1.setSweetness(66.0);
		wein1.setTaste(21);
		wein1.setWinery(7);
		wein1.setWineStyle(360);
		wein1.addGrape(148);
		wein1.setVdp(1);
		wein1.setYear(2011);

		wein2.setName("2010 Riesling Brauneberger Juffer-Sonnenuhr Auslese");
		wein2.setId(2);
		wein2.setAcid(11.4);
		wein2.setAlcohol(8.0);
		wein2.setPrice(17.5);
		wein2.setQuality(14);
		wein2.setRegion(13);
		wein2.setSweetness(132.0);
		wein2.setTaste(21);
		wein2.setWinery(7);
		wein2.setWineStyle(357);
		wein2.addGrape(148);
		wein2.setVdp(1);
		wein2.setYear(2010);

		System.out.println("Region: "
				+ Similarity.region(wein1.getRegion(), wein2.getRegion()));
		System.out.println("Jahr: "
				+ Similarity.year(wein1.getYear(), wein2.getYear()));
		System.out.println("VDP: "
				+ Similarity.vdp(wein1.getVdp(), wein2.getVdp()));
		System.out.println("Weingut: "
				+ Similarity.winery(wein1.getWinery(), wein2.getWinery()));
		System.out.println("Alkohol: "
				+ Similarity.alcohol(wein1.getAlcohol(), wein2.getAlcohol()));
		System.out.println("Restzucker: "
				+ Similarity.sweetness(wein1.getSweetness(),
						wein2.getSweetness()));
		System.out.println("Säure: "
				+ Similarity.acid(wein1.getAcid(), wein2.getAcid()));
		System.out.println("Preis: "
				+ Similarity.price(wein1.getPrice(), wein2.getPrice()));

		System.out.println();
		System.out.println("Gesamt: "
				+ WeightedSimilarity.getSimilarity(wein1, wein2));

	}

}
