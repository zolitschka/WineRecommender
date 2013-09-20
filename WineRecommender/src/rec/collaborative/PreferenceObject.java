package rec.collaborative;

public class PreferenceObject {
	private static double weight = 1;
	private static String name;

	public PreferenceObject(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public static String getName() {
		return name;
	}

}
