package iFace;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Community.Community;
import Singleton.Singleton;
import User.NoUser;
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
				iNewUser();

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
		String usernameretorno = Singleton.getInstance().openSession(username);
		if (usernameretorno.equals("200")) {
			System.out.println("Login efetuado.");
			return true;
		} else if (usernameretorno.equals("000")) {
			System.out.println("Conta não existe");
		} else if (usernameretorno.equals("001")) {

			System.out.println("Senha invalida");
		}
		return false;
	}
	public boolean verificPassword(String password){
		ArrayList<User> aux = Singleton.getInstance().users;

		for (User user : aux) {
			if (user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean usernameExist(String username) {

		ArrayList<User> aux = Singleton.getInstance().users;

		for (User user : aux) {
			if (user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;

	}


	public void iNewUser() {

		System.out.println("---- Criando uma nova conta-------");
		try {
			String name = JOptionPane.showInputDialog("Nome: ");
			String username = JOptionPane.showInputDialog("Username: ");
			while (usernameExist(username) == true) {
				System.out.println("Username já existe!\n Tente outro");
				username = JOptionPane.showInputDialog("Username: ");
			}
			String password = JOptionPane.showInputDialog("Senha: ");
			Singleton.getInstance().newUser(name, password, username);
			Singleton.getInstance().closeSession();
			System.out.println("Usuario criado com sucesso!");
			iMenu();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Dados invalidos");
		}

	}

	public void requisitarAmigo() {
		Integer friend;
		ArrayList<User> aux = Singleton.getInstance().users;
		System.out.println(aux.size());
		for (User user : aux) {
			if (!Singleton.getInstance().currentSession.equals(user) || user instanceof NoUser)
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
		if(Singleton.getInstance().currentSession.getFriends().size()!=0){
			op = Singleton.getInstance().cin.nextInt();
			switch (op) {
			case 1:
				System.out.println("Deseja enviar para qual amigo?");
				ArrayList<User> aux = Singleton.getInstance().currentSession.getFriends();

				for (User user : aux) {
					if (!Singleton.getInstance().currentSession.equals(user) || !(user instanceof NoUser))
						System.out.println("id: " + user.getId() + " Name: " + user.getName());
				}
				friend = Singleton.getInstance().cin.nextInt();
				System.out.println("Qual o corpo da menssagem:");
				String corpo = cin.nextLine();
				Message message = new Message(Singleton.getInstance().currentSession,
						Singleton.getInstance().users.get(friend), corpo);
				message.send();
				secondMenu();
				break;
			case 2:

				ArrayList<Message> recebe = Singleton.getInstance().messages
				.get(Singleton.getInstance().currentSession.getId());
				System.out.println(
						Singleton.getInstance().currentSession.getName() + "você tem " + recebe.size() + " menssagens!");
				if (recebe.size() != 0) {
					try {

						for (int i = 0; i < recebe.size(); ++i) {
							Message msg = recebe.get(i);
							System.out.println("ID: " + i + " Nome:" + ((User) msg.sender).getName());
						}

						System.out.println("Qual messagen deseja ler? Informe o Id da menssagem");
						op = cin.nextInt();
						System.out.println("Menssagem: " + recebe.get(op).message);
						secondMenu();


					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Não existe Menssagem!");
						secondMenu();
					}
				} 
				break;

			}
		}
		else {
			System.out.println("Você não tem amigos ainda!");
			secondMenu();
		}

	}

	public void gerenciarComunidade() {

		String op;
		Integer op1;
		String idCommunity;

		System.out.println("------Comunidade-----");
		System.out.println("1-Criar nova comunidade");
		System.out.println("2-Gerenciar minhas comunidades");

		op = cin.nextLine();
		switch (Integer.parseInt(op)) {
		case 1:
			String nome = JOptionPane.showInputDialog("Nome da comunidade ");
			String descricao = JOptionPane.showInputDialog("Descricao ");
			Singleton.getInstance().newCommunity(nome, descricao);

			System.out.println("Comunidade Criada");
			secondMenu();
			break;
		case 2:
			ArrayList<Community> aux = Singleton.getInstance().currentSession.getCommunities();
			System.out.println("Qual comunidade que  deseja gerenciar?");
			//System.out.println("tamanhi"+aux.size());
			for (Community community : aux) {
				System.out.println("Id: " + community.getId() + " Name: " + community.getName());
			}
			idCommunity = cin.nextLine();
			System.out.println(
					"Comunidade: " + Singleton.getInstance().currentSession.getCommunities().get(Integer.parseInt(idCommunity)).getName());
			System.out.println("1-Adicionar Membro");
			System.out.println("2-Remover membro");
			System.out.println("3-Enviar Menssagem");
			op = cin.nextLine();
			try{
				switch (Integer.parseInt(op)) {
				case 1:

					addMembrosComunidade(aux.get(Integer.parseInt(idCommunity)));
					secondMenu();
					break;
				case 2:
					removerMembroComunidade(Singleton.getInstance().currentSession.getCommunities().get(Integer.parseInt(idCommunity)));
					secondMenu();
					break;
				case 3:
					System.out.println("Qual o corpo da menssagem:");
					String corpo = cin.nextLine();
					Message message = new Message(Singleton.getInstance().currentSession,
							Singleton.getInstance().currentSession.getCommunities().get(Integer.parseInt(idCommunity)), corpo);
					message.send();
					secondMenu();
					break;

				}


			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Entrada Invalida");
				secondMenu();
			}
		}

	}



	public void removerMembroComunidade(Community community) {
		String member;
		ArrayList<User> aux = community.getMembers();
		System.out.println(aux.size());
		for (User user : aux) {
			if (!Singleton.getInstance().currentSession.equals(user) || !(user instanceof NoUser))
				System.out.println("id: " + user.getId() + " Name: " + user.getName());
		}
		System.out.println("Qual membro deseja remover? Escreva o ID");
		member = cin.nextLine();
		community.getMembers().remove(Integer.parseInt(member));
		System.out.println("Membro retirado da comunidade!");

	}

	public void addMembrosComunidade(Community community) {
	
		String member;
		ArrayList<User> aux = Singleton.getInstance().users;
		System.out.println(aux.size());
		for (User user : aux) {
			if (!Singleton.getInstance().currentSession.equals(user) || !(user instanceof NoUser))
				System.out.println("id: " + user.getId() + " Name: " + user.getName());
		}
		System.out.println("Qual membro deseja add? Escreva o ID");
		member = cin.nextLine();
		community.getMembers().add(Singleton.getInstance().users.get(Integer.parseInt(member)));
		System.out.println("Membro adicionado a comunidade!");

	}

	public void deletarConta() {
		String op1;
		System.out.println("Você realmente deseja deletar est conta?");
		System.out.println("Sim(1) ou Não(2)");
		op1 = cin.nextLine();
		switch (Integer.parseInt(op1)) {
		case 1:
			System.out.println("aqui");
			Singleton.getInstance().removeUser(Singleton.getInstance().currentSession.getId());
			System.out.println("Conta deletada!");
			iMenu();
			break;

		case 2:
			System.out.println("Ficamos feliz por tê-lo aqui!");
			secondMenu();
			break;
		}
		
	}

	public String informacaoesUser() {
		if (Singleton.getInstance().currentSession != null)
			return Singleton.getInstance().currentSession.toString();
		return "error info";

	}

	public void editarPerfil() {
		String nome;
		String email;
		String idade;
		System.out.println("Edição do Perfil");
		System.out.println("1-Nome");
		System.out.println("2-Email");
		System.out.println("3-Idade");
		System.out.println("O que quer mudar?");

		String op1 = cin.nextLine();
		switch (Integer.parseInt(op1)) {
		case 1:

			nome = JOptionPane.showInputDialog("Nome: ");
			Singleton.getInstance().currentSession.setName(nome);
			secondMenu();
			break;
		case 2:

			email = JOptionPane.showInputDialog("Email: ");
			Singleton.getInstance().currentSession.setName(email);
			secondMenu();
			break;
		case 3:

			idade = JOptionPane.showInputDialog("Idade ");;
			Singleton.getInstance().currentSession.setIdade(idade);
			secondMenu();
			break;

		default:
			break;
		}

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
		System.out.println("8-Deletar Conta");

		try {
			op = cin.nextLine();

			switch (Integer.parseInt(op)) {
			case 1:
				editarPerfil();
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
				gerenciarComunidade();
				break;
			case 6:
				System.out.println(informacaoesUser());
				break;
			case 7:
				System.out.println("Até mais!");
				iMenu();
				break;
			case 8:
				deletarConta();
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error interno1");
		}

	}
}
