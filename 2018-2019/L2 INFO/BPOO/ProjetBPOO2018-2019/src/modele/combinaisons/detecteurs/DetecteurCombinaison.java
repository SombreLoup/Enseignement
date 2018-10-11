package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;

public class DetecteurCombinaison {
	
	private	DetecteurCombinaison	suivant = null;
	
	protected DetecteurCombinaison(DetecteurCombinaison s) {
		suivant = s;
	}
	
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		return null;
	}
	
	private Combinaison detecterCombinaison(Bonbon b, int l, int c, Plateau p) {
		Combinaison combo = compareCombinaison(b, l, c, p);
		
		if ((combo==null) && (suivant!=null))
			return suivant.detecterCombinaison(b, l, c, p);
		
		return combo;
	}

	
	private static DetecteurCombinaison chaineDetecteur=null;
	
	private static Combinaison detecter(Bonbon b, int l, int c, Plateau p) {
		if (chaineDetecteur==null) {
			// Construction de la chaine-->Attention à l'ordre

			chaineDetecteur = new DetecteurAlignement3Horizontal(chaineDetecteur);
			chaineDetecteur = new DetecteurAlignement3Vertical(chaineDetecteur);
			chaineDetecteur = new DetecteurAlignement3VerticalRaye(chaineDetecteur);
			chaineDetecteur = new DetecteurAlignement3HorizontalRaye(chaineDetecteur);
			chaineDetecteur = new DetecteurAlignement4Horizontal(chaineDetecteur);
			chaineDetecteur = new DetecteurAlignement4Vertical(chaineDetecteur);
		}
		
		if (chaineDetecteur == null)
			return null;
		else
			return chaineDetecteur.detecterCombinaison(b, l, c, p);
	}

	public static Combinaison detecterCombinaison(Plateau plateau) {
		for (int l = 0; l < 10; l++) {
			for (int c = 0; c < 10; c++) {		
				Combinaison combo = detecter(plateau.getBonbon(l, c), l, c, plateau);
				if (combo != null)
					return combo;
			}
		}
		return null;
	}
	
	public static Combinaison detecterCombinaison(Plateau plateau, int l, int c) {
		Combinaison combo = detecter(plateau.getBonbon(l, c), l, c, plateau);
		if (combo != null)
			return combo;
		return null;
	}
	
}
