package User;

import java.util.ArrayList;

import Community.Community;
import iFace.Agent;

public class User extends Agent {
	Integer id;
	String name;
	String password;
	String email;
	String username;
	ArrayList<User> friends;
	ArrayList<User> request;
	ArrayList<Community> communities;
	Integer lastRead;
	

	public User(Integer id, String name, String password, String username) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
		this.friends = new ArrayList<>();
		this.request = new ArrayList<>();
		this.communities = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public ArrayList<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(ArrayList<Community> communities) {
		this.communities = communities;
	}

	public Integer getLastRead() {
		return lastRead;
	}

	public void setLastRead(Integer lastRead) {
		this.lastRead = lastRead;
	}

	public ArrayList<User> getRequest() {
		return request;
	}

	public void setRequest(ArrayList<User> request) {
		this.request = request;
	}
	
	

}
