package jeu.cor;

import application.Hearthstone;
import jeu.HearthstoneException;
import jeu.Heros;
import jeu.ICarte;
import jeu.IPlateau;
import jeu.Plateau;
import tools.Console;

public class InterfaceJaina extends InterfaceConsole {

	public InterfaceJaina(InterfaceConsole suivant) {
		super(suivant);
	}

	
	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof Heros))
			return false;
		
		Heros heros = (Heros) obj;
		return heros.getNom().contains("Jaina");
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		Console out = Hearthstone.out;
		
		
		try {
			out.println("Qu'est-ce que tu vises avec ton pouvoir ?");
			out.println("1. Le héros");
			out.println("2. Une autre carte");
			out.print("-->");
			int choixCible = out.readInt();
			if (choixCible==1) {
				board.getJoueurCourant().utiliserPouvoir(board.getAdversaire(board.getJoueurCourant()));
			}
			else {
				out.println("Quelle carte vises-tu ? (donne un bout de son nom)");
				out.print("-->");
				String nomCarteCible = out.readLine();
				ICarte carteCible = board.getAdversaire(board.getJoueurCourant()).getCarteEnJeu(nomCarteCible);
				if (carteCible==null) {
					throw new HearthstoneException("Cette carte n'est pas en jeu...");
				}
				else {
					board.getJoueurCourant().utiliserPouvoir(carteCible);
				}
			}
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		return null;	
	}
}
