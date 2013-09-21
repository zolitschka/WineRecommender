package rec.content;

public class StringDoubleObject implements Comparable<StringDoubleObject> {
	private String str;
	private double doub;

	public StringDoubleObject(String str, double doub) {
		this.str = str;
		this.doub = doub;
	}

	@Override
	public int compareTo(StringDoubleObject o) {
		if (doub > o.getDouble()) {
			return 1;
		} else {
			return -1;
		}
	}

	public double getDouble() {
		return doub;
	}

	public String getString() {
		return str;
	}

	public String toString() {
		return str + ": " + doub + "\n";
	}

}
