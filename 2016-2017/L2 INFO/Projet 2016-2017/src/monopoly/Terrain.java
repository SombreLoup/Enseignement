package monopoly;

import monopoly.actions.PayerLoyer;
import plateau.Case;

public class Terrain extends Case {
	private	String	nom;
	private	int		coutTerrain;
	private	int		loyer;
	private	int		coutMaison;
	
	
	private	Monopole	famille;
	
	public int getCoutTerrain() {
		return coutTerrain;
	}

	private	JoueurMonopoly	proprietaire;
	
	public JoueurMonopoly getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Terrain(String nom, int coutTerrain, int loyer, int coutMaison) {
		this.nom = nom;
		this.loyer = loyer;
		this.coutMaison = coutMaison;
		this.coutTerrain = coutTerrain;
		
		addActionObligatoire(new PayerLoyer(this));
	}

	public String getNom() {
		return nom;
	}

	public int getLoyer() {
		return loyer;
	}

	public int getCoutMaison() {
		return coutMaison;
	}

	@Override
	public String toString() {
		String p;
		if (proprietaire==null)
			p="null";
		else
			p=proprietaire.getNom();
		
		return "Terrain [nom=" + nom + ", loyer=" + loyer + ", coutMaison=" + coutMaison + ";proprio="+p+ "]";
	}

	public Monopole getFamille() {
		return famille;
	}

	public void setFamille(Monopole famille) {
		this.famille = famille;
	}

}
