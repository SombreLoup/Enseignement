package jeu;

import java.util.ArrayList;

import jeu.cartes.Carte;

public class Joueur implements IJoueur {
	public final static int	MAX_MANA = 10;
	public final static int TAILLE_DECK = 12;
	
	private	String	pseudo;
	private	int		mana;
	private	Heros	heros;

	private		ArrayList<ICarte>		deck = new ArrayList<ICarte>();
	private		ArrayList<ICarte>		main = new ArrayList<ICarte>();
	private		ArrayList<ICarte>		jeu = new ArrayList<ICarte>();
	private boolean doitJouer;
	
	
	public Joueur(String pseudo) {
		this.pseudo = pseudo;
		this.mana = 0;
		this.doitJouer = false;
		try {
			heros = Heros.getHeros("Jaina");
		} catch (HearthstoneException e) {
			e.printStackTrace();
		}
	}

	public Joueur(String pseudo, Heros heros) {
		this.pseudo = pseudo;
		this.mana = 0;
		setHeros(heros);
	}

	public String getPseudo() {
		return pseudo;
	}

	public int getMana() {
		return mana;
	}
	
	public Heros getHeros() {
		return heros;
	}
	
	public void setHeros(Heros heros) {
		if (heros==null)
			throw new NullPointerException("Le heros ne doit pas être null");
		
		this.heros = heros;
	}
	
	
	public ArrayList<ICarte> getDeck() {
		return deck;
	}
	
	public ArrayList<ICarte> getMain() {
		return main;
	}
	
	public ArrayList<ICarte> getJeu() {
		return jeu;
	}
	

	
	@Override
	public String toString() {
		String s = "Joueur [pseudo=" + pseudo + ", heros=" + heros + ", mana=" + mana + "]\n";
		for (ICarte carte : deck) {
			s += "\t"+carte+"\n";
		}
		
		return s;
	}

	public boolean ajouter(Carte e) {
		return deck.add(e);
	}


	@Override
	public void prendreTour() throws HearthstoneException {
		if (doitJouer==true)
			throw new HearthstoneException("Tu as déjà le tour, espèce de gobelin décérébré !");
		
		doitJouer = true;
		augmenterMana();
		piocher();
	}

	private void augmenterMana() {
		if (mana<MAX_MANA)
			mana++;
	}

	@Override
	public void finirTour() throws HearthstoneException {
		if (doitJouer==false)
			throw new HearthstoneException("Tu n'as le tour, espèce de limace baveuse !");		
		
		doitJouer = false;
		Plateau.getInstance().finTour(this);
	}

	@Override
	public void piocher() {
		int	nb = deck.size();
		if (nb==0)
			return;
		
		int num = (int) (Math.random()*nb);
		ICarte	carte = deck.get(num);
		deck.remove(num);
		main.add(carte);
	}

	@Override
	public void jouerCarte(ICarte carte) throws HearthstoneException {
		if (! main.contains(carte))
			throw new HearthstoneException("Tricheur ! Tu ne possède pas cette carte "+carte);
		
		main.remove(carte);
		jeu.add(carte);
		carte.executerEffetDebutTour();
	}

	@Override
	public void utiliserCarte(ICarte carte, Object cible) throws HearthstoneException {
		if (! jeu.contains(carte))
			throw new HearthstoneException("Tricheur ! Cette carte n'est pas encore en jeu --> "+carte);
		
		carte.executerAction(cible);
		if (carte.disparait())
			jeu.remove(carte);
	}

	@Override
	public void utiliserPouvoir(Object cible) {
		// TODO Auto-generated method stub
		
	}
}
