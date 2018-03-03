package jeu.cor;

import application.Hearthstone;
import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IPlateau;
import jeu.Plateau;
import jeu.capacites.CapaciteAttaqueCible;
import jeu.cartes.Sort;
import tools.Console;

public class InterfaceSortAttaqueCible extends InterfaceConsole {

	public InterfaceSortAttaqueCible(InterfaceConsole ihm) {
		super(ihm);
	}

	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof Sort))
			return false;
		
		Sort sort = (Sort) obj;
		if (! (sort.getCapacite() instanceof CapaciteAttaqueCible))
			return false;
		
		return true;
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		Console out = Hearthstone.out;
		
		try {
			out.println("Qu'est-ce que tu vise ?");
			out.println("1. Le héros");
			out.println("2. Une autre carte");
			out.print("-->");
			int choixCible = out.readInt();
			if (choixCible==1) {
				return  board.getAdversaire(board.getJoueurCourant());
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
					return carteCible;
				}
			}
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		return null;	
		
	}
	
}
