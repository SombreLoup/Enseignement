package modele.plateau;


public class PlateauTempsLimite extends Plateau {

	private	int	nombreSecondesLimite;
	private	int	score;
	
	public PlateauTempsLimite(int largeur, int hauteur, int limiteDeplacement, int score) {
		super(largeur, hauteur);
		this.nombreSecondesLimite = limiteDeplacement;
		this.score = score;
	}

	@Override
	public boolean estTermine() {
		return (nombreSecondesLimite<=getSecondesJouees()) || (score <= getNombrePoints());
	}

	@Override
	public boolean objectifAtteint() {
		return (score <= getNombrePoints()) && (nombreSecondesLimite>=getSecondesJouees());
	}
	
	@Override
	public String getDesciption() {
		return "Faire plus de "+score+" points en moins de "+nombreSecondesLimite+" secondes";
	}
}
