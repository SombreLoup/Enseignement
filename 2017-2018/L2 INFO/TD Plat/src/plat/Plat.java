package plat;

/**
 * Tous les contr�les sont faits mais comme on a pas encore fait les exceptions
 * on affiche un message et on quitte le programme
 * @author yann
 *
 */
public class Plat {
	
	private	String			nom;
	private	double			prixVente;
	private	double			cout;
	private boolean			vegetarien;
	private	CategoriePlat	categorie;
	
	public Plat(String nom, CategoriePlat categorie) {
		/**
		 * On utilise les setters car cela permet de factoriser les contr�les
		 * que l'on doit faire sur les param�tres
		 */
		setNom(nom);
		setCategorie(categorie);
		setPrixVente(0);
		setCout(0);
		setVegetarien(false);
	}

	public Plat(String nom, double prixVente, double cout, boolean vegetarien, CategoriePlat categorie) {
		setNom(nom);
		setCategorie(categorie);
		setPrixVente(prixVente);
		setCout(cout);
		setVegetarien(vegetarien);
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		if (prixVente<0) {
			System.out.println("prix incorrect");
			System.exit(-1);
		}
		this.prixVente = prixVente;
	}

	public double getCout() {
		return cout;
	}

	public void setCout(double cout) {
		if (cout<0) {
			System.out.println("cout incorrect");
			System.exit(-1);
		}
		this.cout = cout;
	}

	public boolean isVegetarien() {
		return vegetarien;
	}

	public void setVegetarien(boolean vegetarien) {
		this.vegetarien = vegetarien;
	}

	public CategoriePlat getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePlat categorie) {
		// C'est ici que la relation bidirectionnelle est gérée en détail
		if (categorie==null) {
			System.out.println("Argument ill�gal");
			System.exit(-1);
		}
		
		if (this.categorie==null) { // Correspond à l'appel effectué dans le constructeur
			categorie.add(this);
			this.categorie = categorie;
		} 
		else {
			// ici, c'est un changement de catégorie. Le principe consiste à annuler
			// la catégprie du plat, puis de faire comme si c'était une construction
			// avec la nouvelle catégorie.
			this.categorie.remove(this);
			this.categorie = null;
			categorie.add(this);
			this.categorie = categorie;
		}
	}

	public String getNom() {
		return nom;
	}
	
	/**
	 * private car il ne doit servir que dans le constructeur. Dans
	 * le cas g�n�ral, une fois le nom fix�, on en change plus.
	 * @param nom
	 */
	private void setNom(String nom) {
		if ((nom==null) || (nom.trim().equals(""))) {
			System.out.println("Argument ill�gal");
			System.exit(-1);
		}
		this.nom = nom;
	}

	/**
	 * equals g�n�r�e par Eclipse. Les �tudiants doivent le faire � la main
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plat other = (Plat) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (Double.doubleToLongBits(cout) != Double
				.doubleToLongBits(other.cout))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(prixVente) != Double
				.doubleToLongBits(other.prixVente))
			return false;
		if (vegetarien != other.vegetarien)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Plat [nom=" + nom + ", prixVente=" + prixVente + ", cout="
				+ cout + ", vegetarien=" + vegetarien + ", categorie="
				+ categorie.getNom() + "]";
	}

	
}
