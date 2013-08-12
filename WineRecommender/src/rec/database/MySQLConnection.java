package rec.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MySQLConnection {
 
  private static Connection conn = null;
 
  // Hostname
  private static String dbHost = "127.0.0.1";
 
  // Port -- Standard: 3306
  private static String dbPort = "3306";
 
  // Datenbankname
  private static String database = "test";
 
  // Datenbankuser
  private static String dbUser = "phpmyadmin";
 
  // Datenbankpasswort
  private static String dbPassword = "test12345";
 
  private MySQLConnection() {
    try {
 
      // Datenbanktreiber für ODBC Schnittstellen laden.
      // Für verschiedene ODBC-Datenbanken muss dieser Treiber
      // nur einmal geladen werden.
      Class.forName("com.mysql.jdbc.Driver");
 
      // Verbindung zur ODBC-Datenbank 'sakila' herstellen.
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
 
  private static Connection getInstance()
  {
    if(conn == null)
      new MySQLConnection();
    return conn;
  }
  
  public static void getWarenkoerbe() //TODO return typ anpassen
  {
    conn = getInstance();
 
    if(conn != null)
    {
      // Anfrage-Statement erzeugen.
      Statement query;
      try {
        query = conn.createStatement();
 
        // Ergebnistabelle erzeugen und abholen.
        String sql = 
        		"SELECT order_id, product_id"
        		+ "FROM sales_flat_order_item"
        		+ "ORDER BY order_id, product_id";
        ResultSet result = query.executeQuery(sql);
 
        // Ergebnissätze durchfahren.
        while (result.next()) {
        	//TODO Datenstruktur ergänzen
          int order = result.getInt("order_id");
          int product = result.getInt("product_id");
          System.out.println(order + " " + product); // Für Tests
        }
      //TODO Datenstruktur returnen  
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void getKaufverhalten() //TODO return typ anpassen
  {
    conn = getInstance();
 
    if(conn != null)
    {
      // Anfrage-Statement erzeugen.
      Statement query;
      try {
        query = conn.createStatement();
 
        // Ergebnistabelle erzeugen und abholen.
        String sql = 
        		"SELECT kunden.customer_id, items.product_id"
        		+ "FROM sales_flat_order_item AS items"
        		+ "INNER JOIN sales_flat_order_grid AS kunden ON items.order_id = kunden.entity_id"
        		+ "ORDER BY kunden.customer_id, items.product_id";
        ResultSet result = query.executeQuery(sql);
 
        // Ergebnissätze durchfahren.
        while (result.next()) {
        	//TODO Datenstruktur ergänzen
          int customer = result.getInt("kunden.customer_id");
          int product = result.getInt("items.product_id");
          System.out.println(customer + " " + product); // Für Tests
        }
      //TODO Datenstruktur returnen  
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  
}