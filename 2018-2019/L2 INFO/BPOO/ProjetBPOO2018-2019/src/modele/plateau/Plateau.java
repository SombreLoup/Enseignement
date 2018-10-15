package modele.plateau;


import modele.Bonbon;
import modele.CandyException;
import modele.Sortes;
import modele.combinaisons.Combinaison;
import modele.combinaisons.detecteurs.DetecteurCombinaison;

public class Plateau {

	protected	Bonbon [][]	grille = null;
	private	int			nombreDeplacement = 0;
	private int 		nombrePoints = 0;
	private	int			secondesJouees = 0;
	
	
	public Plateau(int largeur, int hauteur) {
		grille = new Bonbon[hauteur][largeur];
	}
	
	
	public void initPlateauAleatoire() {
		
		for (int l = 0; l < grille.length; l++) {
			for (int c = 0; c < grille[l].length; c++) {
				grille[l][c] = new Bonbon(getSorteAleatoire());
			}
		}
		
		eliminerToutesLesCombinaisons();
	}

	public int getNombreDeplacement() {
		return nombreDeplacement;
	}

	public int getNombrePoints() {
		return nombrePoints;
	}

	public Bonbon getBonbon(int l, int c) {
		return grille[l][c];
	}
	
	public void setBonbon(Bonbon b, int l, int c) {
		grille[l][c] = b;
	}
	
	public void placerBonbon(Bonbon bonbon, int l, int c) {
		if (grille[l][c].getSorte() != Sortes.VIDE) 
			return;
		grille[l][c] = bonbon;
	}	
	
	public void initialiserBonbon(Bonbon bonbon, int l, int c) {
		grille[l][c] = bonbon;
	}
	
	
	public void eliminerCasesVides() {
		for (int c = 0; c < grille[0].length; c++) {
			for (int l = grille.length-1; l>=0; l--) {
				while (grille[l][c].getSorte().equals(Sortes.VIDE)) {
					for (int i=l; i>0; i--)
						grille[i][c] = grille[i-1][c];
					grille[0][c] = new Bonbon(getSorteAleatoire());
				}
			}
		}
	}
	
	public void debugConsole() {
		for (int l = 0; l < grille.length; l++) {
			for (int c = 0; c < grille[l].length; c++) {
				System.out.print(grille[l][c].getSorte().ordinal()+" ");
			}
			System.out.println();
		}
		System.out.println();
	}


	public void echanger(int ls, int cs, int lt, int ct) throws CandyException {
		verifierLegaliteEchange(ls, cs, lt, ct);
		
		Bonbon temp = grille[ls][cs];
		grille[ls][cs] = grille[lt][ct];
		grille[lt][ct] = temp;
	}

	private void eliminerToutesLesCombinaisons() {
		Combinaison combo = DetecteurCombinaison.detecterCombinaison(this);
		while (combo != null) {
			combo.viderCombinaison(this);
			eliminerCasesVides();
			combo = DetecteurCombinaison.detecterCombinaison(this);
		}
	}
	
	public void incrementerDeplacements() {
		nombreDeplacement++;
	}
	
	public void comptabiliser(int nb) {
		nombrePoints += nb;
	}

	private Sortes getSorteAleatoire() {
		return Sortes.values()[(int)(Math.random()*4)+1];
	}

	private void verifierLegaliteEchange(int ls, int cs, int lt, int ct) throws CandyException {
		if (grille[ls][cs].getSorte().equals(Sortes.MERINGUE) || grille[lt][ct].getSorte().equals(Sortes.MERINGUE))
			throw new CandyException("Pas possible de bouger la meringue");
		if ((lt==ls) && ((ct>=cs-1) || (ct<=cs+1))) // pas plus d'une case sur la même ligne
			return;
		if ((ct==cs) && ((lt>=ls-1) || (lt<=ls+1))) // Pas plus d'une case sur la même colonne
			return;
		
		// Dans tous les autres cas, c'est une exception
		throw new CandyException("Impossible d'échanger avec un bonbon qui n'est pas situé dans le voisinnage direct");
	}
	
	public boolean estTermine() {
		// A redéfinir pour éviter un plateau interminable
		return false;
	}

	public boolean objectifAtteint() {
		// A redéfinir pour éviter un plateau interminable
		return false;
	}
	
	public String getDesciption() {
		// A redéfinir pour personnaliser la description
		return "Faire un max de points en jouant à l'infini !";
	}


	public int getSecondesJouees() {
		return secondesJouees;
	}


	public void setSecondesJouees(int secondesJouees) {
		this.secondesJouees = secondesJouees;
	}
}
