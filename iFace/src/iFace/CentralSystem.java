package iFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

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

	public String openSession(String username) {

		// TODO Auto-generated method stub
		for (User user : users) {
			if (user.getUsername().equals(username) && !(user instanceof NoUser) ) {
				
				String pass = JOptionPane.showInputDialog("Senha: ");
				
				if (user.getPassword().equals(pass)) {
					currentSession = user;
					return ("200");
				} else
					return ("001");
			}
		}
		return ("000");
	}

	public void closeSession() {
		// TODO Auto-generated method stub
		currentSession = null;
	}

	public void newUser(String name, String password, String username) {
		// TODO Auto-generated method stub
		User user = new User(users.size(), name, password, username);
		users.add(user);
		messages.put(user.getId(), new ArrayList<>());

	}

	public void removeUser(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("aquii");
		NoUser user = new NoUser(id, users.get(id).getName(), users.get(id).getPassword(), users.get(id).getUsername());
		users.set(id, user);
	}

	public void newCommunity(String name,String descricao) {
		// TODO Auto-generated method stub
		if (currentSession != null) {
			this.currentSession.getCommunities().add(new Community(communities.size(),name,descricao,currentSession.getId()));
			return;
		}
		
	}

	public void removeCommunity(Integer id) {
		// TODO Auto-generated method stub
		
		NoCommunity community = (NoCommunity) communities.get(id);
		communities.set(id, community);

	}

	public void addFriends(Integer id) {
		this.currentSession.getFriends().add(users.get(id));
	}

	public void request(User user) {
		user.getRequest().add(user);
	}

}
