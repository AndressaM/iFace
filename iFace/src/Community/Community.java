package Community;

import java.util.ArrayList;

import User.User;
import iFace.Agent;

public class Community extends Agent {
	private ArrayList<User> members = new ArrayList<User>();
	protected String name;
	protected String descricao;
	protected Integer admin;
	protected Integer id;



	public Community(Integer id ,String name,String descricao, Integer admin) {
		super();
		this.id = id;
		this.descricao = descricao;
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
		return descricao;
	}

	public void setDescrição(String descrição) {
		this.descricao = descrição;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
