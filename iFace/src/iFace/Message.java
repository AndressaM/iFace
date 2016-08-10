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
        if (dist instanceof User) {
            User agent = (User) dist;
            Singleton.getInstance().messages.get(agent.getId()).add(this);
            System.out.println("MESSAGEM ENVIADA COM SUCESSO");
        } else if (dist instanceof Community) {
            Community community = (Community) sender;
            for (User agent : community.getMembers()) {
                Singleton.getInstance().messages.get(agent.getId()).add(this);
            }
        }
    }

}
