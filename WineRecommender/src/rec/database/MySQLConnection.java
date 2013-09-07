package rec.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import rec.Wine;
import rec.database.DBUser;

public class MySQLConnection {

	private static Connection conn = null;

	// Hostname
	private static String dbHost = DBUser.dbHost;

	// Port -- Standard: 3306
	private static String dbPort = DBUser.dbPort;

	// Datenbankname
	private static String database = DBUser.database;

	// Datenbankuser
	private static String dbUser = DBUser.dbUser;

	// Datenbankpasswort
	private static String dbPassword = DBUser.dbPassword;

	private MySQLConnection() {
		try {

			// Datenbanktreiber für ODBC Schnittstellen laden.
			// Für verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur ODBC-Datenbank herstellen.
			// Es wird die JDBC-ODBC-Brücke verwendet.
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Connect nicht moeglich");
		}
	}

	private static Connection getInstance() {
		if (conn == null)
			new MySQLConnection();
		return conn;
	}

	public static void getWarenkoerbe() // TODO return typ anpassen
	{
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT order_id, product_id "
						+ "FROM sales_flat_order_item "
						+ "ORDER BY order_id, product_id";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					// TODO Datenstruktur ergänzen
					int order = result.getInt("order_id");
					int product = result.getInt("product_id");
					System.out.println("Cart: " + order + " contains product: "
							+ product); // Für Tests
				}
				// TODO Datenstruktur returnen
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getKaufverhalten() // TODO return typ anpassen
	{
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT kunden.customer_id, items.product_id "
						+ "FROM sales_flat_order_item AS items "
						+ "INNER JOIN sales_flat_order_grid AS kunden ON items.order_id = kunden.entity_id "
						+ "ORDER BY kunden.customer_id, items.product_id";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					// TODO Datenstruktur ergänzen
					int customer = result.getInt("kunden.customer_id");
					int product = result.getInt("items.product_id");
					System.out.println("Customer: " + customer
							+ " bought product: " + product); // Test
				}
				// TODO Datenstruktur returnen
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getRatings() // TODO return typ anpassen
	{
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT customer_id, entity_pk_value, value "
						+ "FROM rating_option_vote "
						+ "ORDER BY customer_id, entity_pk_value, value";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					// TODO Datenstruktur ergänzen
					int customer = result.getInt("customer_id");
					int product = result.getInt("entity_pk_value");
					int rating = result.getInt("value");
					System.out.println("Customer: " + customer
							+ " rated product: " + product + " with " + rating
							+ " stars"); // Test
				}
				// TODO Datenstruktur returnen
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Vector<Wine> getWineContent() // TODO return typ anpassen
	{
		Vector<Wine> wineVector = new Vector<Wine>();
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT pd.entity_id, name, price, manufacturer, manufacturer_value, vdp, taste, taste_value, grape, at.attribute_id, at.value "
						+ "FROM catalog_product_flat_1 AS pd "
						+ "INNER JOIN (SELECT entity_id, attribute_id, value "
						+ "FROM catalog_product_entity_varchar "
						+ "WHERE attribute_id = 140 OR attribute_id = 141 OR attribute_id = 142 "
						+ "UNION SELECT entity_id, attribute_id, value "
						+ "FROM catalog_product_entity_int "
						+ "WHERE attribute_id = 135 OR attribute_id = 138 OR attribute_id = 166 OR attribute_id = 203) AS at ON pd.entity_id = at.entity_id "
						+ "ORDER BY pd.entity_id, at.attribute_id";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					// TODO Datenstruktur ergänzen
					Wine tmp = new Wine();

					// Defaultwerte f�r switch Attribute
					int quality = -1;
					int region = -1;
					double alcohol = -1;
					double acid = -1;
					double sweetness = -1;
					int wineStyle = -1;
					int year = -1;

					int wineID = result.getInt("pd.entity_id");
					String name = result.getString("name");
					double price = result.getDouble("price");
					int winery = result.getInt("manufacturer");
					String wineryName = result.getString("manufacturer_value");
					int vdp = result.getInt("vdp");
					int taste = result.getInt("taste");
					String tasteName = result.getString("taste_value");
					// int grape = result.getInt("grape"); //funktioniert noch
					// nicht weil mehrere Werte in der Spalte vorkommen
					// können...

					switch (result.getInt("at.attribute_id")) {
					case 135:
						quality = result.getInt("value");
						break;
					case 138:
						region = result.getInt("value");
						break;
					case 140:
						// alcohol = result.getDouble("value"); // TODO
						// Sonderzeichen
						break;
					case 141:
						// acid = result.getDouble("value"); // TODO
						// Sonderzeichen
						break;
					case 142:
						// sweetness = result.getDouble("value");// TODO
						// Sonderzeichen
						break;
					case 166:
						wineStyle = result.getInt("value");
						break;
					case 203:
						year = result.getInt("value");
					default:
					}
					// Weinobjekt f�llen + zum wineVektor hinzuf�gen
					Wine tmpWine = search(wineVector, wineID);
					if (tmpWine == null) {
						tmp.setId(wineID);
						tmp.setName(name);

						tmp.setPrice(price);
						tmp.setQuality(quality);
						tmp.setTaste(taste);
						tmp.setVdp(vdp);
						tmp.setWinery(winery);
						// // tmp.addGrape(grape);
						wineVector.add(tmp);
					} else {
						// tmpWine.setRegion(region);
						// tmpWine.setAlcohol(alcohol);
						// tmpWine.setAcid(acid);
						// tmpWine.setSweetness(sweetness);
						// tmpWine.setWineStyle(wineStyle);
						// tmpWine.setYear(year);

						// tmp.addAroma(aroma); //TODO noch nicht in der
						// Datenbank
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(wineVector);
		return wineVector;
	}

	private static Wine search(Vector<Wine> wineVector, int id) {
		Wine result = null;

		for (int i = 0; i < wineVector.size(); i++) {
			Wine tmp = wineVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}

}