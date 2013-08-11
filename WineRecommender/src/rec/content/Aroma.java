package rec.content;

import java.util.Vector;

public class Aroma {
	private final double similarity;
	private final Aroma parent;

	private static Vector<Aroma> childsVector = new Vector<Aroma>();

	public Aroma(Aroma parent, double similarity) {
		this.similarity = similarity;
		this.parent = parent;
	}
	

	public Vector<Aroma> getChilds() {
		return childsVector;
	}

	public static void setChilds(Aroma... childs) {
		for (Aroma child : childs) {
			childsVector.add(child);
		}
	}

	public double getSimilarity() {
		return similarity;
	}

	public Aroma getParent() {
		return parent;
	}

}
