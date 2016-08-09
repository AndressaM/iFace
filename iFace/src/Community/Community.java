package Community;

import java.util.ArrayList;

import User.User;
import iFace.Agent;

public class Community extends Agent {
	private ArrayList<User> members;
	protected String name;
	protected String descrição;
	protected Integer admin;

	public Community(String name, Integer admin) {
		super();
		this.name = name;
		this.admin = admin;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrição() {
		return descrição;
	}

	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

}
