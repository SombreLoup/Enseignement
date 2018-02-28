package jeu.cor;

import application.Hearthstone;
import jeu.HearthstoneException;
import jeu.ICarte;
import jeu.IPlateau;
import jeu.Plateau;
import jeu.capacites.CapaciteCharge;
import jeu.cartes.Sort;
import tools.Console;

public class InterfaceSortCharge extends InterfaceConsole {

	public InterfaceSortCharge(InterfaceConsole ihm) {
		super(ihm);
	}

	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof Sort))
			return false;
		
		Sort sort = (Sort) obj;
		if (! (sort.getCapacite() instanceof CapaciteCharge))
			return false;
		
		return true;
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		Console out = Hearthstone.out;
		
		
		try {
			out.println("Quel serviteur de ton jeu ? (un bout de son nom)");
			out.print("-->");
			String nomCible = out.readLine();
			ICarte cible = board.getJoueurCourant().getCarteEnJeu(nomCible);
			
			if (cible==null) 
				throw new HearthstoneException("Cette carte n'est pas en jeu...");
	
			return cible;		
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		
		return null;	
	}
	
}
