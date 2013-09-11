package rec.content;

import java.util.Vector;

import rec.User;
import rec.database.GetBuyHistory;

//Präferenzprofil eines Users
public class PreferenceProfil {
	final static Vector<User> userList = GetBuyHistory.getUserList();

	public static Vector<User> getUserList() {
		return userList;
	}

}
