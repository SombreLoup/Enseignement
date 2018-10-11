package modele;

public class Bonbon {
	private	Sortes sorte;
	
	public Bonbon(Sortes s) {
		this.setSorte(s);
	}

	public Sortes getSorte() {
		return sorte;
	}

	private void setSorte(Sortes sorte) {
		this.sorte = sorte;
	}
	
	public boolean estMemeCouleur(Bonbon b) {
		return Sortes.estMemeCouleur(this.getSorte(), b.getSorte());
	}
	
	public boolean estMemeCouleur(Bonbon b1, Bonbon b2) {
		return estMemeCouleur(b1) && estMemeCouleur(b2);
	}
	
	public boolean estMemeCouleur(Bonbon b1, Bonbon b2, Bonbon b3) {
		return estMemeCouleur(b1,b2) && estMemeCouleur(b3);
	}	
	
	public boolean estMemeCouleur(Bonbon b1, Bonbon b2, Bonbon b3, Bonbon b4) {
		return estMemeCouleur(b1,b2,b3) && estMemeCouleur(b4);
	}
}
