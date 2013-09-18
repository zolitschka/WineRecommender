package rec.database;

import java.util.Vector;

import rec.User;
import rec.collaborative.History;

public class GetBuyHistory {
	private static Vector<User> userList = MySQLConnection.getUser();
	private static Vector<History> orderHistory= MySQLConnection.getWarenkoerbe(); 

	public static Vector<History> getOrderHistory() {
		return orderHistory;
	}

	public static Vector<User> getUserList() {
		return userList;
	}
}
