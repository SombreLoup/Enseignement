package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison3VerticalRaye;
import modele.plateau.Plateau;

public class DetecteurAlignement3VerticalRaye extends DetecteurCombinaison {
	
	public DetecteurAlignement3VerticalRaye(DetecteurCombinaison d) {
		super(d);
	}
	
	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		DetecteurCombinaison detecteur3V = new DetecteurAlignement3Vertical(null);
		
		Combinaison combo = detecteur3V.compareCombinaison(b, l, c, p);
		if (combo != null) {
			if (contientRaye(combo, p))
				return new Combinaison3VerticalRaye(b, combo.getLdepart(), combo.getCdepart());
		}
		
		return null;
	}

	private boolean contientRaye(Combinaison combo, Plateau p) {
		int l = combo.getLdepart(), c = combo.getCdepart();
		return estRayé(l, c, p) || estRayé(l+1, c, p) || estRayé(l+2, c, p);
	}
	
	private boolean estRayé(int l, int c, Plateau p) {
		return p.getBonbon(l, c).getSorte().estRaye();
	}

}
