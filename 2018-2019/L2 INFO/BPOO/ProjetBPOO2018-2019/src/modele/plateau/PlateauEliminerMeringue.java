package modele.plateau;

import modele.Sortes;

public class PlateauEliminerMeringue extends Plateau {

	private	int	nombreSecondesLimite;
	
	public PlateauEliminerMeringue(int largeur, int hauteur, int temps) {
		super(largeur, hauteur);
		this.nombreSecondesLimite = temps;

	}

	@Override
	public boolean estTermine() {
		return (nombreSecondesLimite<=getSecondesJouees()) || (plusDeMeringue());
	}

	@Override
	public boolean objectifAtteint() {
		return plusDeMeringue();
	}
	
	private boolean plusDeMeringue() {
		for (int l=0; l<grille.length; l++)
			for (int c=0; c<grille[l].length;c++)
				if (grille[l][c].getSorte().equals(Sortes.MERINGUE))
					return false;
		return true;
	}

	@Override
	public String getDesciption() {
		return "Eliminer toute la meringue en moins de "+nombreSecondesLimite+" secondes";
	}
}
