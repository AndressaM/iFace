package Community;

import java.util.ArrayList;

import User.User;
import iFace.Agent;

public class Community extends Agent {
	private ArrayList<User> members;
	String name;
	Integer admin;

	public Community(String name, Integer admin) {
		super();
		this.name = name;
		this.admin = admin;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

}
