package application;

import java.util.ArrayList;

import cor.Console;
import cor.Interface;
import cor.InterfaceCercle;
import cor.InterfaceQuitter;
import cor.InterfaceXML;
import dessin.Dessin;

public class Application {
	private	static Interface	ihm = null;
	private	static Dessin dessin = new Dessin("Mon dessin");
	
	public final static Console es = new Console();
	
	public static void main(String[] args) {
		ihm = initialiserInterfaces();
		
		if (ihm==null) {
			System.out.println("L'application ne sais rien faire....");
			System.exit(0);
		}	
		
		while (true) {
			String choix = menu();
			try {
				ihm.interagir(choix, dessin);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String menu() {
		ArrayList<String>	menu = new ArrayList<String>();
		Interface i = ihm;
		while (i != null) {
			menu.add(i.getDescription());
			i = i.getSuivant();
		}
		
		int n = 1;
		for (String s : menu) {
			es.println(""+n+". "+s);
			n++;
		}
		
		es.println("");
		es.println("Votre choix : ");
		int choix = es.readInt();
		
		return menu.get(choix-1);
	}

	private static Interface initialiserInterfaces() {
		Interface monInterface = null;
		monInterface = new InterfaceCercle(monInterface);
		monInterface = new InterfaceXML(monInterface);
		monInterface = new InterfaceQuitter(monInterface);
		
		return monInterface;
	}
}
