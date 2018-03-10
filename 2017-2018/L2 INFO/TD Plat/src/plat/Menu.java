package plat;


public class Menu {
	private	Plat	entree; // pas forcément de la catégorie des entrée
	private	Plat	platPrincipal;
	private	Plat	dessert; // pas forcément un dessert (un formage par exemple)
	
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
	 * On compare les catégories que lorsque l'on sait que les trois plats
	 * ne sont pas null. 
	 */
	private void comparerCategories() {
		boolean erreurCategorie = false;
		erreurCategorie = erreurCategorie || (entree.getCategorie().equals(platPrincipal.getCategorie()));
		erreurCategorie = erreurCategorie || (dessert.getCategorie().equals(platPrincipal.getCategorie()));
		erreurCategorie = erreurCategorie || (entree.getCategorie().equals(dessert.getCategorie()));
		if (erreurCategorie) {
			System.out.println("Erreur de catégorie");
			System.exit(-1);
		}
	}

	public Plat getEntree() {
		return entree;
	}

	/**
	 * Ce plat ne doit pas être vide
	 * @param entree
	 */
	public void setEntree(Plat entree) {
		if (entree == null) {
			System.out.println("Paramètre illégal");
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
			System.out.println("Paramètre illégal");
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
			System.out.println("Paramètre illégal");
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
	 * le cas général, une fois le nom fixé, on en change plus.
	 * @param nom
	 */
	private void setNom(String nom) {
		if ((nom==null) || (nom.trim().equals(""))) {
			System.out.println("Argument illégal");
			System.exit(-1);
		}
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}
	
	/**
	 * Privée car elle ne sert qu'à vérifier la contrainte du prix
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
