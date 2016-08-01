package iFace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
public class Sistema {
	
	int opcao;
	String login;
	String passaword;
	String nome;
	Scanner inn = new Scanner(System.in);//entrada
	Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();
	
	
	public void menuI(){
		System.out.println(" ----iFace -----");
		System.out.println("1-Login \n ");
		System.out.println("2-Crie uma nova conta!");
		System.out.println("*Já tenha um conta ,faça o login");
		opcao = Integer.parseInt(inn.nextLine());
		
		switch (opcao) {
		case 1 :
			login();
			break;
		case 2 :
			criarConta();
			break;
			
		default:
			break;
		}
	}
	public void login(){
		System.out.println("Login:");
		login = inn.nextLine();
		System.out.println("Senha:");
		passaword = inn.nextLine();
		
		if(buscaUsuario(login)!= null){
			
			if(buscaUsuario(login).getPassword() == passaword){
				System.out.println("Bem Vindo!\n");
			}
			else {
				System.out.println("Senha incorreta!/n Tente Novamente: \n");
				login();
			}
		}
		else {
			System.out.println("Usuario inexistente\n");
			menuI();
		}		
	}
	public void criarConta(){
		System.out.println("Criando uma Conta - iFace");
		System.out.println("Qual é seu nome?");
		nome = inn.nextLine();
		System.out.println("Qual login que deseja?");
		login = inn.nextLine();
		if(buscaUsuario(login)!= null){
			while(buscaUsuario(login)!=null)
				System.out.println("Esse login já existe , tente um novo.");
			login = inn.nextLine();
		}
		System.out.println("Entre com sua senha:");
		passaword = inn.nextLine();
		
		usuarios.put(login ,new Usuario(nome,login,passaword,null,null));
		System.out.println("Conta criada com sucesso!");
		System.out.println(usuarios.get(login).toString());
	
	}
	public Usuario buscaUsuario(String login){
		if(usuarios.containsKey(login)){
			return usuarios.get(login);
		}
		else return null;
	}
	
	public void perfil(Usuario atual){
		System.out.println(" ----iFace -----");
		System.out.println("Bem Vindo "+ atual.getName());
		
	}
	
	
	
}
