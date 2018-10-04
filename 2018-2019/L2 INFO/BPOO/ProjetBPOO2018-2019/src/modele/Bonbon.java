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
	
	public void effet(Plateau plateau) {
		// Aucun effet par défaut
	}
}
