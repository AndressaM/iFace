package iFace;

import Community.Community;
import Singleton.Singleton;
import User.User;

public class Message {
	Agent sender;
	Agent dist;
	String message;

	public Message(Agent sender, Agent dist, String message) {
		super();
		this.sender = sender;
		this.dist = dist;
		this.message = message;
	}

	public void send() {
		if (sender instanceof User) {
			User agent = (User) sender;
			Singleton.getInstance().messages.get(agent.getId());
		} else if (sender instanceof Community) {
			Community community = (Community) sender;
			for (User agent : community.getMembers()) {
				Singleton.getInstance().messages.get(agent.getId());
			}
		}
	}

}
