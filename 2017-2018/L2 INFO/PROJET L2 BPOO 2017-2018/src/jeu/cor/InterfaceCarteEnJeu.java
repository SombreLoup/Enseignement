package jeu.cor;

import application.Hearthstone;
import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IPlateau;
import jeu.Plateau;
import tools.Console;

public class InterfaceCarteEnJeu extends InterfaceConsole {

	public InterfaceCarteEnJeu(InterfaceConsole suivant) {
		super(suivant);
	}

	
	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof String))
			return false;
		
		String	s = (String)obj;
		return s.contains("carte en jeu");
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		Console out = Hearthstone.out;
		
		
		try {
			out.println("Laquelle ? (donne un bout de son nom);");
			out.print("-->");
			String nomCarteJeu = out.readLine();
			ICarte carte = board.getJoueurCourant().getCarteEnJeu(nomCarteJeu);
			
			if (carte==null) 
				throw new HearthstoneException("Cette carte n'est pas en jeu...");
			
			return carte;
			
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		
		return null;	
	}
}
