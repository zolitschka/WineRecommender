package rec;

import java.util.Vector;

import rec.content.Weighting;

public class User {
	private int userID;
	private Vector<Wine> products = new Vector<Wine>();
	private Weighting weighting = new Weighting();

	public User() {

	}

	public int getId() {
		return userID;
	}

	public void setId(int id) {
		this.userID = id;
	}

	public Vector<Wine> getProducts() {
		return products;
	}

	public void addProduct(Wine product) {
		this.products.add(product);
	}

	public String toString() {
		return "User " + userID;
	}
}
