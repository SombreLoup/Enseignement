
/**
 * J'ai développé cette classe de manière assez sophistiquée. Dans la copie,
 * une simple exception avec un message suffisait.
 * @author yann
 *
 */
public class EpreuveException extends Exception {
	
	private	double	nombrePoints;
	private	double	bareme;

	public EpreuveException(double nombrePoints, double bareme) {
		this.nombrePoints = nombrePoints;
		this.bareme = bareme;
	}

	public double getNombrePoints() {
		return nombrePoints;
	}

	public double getBareme() {
		return bareme;
	}

	public String toString() {
		return "Dépassement du seuil de 20 points (points="+nombrePoints+";"+"bareme de la question="+bareme+")";
	}
	
	

}
