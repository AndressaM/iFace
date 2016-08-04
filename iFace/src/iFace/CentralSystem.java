package iFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Community.Community;
import Community.NoCommunity;
import User.NoUser;
import User.User;

public class CentralSystem {
	
	Scanner cin = new Scanner(System.in);
	HashMap<Integer, ArrayList<Message>> messages;
	ArrayList<User> users;
	ArrayList<Community> communities;
	User currentSession = null;

	public CentralSystem() {
		this.users = new ArrayList<>();
		this.communities = new ArrayList<>();
		this.messages = new HashMap<>();
	}

	public void openSession() {
		
		// TODO Auto-generated method stub
		
		String username = cin.next();
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				String pass = cin.next();
				if (user.getPassword().equals(pass)) {
					currentSession = user;
				}
			
			}
		}
		System.out.println("Nao tem");

	}

	public void closeSession() {
		// TODO Auto-generated method stub
		currentSession = null;
	}

	public void newUser(String name, String password,String username) {
		// TODO Auto-generated method stub
		User user = new User(users.size(), name, password, username);
		users.add(user);

	}

	public void removeUser(Integer id){
		// TODO Auto-generated method stub
		NoUser user = (NoUser) users.get(id);
		users.set(id, user);
	}
	

	public void newCommunity(String name) {
		// TODO Auto-generated method stub
		if (currentSession != null) {
			communities.add(new Community(name, currentSession.getId()));
			return;
		}
		System.out.println("Faça login");
	}

	public void removeCommunity() {
		// TODO Auto-generated method stub
		Integer id = cin.nextInt();
		NoCommunity community = (NoCommunity) communities.get(id);
		communities.set(id, community);

	}
	
	public void addFriends(Integer id){
		
	}
	
	public void request(User user){
		user.getRequest().add(user);
	}
		
}
