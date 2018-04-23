package monopoly;

import plateau.Joueur;

public class JoueurMonopoly extends Joueur {

	private	int	compte;
	
	private final static 	int	LIBRE = 0;
	private final static 	int	EMPRISONNE = 1;
	private final static 	int	DOITPAYERLOYER = 2;
	private final static 	int	DOITREJOUER1 = 4;
	private final static 	int	DOITREJOUER2 = 8;
	private					int	etat = LIBRE;


	public JoueurMonopoly(String nom) {
		super(nom);
		compte = 1500;	// en euros
	}

	@Override
	public int lancerDes() {
		int d1, d2;
		d1 = (int) Math.round((Math.random()*6+1));
		d2 = (int) Math.round((Math.random()*6+1));	
		
		return d1+d2;
	}
	
	@Override
	public String toString() {
		return getNom()+"[compte="+compte+";case="+getCaseCourante()+"]";
	}

	public int getCompte() {
		return compte;
	}

	public void setCompte(int compte) {
		this.compte = compte;
	}
}
