package modele;

public class Plateau {

	private	Bonbon [][]	grille = null;
	
	
	public Plateau(int largeur, int hauteur) {
		grille = new Bonbon[hauteur][largeur];
		System.out.println("grille.length = "+grille.length);
		System.out.println("grille[0].length = "+grille[0].length);
	}
	
	public void initPlateauAleatoire() {
		for (int l = 0; l < grille.length; l++) {
			for (int c = 0; c < grille[l].length; c++) {
				grille[l][c] = new Bonbon(getSorteAleatoire());
			}
		}
	}

	private Sortes getSorteAleatoire() {
		return Sortes.values()[(int)(Math.random()*(Sortes.values().length-1))+1];
	}

	public Bonbon getBonbon(int l, int c) {
		return grille[l][c];
	}
	
	public void eliminerCombos() {
		for (int l = 0; l < grille.length; l++) {
			for (int c = 0; c < grille[l].length; c++) {
				int longueurSuite = calculerLongueurSuite(l,c);
				
				if (longueurSuite>=3 && longueurSuite<=5) {
					viderCasesHorizontales(l, c, longueurSuite);
					return;
				}
				else if (longueurSuite<=-3 && longueurSuite>=-5) {
					viderCasesVerticales(l, c, -longueurSuite);	
					return;
				}					
			}
		}
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

	private void viderCasesVerticales(int l, int c, int n) {
		grille[l][c] = new Bonbon(Sortes.VIDE);
		grille[l+1][c] = new Bonbon(Sortes.VIDE);
		grille[l+2][c] = new Bonbon(Sortes.VIDE);
		if (n>=4)
			grille[l+3][c] = new Bonbon(Sortes.VIDE);
		if (n==5)
			grille[l+4][c] = new Bonbon(Sortes.VIDE);
	}

	private void viderCasesHorizontales(int l, int c, int n) {
		grille[l][c] = new Bonbon(Sortes.VIDE);
		grille[l][c+1] = new Bonbon(Sortes.VIDE);
		grille[l][c+2] = new Bonbon(Sortes.VIDE);
		if (n>=4)
			grille[l][c+3] = new Bonbon(Sortes.VIDE);
		if (n==5)
			grille[l][c+4] = new Bonbon(Sortes.VIDE);
	}

	private int calculerLongueurSuite(int ligne, int colonne) {
		int longueur = 1;
		int l = ligne;
		int c = colonne;
		
		Sortes sorteCourante = grille[l][c].getSorte();
		c++;
		while (c<grille[l].length)
			if (grille[l][c].getSorte().equals(sorteCourante)) {
				longueur++;
				c++;
			}
			else
				break;
		
		if (longueur>=3)
			return longueur;
		
		longueur = 1;
		c = colonne;
		l++;
		while (l<grille.length)
			if (grille[l][c].getSorte().equals(sorteCourante)) {
				longueur++;
				l++;
			}
			else
				break;
		
		return -longueur;
	}

	public void echanger(int ls, int cs, int lt, int ct) {
		Bonbon temp = grille[ls][cs];
		grille[ls][cs] = grille[lt][ct];
		grille[lt][ct] = temp;
	}
}
