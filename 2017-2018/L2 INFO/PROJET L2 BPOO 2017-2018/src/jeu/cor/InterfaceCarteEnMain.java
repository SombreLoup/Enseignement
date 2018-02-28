package jeu.cor;

import application.Hearthstone;
import jeu.HearthstoneCibleNullException;
import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IPlateau;
import jeu.Plateau;
import tools.Console;

public class InterfaceCarteEnMain extends InterfaceConsole {

	public InterfaceCarteEnMain(InterfaceConsole suivant) {
		super(suivant);
	}

	
	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof String))
			return false;
		
		String	s = (String)obj;
		return s.contains("carte en main");
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		Console out = Hearthstone.out;
		
		try {
			out.println("Laquelle ? (donne un bout de son nom);");
			out.print("-->");
			String nomCarteMain = out.readLine();
			ICarte carte = board.getJoueurCourant().getCarteEnMain(nomCarteMain);
			Object cible;
			
			if (carte==null) {
				throw new HearthstoneException("Cette carte n'est pas dans ta main...");
			}
			
			try {
				board.getJoueurCourant().jouerCarte(carte);
				
			} catch (HearthstoneCibleNullException e) {
				cible =  Hearthstone.ihm.interagir(carte);
				board.getJoueurCourant().jouerCarte(carte,cible);
			}
			
			return null;
			
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		
		return null;	
	}
}
