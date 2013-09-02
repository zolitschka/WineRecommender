package rec;

import rec.database.MySQLConnection;

public class main {
	// GUI skalierbar über Parameter im Konstruktor
	public static void main(String[] args) {
		GUI gui = new GUI(800);
		MySQLConnection.getWineContent();

	}
}
