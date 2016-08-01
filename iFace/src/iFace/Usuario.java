package iFace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class  Usuario {

	protected String nome;
	protected String login; 
	protected String senha;
	protected String email;
	protected ArrayList<Usuario> amigos =  new ArrayList<Usuario>();
	protected Hashtable<String,String> soliticaoAmizades;
	
	
	
	public  Usuario (String nome,String email, String login, String senha, ArrayList<Usuario> amigos ,Hashtable<String, String> soliticaoAmizades ){
		
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.senha = senha; 
		this.amigos = amigos;
		this.soliticaoAmizades = soliticaoAmizades;
		
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Hashtable<String, String> getSoliticaoAmizades() {
		return soliticaoAmizades;
	}

	public void setSoliticaoAmizades(Hashtable<String, String> soliticaoAmizades) {
		this.soliticaoAmizades = soliticaoAmizades;
	}

	public ArrayList<Usuario> getAmigos() {
		return amigos;
	}

	public void setAmigos(ArrayList<Usuario> amigos) {
		this.amigos = amigos;
	}

	

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return senha;
	}

	public void setPassword(String password) {
		this.senha = password;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", login=" + login + ", senha=" + senha + "]";
	}
	
	
}
