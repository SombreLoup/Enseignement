package jeu;

import java.util.ArrayList;

import jeu.cartes.Carte;

public class Joueur {
	public final static int	MAX_MANA = 10;
	
	private	String	pseudo;
	private	int		mana;
	private	Heros	heros;

	private		ArrayList<Carte>		deck = new ArrayList<Carte>();
	
	
	public Joueur(String pseudo) {
		this.pseudo = pseudo;
		this.mana = 0;
		try {
			heros = Heros.getHeros("Jaina");
		} catch (ExceptionHearthstone e) {
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
	
	
	public ArrayList<Carte> getDeck() {
		return deck;
	}
	

	public Carte piocher() throws ExceptionHearthstone {
		int	nb = deck.size();
		int num = (int) (Math.random()*nb);
		Carte	carte = deck.get(num);
		deck.remove(num);
		
		return carte;
	}


	public boolean ajouter(Carte e) {
		return deck.add(e);
	}


	@Override
	public String toString() {
		String s = "Joueur [pseudo=" + pseudo + ", heros=" + heros + ", mana=" + mana + "]\n";
		for (Carte carte : deck) {
			s += "\t"+carte+"\n";
		}
		
		return s;
	}
}
