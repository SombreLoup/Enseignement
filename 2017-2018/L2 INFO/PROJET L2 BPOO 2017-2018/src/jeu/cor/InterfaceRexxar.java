package jeu.cor;

import jeu.HearthstoneException;
import jeu.Heros;
import jeu.IPlateau;
import jeu.Plateau;

public class InterfaceRexxar extends InterfaceConsole {

	public InterfaceRexxar(InterfaceConsole suivant) {
		super(suivant);
	}
	
	@Override
	protected boolean peutInteragir(Object obj) {
		if (! (obj instanceof Heros))
			return false;
		
		Heros heros = (Heros) obj;
		return heros.getNom().contains("Rexxar");
	}
	
	@Override
	protected Object executerInteragir(Object obj) {
		IPlateau board = Plateau.getInstance();
		try {
			board.getJoueurCourant().utiliserPouvoir(board.getAdversaire(board.getJoueurCourant()));
		} catch (HearthstoneException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
}
