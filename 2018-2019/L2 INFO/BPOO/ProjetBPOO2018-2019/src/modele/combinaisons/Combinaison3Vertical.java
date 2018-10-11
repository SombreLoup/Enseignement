package modele.combinaisons;

import modele.Bonbon;
import modele.Plateau;
import modele.Sortes;

public class Combinaison3Vertical extends Combinaison {

	public Combinaison3Vertical(Bonbon b, int l, int c) {
		super(b, l, c);
		setNombrePoints(30);
	}

	@Override
	public Bonbon getBonbonSpecial() {
		return new Bonbon(Sortes.VIDE);
	}

	@Override
	public void viderCombinaison(Plateau p) {
		comptabiliserNombrePoints(p);

		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+1, getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart()+2, getCdepart());
	}
	
	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getCdepart()==c) && (getLdepart()<=l) && (l<=getLdepart()+2) ;
	}

}
