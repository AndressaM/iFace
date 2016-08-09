package Main;

import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.text.html.HTMLEditorKit.Parser;

import Singleton.Singleton;
import User.User;
import iFace.CentralSystem;
import iFace.ConsoleSystem;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {

		// CentralSystem s = new CentralSystem();
		Scanner cin = new Scanner(System.in);
		ConsoleSystem Cs = new ConsoleSystem();
		Singleton.getInstance().newUser("andressa", "pass", "and");
		Singleton.getInstance().newUser("andressa1", "pass", "and1");
		Singleton.getInstance().newUser("andressa2", "pass", "and2");
		Singleton.getInstance().newUser("andressa3", "pass", "and3");
		Singleton.getInstance().newUser("andressa4", "pass", "and4");
		
		
		Cs.iMenu();


		
		

	}

}
