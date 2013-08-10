package rec.content;

public class content_testklasse {

	public static void main(String[] args) {
		wine wein1 = new wine();
		wine wein2 = new wine();

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
		wein1.setYear(2010);

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

		wein1.print();
		wein2.print();

	}

}
