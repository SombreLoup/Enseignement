package modele.combinaisons.detecteurs;

import modele.Bonbon;
import modele.Plateau;
import modele.combinaisons.Combinaison;
import modele.combinaisons.Combinaison3HorizontalRaye;

public class DetecteurAlignement3HorizontalRaye extends DetecteurCombinaison {
	
	public DetecteurAlignement3HorizontalRaye(DetecteurCombinaison d) {
		super(d);
	}

	
	@Override
	protected Combinaison compareCombinaison(Bonbon b, int l, int c, Plateau p) {
		DetecteurCombinaison detecteur3H = new DetecteurAlignement3Horizontal(null);
		
		Combinaison combo = detecteur3H.compareCombinaison(b, l, c, p);
		if (combo != null) {
			if (contientRaye(combo, p))
				return new Combinaison3HorizontalRaye(b, combo.getLdepart(), combo.getCdepart());
		}
		
		return null;
	}


	private boolean contientRaye(Combinaison combo, Plateau p) {
		int l = combo.getLdepart(), c = combo.getCdepart();
		return estRayé(l, c, p) || estRayé(l, c+1, p) || estRayé(l, c+2, p);
	}

	private boolean estRayé(int l, int c, Plateau p) {
		return p.getBonbon(l, c).getSorte().estRaye();
	}


}
