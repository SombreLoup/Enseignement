package modele.combinaisons;

import modele.Bonbon;
import modele.Plateau;
import modele.Sortes;

public class Combinaison3HorizontalRaye extends Combinaison {

	public Combinaison3HorizontalRaye(Bonbon b, int l, int c) {
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
		for (int c=0; c<10; c++)
			p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), c);
	}
	
	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getLdepart()==l) && (getCdepart()<=c) && (c<=getCdepart()+2) ;
	}

}
