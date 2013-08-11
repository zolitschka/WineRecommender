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
		wein1.setQuality(450);
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
		wein2.setAcid(8.0);
		wein2.setAlcohol(8.0);
		wein2.setPrice(14.5);
		wein2.setQuality(115);
		wein2.setRegion(13);
		wein2.setSweetness(75.0);
		wein2.setTaste(21);
		wein2.setWinery(7);
		wein2.setWineStyle(359);
		wein2.addGrape(148);
		wein2.setVdp(1);
		wein2.setYear(2010);

		new Similarity();

		System.out.println("Region: "
				+ Similarity.binary(wein1.getRegion(), wein2.getRegion()));
		System.out.println("Jahr: "
				+ Similarity.binary(wein1.getYear(), wein2.getYear()));
		System.out.println("VDP: "
				+ Similarity.binary(wein1.getVdp(), wein2.getVdp()));
		System.out.println("Weingut: "
				+ Similarity.binary(wein1.getWinery(), wein2.getWinery()));
		System.out.println("Alkohol: "
				+ Similarity.alcohol(wein1.getAlcohol(), wein2.getAlcohol()));
		System.out.println("Restzucker: "
				+ Similarity.sweetness(wein1.getSweetness(),
						wein2.getSweetness()));
		System.out.println("Säure: "
				+ Similarity.acid(wein1.getAcid(), wein2.getAcid()));
		System.out.println("Preis: "
				+ Similarity.price(wein1.getPrice(), wein2.getPrice()));
		System.out.println("Weinstil: "
				+ Similarity.wineStyle(wein1.getWineStyle(),
						wein2.getWineStyle()));
		System.out.println("Qualität: "
				+ Similarity.quality(wein1.getQuality(), wein2.getQuality()));
		System.out.println("Rebsorte/n: "
				+ Similarity.grape(wein1.getGrape(), wein2.getGrape()));

		System.out.println();
		System.out.println("Gesamt: "
				+ WeightedSimilarity.getSimilarity(wein1, wein2));

		System.out.println();

	}

}
