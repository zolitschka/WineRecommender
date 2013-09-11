package rec.database;

import java.util.Vector;

import rec.User;
import rec.Wine;

public class GetBuyHistory {
	private static Vector<User> userList = MySQLConnection.getUser();

	public static Vector<User> getUserList() {
		return userList;
	}
}
