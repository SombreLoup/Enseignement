package modele.plateau;


public class PlateauNombreDeplacementsLimite extends Plateau {

	private	int	nombreDeplacementsLimite;
	private	int	score;
	
	public PlateauNombreDeplacementsLimite(int largeur, int hauteur, int limiteDeplacement, int score) {
		super(largeur, hauteur);
		this.nombreDeplacementsLimite = limiteDeplacement;
		this.score = score;
	}

	@Override
	public boolean estTermine() {
		return (score <= getNombrePoints()) || (nombreDeplacementsLimite==getNombreDeplacement());
	}

	@Override
	public boolean objectifAtteint() {
		return (score <= getNombrePoints()) && (nombreDeplacementsLimite>=getNombreDeplacement());
	}
	
	@Override
	public String getDesciption() {
		return "Faire plus de "+score+" points en maximum "+nombreDeplacementsLimite+" déplacements";
	}
}
