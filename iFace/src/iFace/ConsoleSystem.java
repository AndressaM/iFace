package iFace;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Singleton.Singleton;
import User.User;

public class ConsoleSystem {

	Scanner cin = new Scanner(System.in);
	// CentralSystem cs = new CentralSystem();
	String op = "0";

	public void iMenu() {

		System.out.println("----------iFace-----------");
		System.out.println("1-Login");
		System.out.println("2-Criar Conta");
		try {
			op = cin.nextLine();
			switch (Integer.parseInt(op)) {
			case 1:
				if (iLogin()) {
					System.out.println(
							"Numero de solicitações " + Singleton.getInstance().currentSession.getRequest().size());
					System.out.println("Numero de menssagens " + Singleton.getInstance().messages
							.get(Singleton.getInstance().currentSession.getId()).size());
					System.out.println(Singleton.getInstance().currentSession.getName());
					secondMenu();
				}
				break;
			case 2:

				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Entrada invalida!");
			// System.out.println(e.getMessage());
		}

	}

	public boolean iLogin() {
		String username = JOptionPane.showInputDialog("Username:");
		if (Singleton.getInstance().openSession(username).equals("200")) {
			System.out.println("Login efetuado.");
			return true;
		} else if (Singleton.getInstance().openSession(username).equals("000")) {
			System.out.println("Conta não existe");
		} else if (Singleton.getInstance().openSession(username).equals("001")) {
			System.out.println("Senha invalida");
		}
		return false;
	}

	public void iNewUser() {

		String username = JOptionPane.showInputDialog("Username: ");
		String name = JOptionPane.showInputDialog("Nome: ");
		String password = JOptionPane.showInputDialog("Senha: ");
		Singleton.getInstance().newUser(name, password, username);
	}

	public void requisitarAmigo() {
		Integer friend;
		ArrayList<User> aux = Singleton.getInstance().users;
		System.out.println(aux.size());
		for (User user : aux) {
			if (!Singleton.getInstance().currentSession.equals(user))
				System.out.println("id: " + user.getId() + " Name: " + user.getName());
		}
		while (true) {
			System.out.println("Entre com id desejado:");
			try {
				friend = Singleton.getInstance().cin.nextInt();
				Singleton.getInstance().users.get(friend).getRequest().add(Singleton.getInstance().currentSession);
				System.out.println("Solicitação enviada!");
				secondMenu();
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Entrada inesperada");
				System.out.println("Observer se digitou corretamente o ID");
				cin.next();
			}
		}

	}

	public void gerenciarSolicitcoes() {
		Integer friend, op;
		System.out.println("Solicitação de Amizades");
		ArrayList<User> aux = Singleton.getInstance().currentSession.getRequest();
		System.out.println(aux.size());
		for (User user : aux) {
			if (!Singleton.getInstance().currentSession.equals(user))
				System.out.println("id: " + user.getId() + " Name: " + user.getName());
		}
		while (true) {
			System.out.println("Entre com id desejado:");
			try {
				friend = Singleton.getInstance().cin.nextInt();
				System.out.println("Deseja adicionar(1) ou não aceitar(2)");
				op = Singleton.getInstance().cin.nextInt();
				if (op == 1) {
					Singleton.getInstance().currentSession.getFriends().add(Singleton.getInstance().users.get(friend));
					Singleton.getInstance().users.get(friend).getFriends().add(Singleton.getInstance().currentSession);
					Singleton.getInstance().currentSession.getRequest()
							.remove(Singleton.getInstance().users.get(friend));
					System.out.println("Vocês são amigos agora");
				} else {
					Singleton.getInstance().currentSession.getRequest()
							.remove(Singleton.getInstance().users.get(friend));
					System.out.println("Solicitação rejeitada!");
				}
				secondMenu();
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Entrada inesperada");
				System.out.println("Observer se digitou corretamente o ID");
				cin.next();
			}

		}
	}

	public void gerenciarMenssagens() {
		System.out.println("--------Menssagens-----");
		Integer friend, op;
		System.out.println("1-Enviar Menssagen");
		System.out.println("2-Ler Menssagen");
		op = Singleton.getInstance().cin.nextInt();
		switch (op) {
		case 1:
			System.out.println("Deseja enviar para qual amigo?");
			ArrayList<User> aux = Singleton.getInstance().currentSession.getFriends();
			// System.out.println(aux.size());
			for (User user : aux) {
				if (!Singleton.getInstance().currentSession.equals(user))
					System.out.println("id: " + user.getId() + " Name: " + user.getName());
			}
			friend = Singleton.getInstance().cin.nextInt();
			System.out.println("Qual o corpo da menssagem:");
			String corpo = cin.nextLine();
			Message message = new Message(Singleton.getInstance().currentSession,
					Singleton.getInstance().users.get(friend), corpo);
			message.send();
			break;
		case 2:
			System.out.println("aqui");
			ArrayList<Message> recebe = Singleton.getInstance().messages.get(Singleton.getInstance().currentSession);
			System.out.println(recebe.size());
			try {
				System.out.println("aquii2");
				for (int i = 0; i < recebe.size(); ++i) {
					Message msg = recebe.get(i);
					System.out.println("ID:" + i + "Nome:" + ((User) msg.sender).getName());
				}

				System.out.println("Qual messagen deseja ler? Informe o id da menssagem");
				op = cin.nextInt();
				System.out.println(recebe.get(op).message);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("não existe Menssagem!");
			}
			
			break;
		
		}
		secondMenu();

	}
	public void gerenciarComunidade(){
		
		Integer op;
		
		System.out.println("------Comunidade-----");
		System.out.println("1-Criar nova comunidade");
		System.out.println("2-Gerenciar minhas comunidades");
		
		
	}

	public void secondMenu() {

		System.out.println("--------------Bem Vindo ao iFACE--------------");
		
		System.out.println("1-Editar Perfil");
		System.out.println("2-Adicionar Amigos");
		System.out.println("3-Solicitaçãoes de Amizade");
		System.out.println("4-Menssagens");
		System.out.println("5-Comunidade");
		System.out.println("6-Minhas informçãoes");
		System.out.println("7-Logout");

		try {
			op = cin.nextLine();
			switch (Integer.parseInt(op)) {
			case 1:

				break;
			case 2:
				requisitarAmigo();

				break;
			case 3:
				gerenciarSolicitcoes();
				break;
			case 4:
				gerenciarMenssagens();
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				System.out.println("Até mais!");
				iMenu();
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error interno1");
		}

	}
}
