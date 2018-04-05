package plat;


public class Menu {
	private	Plat	entree; // pas forc�ment de la cat�gorie des entr�e
	private	Plat	platPrincipal;
	private	Plat	dessert; // pas forc�ment un dessert (un formage par exemple)
	
	private	String	nom;
	private	double	prix;
	
	public Menu(String nom, Plat entree, Plat platPrincipal, Plat dessert) {
		setNom(nom);
		setEntree(entree);
		setPlatPrincipal(platPrincipal);
		setDessert(dessert);
		comparerCategories();
	}

	/**
	 * On compare les cat�gories que lorsque l'on sait que les trois plats
	 * ne sont pas null. 
	 */
	private void comparerCategories() {
		boolean erreurCategorie = false;
		erreurCategorie = erreurCategorie || (entree.getCategorie().equals(platPrincipal.getCategorie()));
		erreurCategorie = erreurCategorie || (dessert.getCategorie().equals(platPrincipal.getCategorie()));
		erreurCategorie = erreurCategorie || (entree.getCategorie().equals(dessert.getCategorie()));
		if (erreurCategorie) {
			System.out.println("Erreur de cat�gorie");
			System.exit(-1);
		}
	}

	public Plat getEntree() {
		return entree;
	}

	/**
	 * Ce plat ne doit pas �tre vide
	 * @param entree
	 */
	public void setEntree(Plat entree) {
		if (entree == null) {
			System.out.println("Param�tre ill�gal");
			System.exit(-1);
		}
		comparerCategories();
		this.entree = entree;
	}

	public Plat getPlatPrincipal() {
		return platPrincipal;
	}

	public void setPlatPrincipal(Plat platPrincipal) {
		if (platPrincipal == null) {
			System.out.println("Param�tre ill�gal");
			System.exit(-1);
		}
		comparerCategories();
		this.platPrincipal = platPrincipal;
	}

	public Plat getDessert() {
		return dessert;
	}

	public void setDessert(Plat dessert) {
		if (dessert == null) {
			System.out.println("Param�tre ill�gal");
			System.exit(-1);
		}
		comparerCategories();
		this.dessert = dessert;
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

	public double getPrix() {
		return prix;
	}
	
	/**
	 * Priv�e car elle ne sert qu'� v�rifier la contrainte du prix
	 * @return Somme des prix des plats
	 */
	private double getPrixALaCarte() {
		return entree.getPrixVente()+platPrincipal.getPrixVente()+dessert.getPrixVente();
	}

	public void setPrix(double prix) {
		if ((prix<0) || (prix*1.1<getPrixALaCarte() && getPrixALaCarte()<prix*1.2) ) {
			System.out.println("prix incorrect");
			System.exit(-1);
		}
		this.prix = prix;
	}
	
	
}
