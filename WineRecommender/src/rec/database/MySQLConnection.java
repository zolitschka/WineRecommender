package rec.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.model.Preference;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;

import rec.User;
import rec.Wine;
import rec.collaborative.History;
import rec.content.SimilarityList;
import rec.database.DBUser;

/*
 * 
 * Die Klasse MySQLConnection ist die Schnittstelle zur Datenbank und
 * speichert die Daten in Datenstrukturen
 * 
 */

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

			// Datenbanktreiber fuer ODBC Schnittstellen laden.
			// Fuer verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur ODBC-Datenbank herstellen.
			// Es wird die JDBC-ODBC-Brueke verwendet.
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
		} catch (SQLException e) {
			System.out.println("Connect nicht moeglich");
			JOptionPane.showMessageDialog( null, "Datenbank nicht verf�gbar!");
		}
	}

	private static Connection getInstance() {
		if (conn == null)
			new MySQLConnection();
		return conn;
	}

	public static Vector<History> getWarenkoerbe() {
		conn = getInstance();
		Vector<History> HistoryVector = new Vector<History>();
		Vector<Wine> wineVector = SimilarityList.getWineList();

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

				// Ergebnissaetze durchfahren.
				while (result.next()) {
					int orderId = result.getInt("order_id");
					int productId = result.getInt("product_id");

					History tmpHis = searchOrder(HistoryVector, orderId);
					Wine tmpWine = searchWine(wineVector, productId);

					if (tmpHis == null) {
						History newHistory = new History(orderId);
						if (tmpWine != null) {
							newHistory.wine.add(tmpWine);
						}
						HistoryVector.add(newHistory);
					} else {
						Wine tmpwine = searchWine(wineVector, productId);
						if (tmpwine != null) {
							tmpHis.wine.add(tmpwine);
						}
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return HistoryVector;
	}

	public static DataModel getDatamodellFromDatabase() {
		FastByIDMap<PreferenceArray> userData = new FastByIDMap<PreferenceArray>(); // Mahout
																					// Datenmodell
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

				List<Preference> userPrefs = new ArrayList<Preference>();
				// Ergebnissaetze durchfahren
				while (result.next()) {
					long customerId = result.getLong("customer_id");
					long productId = result.getLong("entity_pk_value");
					float rating = result.getFloat("value");

					userPrefs.add(new GenericPreference(customerId, productId,
							rating));
				}
				// Duplikate entfernen, Mittelwert aus Mehrfachbewertungen des
				// selben Kunden fuer ein Produkt
				for (int i = 0; i < userPrefs.size(); i++) {

					int j = i + 1;
					int c = 1;
					float ratingTmp = userPrefs.get(i).getValue();
					while (j < userPrefs.size()
							&& userPrefs.get(i).getUserID() == userPrefs.get(j)
									.getUserID()
							&& userPrefs.get(i).getItemID() == userPrefs.get(j)
									.getItemID()) {
						j++;
						c++;
						ratingTmp = ratingTmp + userPrefs.get(j).getValue();
					}
					if (c > 1) {
						userPrefs.get(i).setValue(ratingTmp / c);
						for (int k = i + 1; k < j; k++) {
							userPrefs.remove(i + 1);
						}
					}
				}
				// Mahout Datenmodell fuellen
				List<Preference> helperArray = new ArrayList<Preference>();
				long userIDtmp = userPrefs.get(0).getUserID();
				for (int i = 0; i < userPrefs.size(); i++) {
					if (userIDtmp != userPrefs.get(i).getUserID()) {
//						System.out.println("Ratings for User: " + userIDtmp);
						for (int j = 0; j < helperArray.size(); j++) {
//							System.out.println("User: "
//									+ helperArray.get(j).getUserID()
//									+ " Item: "
//									+ helperArray.get(j).getItemID()
//									+ " Rating: "
//									+ helperArray.get(j).getValue());
						}
						userData.put(userIDtmp, new GenericUserPreferenceArray(
								helperArray));
						helperArray.clear();
					}
					userIDtmp = userPrefs.get(i).getUserID();
					helperArray.add(userPrefs.get(i));
				}
				userData.put(userIDtmp, new GenericUserPreferenceArray(
						helperArray)); // mit letztem user abschliessen

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new GenericDataModel(userData);
	}

	public static Vector<User> getUser() {
		Vector<User> userVector = new Vector<User>();
		Vector<Wine> wineVector = SimilarityList.getWineList();
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

				// Ergebnissaetze durchfahren.
				while (result.next()) {

					int userID = result.getInt("kunden.customer_id");
					int productID = result.getInt("items.product_id");

					User tmpUser = searchUser(userVector, userID);

					if (tmpUser == null) {
						User newUser = new User();
						newUser.setId(userID);
						Wine tmpWine = searchWine(wineVector, productID);
						if (tmpWine != null) {
							newUser.addProduct(tmpWine);
						}
						userVector.add(newUser);
					} else {
						Wine tmpWine = searchWine(wineVector, productID);
						if (tmpWine != null) {
							tmpUser.addProduct(tmpWine);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Duplikate eliminieren
		for (int i = 0; i < userVector.size(); i++) {
			User tmpUser = userVector.elementAt(i);
			Vector<Wine> tmpProducts = tmpUser.getProducts();
			for (int j = 0; j < tmpProducts.size(); j++) {
				Wine tmpWine = tmpProducts.elementAt(j);
				for (int k = j + 1; k < tmpProducts.size(); k++) {
					if (tmpWine == tmpProducts.elementAt(k)) {
						tmpProducts.remove(tmpProducts.elementAt(k));
						k -= 1;
					}
				}
			}
		}
		return userVector;
	}

	public static Vector<Wine> getWineContent() {
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
						+ "WHERE attribute_id = 140 OR attribute_id = 141 OR attribute_id = 142 OR attribute_id = 160 "
						+ "UNION SELECT entity_id, attribute_id, value "
						+ "FROM catalog_product_entity_int "
						+ "WHERE attribute_id = 135 OR attribute_id = 138 OR attribute_id = 166 OR attribute_id = 203) AS at ON pd.entity_id = at.entity_id "
						+ "ORDER BY pd.entity_id, at.attribute_id";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissaetze durchfahren.
				while (result.next()) {
					// Datenstruktur
					Wine tmp = new Wine();

					// Defaultwerte fuer switch Attribute
					int quality = -1;
					int region = -1;
					double alcohol = -1;
					double acid = -1;
					double sweetness = -1;
					int wineStyle = -1;
					int year = -1;
					int aroma[] = null;

					// Weinobjekt fuellen + zum wineVektor hinzufuegen
					int wineID = result.getInt("pd.entity_id");

					// ID
					Wine tmpWine = searchWine(wineVector, wineID);
					// Wein hinzufuegen, wenn noch nicht vorhanden
					if (tmpWine == null) {

						// Name
						String name = result.getString("name");
						// Preis
						double price = result.getDouble("price");
						// Weingut
						int winery = result.getInt("manufacturer");
						// VDP
						int vdp = result.getInt("vdp");
						// Geschmack
						int taste = result.getInt("taste");
						// Rebsorte/n
						String grapeString = result.getString("grape");
						String grapeStringArray[] = grapeString.split(",");
						int grape[] = new int[grapeStringArray.length];
						for (int i = 0; i < grapeStringArray.length; i++) {
							grape[i] = Integer.parseInt(grapeStringArray[i]);
						}
						// Jahr
						try {
							year = Integer.parseInt(name.split(" |/")[0]);
						} catch (NumberFormatException e) {
						}

						tmp.setId(wineID);
						tmp.setName(name);
						tmp.setPrice(price);
						tmp.setTaste(taste);
						tmp.setVdp(vdp);
						tmp.setWinery(winery);
						tmp.setGrape(grape);
						tmp.setYear(year);
						wineVector.add(tmp);
					}
					// zusaetzliche EAV-Attribute hinzufuegen
					switch (result.getInt("at.attribute_id")) {
					// Qualitaet
					case 135:
						quality = result.getInt("value");
						break;
					// Region
					case 138:
						region = result.getInt("value");
						break;
					// Alkohol
					case 140:
						alcohol = getDouble(result);
						break;
					// Saeure
					case 141:
						acid = getDouble(result);
						break;
					// Restzucker
					case 142:
						sweetness = getDouble(result);
						break;
					// Aroma
					case 160:
						String aromaString = result.getString("value");
						if (aromaString != null) {
							String aromaStringArray[] = aromaString.split(",");
							aroma = new int[aromaStringArray.length];
							for (int i = 0; i < aromaStringArray.length; i++) {
								aroma[i] = Integer
										.parseInt(aromaStringArray[i]);
							}
						}
						break;
					// Weinstil
					case 166:
						wineStyle = result.getInt("value");
						break;
					// Jahr
					case 203:
						// Erst sinnvoll, wenn Jahr wirklich als Attribut
						// eingetragen wird! Bisher Jahr fast immer nur im Namen
						// drin
						// year = result.getInt("value");
					default:
					}
					if (quality != -1 && quality != 0) {
						tmp.setQuality(quality);
					}
					if (region != -1) {
						tmpWine.setRegion(region);
					}
					if (alcohol != -1 && alcohol != 0) {
						tmpWine.setAlcohol(alcohol);
					}
					if (acid != -1 && acid != 0) {
						tmpWine.setAcid(acid);
					}
					if (sweetness != -1) {
						// falls nicht vorhanden mittig in Geschmack einordnen
						if (sweetness == 0) {
							switch (tmpWine.getTaste()) {
							// trocken
							case (15):
								sweetness = 6;
								break;
							// halbtrocken
							case (23):
								sweetness = 12;
								break;
							// feinherb
							case (119):
								sweetness = 20;
								break;
							// lieblich
							case (21):
								sweetness = 60;
								break;
							// edelsuess
							case (20):
								sweetness = 80;
								break;
							}
						}
						tmpWine.setSweetness(sweetness);
					}
					if (wineStyle != -1 && wineStyle != 0) {
						tmpWine.setWineStyle(wineStyle);
					}
					if (year != -1) {
						tmp.setYear(year);
					}
					if (aroma != null) {
						tmpWine.setAroma(aroma);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return wineVector;
	}

	// Notwendig wegen unterschiedlicher Darstellung (z.B.: "4.3", "4,3",
	// "4.3 %", etc)
	private static double getDouble(ResultSet result) throws SQLException {
		double returnDouble;
		String alcoholString = result.getString("value");
		try {
			returnDouble = Double.parseDouble(alcoholString);
		} catch (NumberFormatException e1) {
			try {
				String returnStringArray[] = result.getString("value").split(
						Pattern.quote(" "));
				returnDouble = Double.parseDouble(returnStringArray[0]);
			} catch (NumberFormatException e2) {
				String returnStringArray[] = result.getString("value").split(
						Pattern.quote(","));
				returnDouble = Double.parseDouble(returnStringArray[0] + "."
						+ returnStringArray[1]);
			}
		}
		return returnDouble;
	}

	// Suche nach Wein mit Hilfe der ID
	private static Wine searchWine(Vector<Wine> wineVector, int id) {
		Wine result = null;

		for (int i = 0; i < wineVector.size(); i++) {
			Wine tmp = wineVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}

	// Suche nach User mit Hilfe der ID
	private static User searchUser(Vector<User> userVector, int id) {
		User result = null;

		for (int i = 0; i < userVector.size(); i++) {
			User tmp = userVector.elementAt(i);
			if (tmp.getId() == id)
				result = tmp;
		}

		return result;
	}

	// Suche nach Order mit Hilfe ID
	private static History searchOrder(Vector<History> hisvec, int id) {
		History result = null;
		for (int i = 0; i < hisvec.size(); i++) {
			History tmp = hisvec.elementAt(i);
			if (tmp.getId() == id) {
				result = tmp;
			}
		}
		return result;
	}
}
