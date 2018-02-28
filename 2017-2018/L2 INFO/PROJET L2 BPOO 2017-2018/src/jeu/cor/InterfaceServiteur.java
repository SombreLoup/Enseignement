package jeu.cor;

import application.Hearthstone;
import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IPlateau;
import jeu.Plateau;
import jeu.cartes.Serviteur;
import tools.Console;

public class InterfaceServiteur extends InterfaceConsole {

	public InterfaceServiteur(InterfaceConsole suivant) {
		super(suivant);
	}

	
	@Override
	protected boolean peutInteragir(Object obj) {
		return (obj instanceof Serviteur);
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		Console out = Hearthstone.out;
		
		ICarte carte = (ICarte)obj;
		try {
			out.println("Qu'est-ce que tu vise ?");
			out.println("1. Le héros");
			out.println("2. Une autre carte");
			out.print("-->");
			int choixCible = out.readInt();
			if (choixCible==1) {
				board.getJoueurCourant().utiliserCarte(carte, board.getAdversaire(board.getJoueurCourant()));
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
					board.getJoueurCourant().utiliserCarte(carte, carteCible);
				}
			}
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		return null;	
	}
}
