package modele.combinaisons;

import modele.Bonbon;
import modele.Sortes;
import modele.plateau.Plateau;

public class Combinaison3Horizontal extends Combinaison {

	public Combinaison3Horizontal(Bonbon b, int l, int c) {
		super(b, l, c);
		setNombrePoints(30);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bonbon getBonbonSpecial() {
		return new Bonbon(Sortes.VIDE);
	}

	@Override
	public void viderCombinaison(Plateau p) {
		comptabiliserNombrePoints(p);
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart());
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart()+1);
		p.setBonbon(new Bonbon(Sortes.VIDE), getLdepart(), getCdepart()+2);
	}

	@Override
	public boolean contient(Plateau p, int l, int c) {
		return (getLdepart()==l) && (getCdepart()<=c) && (c<=getCdepart()+2) ;
	}

}
