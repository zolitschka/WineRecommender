package rec.database;

import java.util.Vector;

import rec.User;

/*
 * 
 * Die Klasse GetBuyHistory stellt eine Liste mit Usern zur Verfügung
 * 
 */

public class GetBuyHistory {
	private static Vector<User> userList = MySQLConnection.getUser();

	public static Vector<User> getUserList() {
		return userList;
	}
}
