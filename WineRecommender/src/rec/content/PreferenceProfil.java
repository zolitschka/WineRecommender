package rec.content;

import java.util.Vector;

import rec.User;
import rec.database.MySQLConnection;

//Präferenzprofil eines Users
public class PreferenceProfil {
	final static Vector<User> userList = MySQLConnection.getUser();

	public static Vector<User> getUserList() {
		return userList;
	}

}
