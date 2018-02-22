package jeu;

import java.util.ArrayList;

public interface IJoueur {
	void prendreTour() throws HearthstoneException;
	void finirTour() throws HearthstoneException;
	void piocher();
	void jouerCarte(ICarte carte) throws HearthstoneException;
	void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException;
	void utiliserPouvoir(Object cible);
	void perdre(ICarte carte) throws HearthstoneException;
	ArrayList<ICarte> getMain();
	ArrayList<ICarte> getJeu();
	ICarte getCarteEnJeu(String nomCarte);
	Heros getHeros();
	ICarte getCarteEnMain(String nomCarteMain);
	String getPseudo();
	int getStockMana();
}
