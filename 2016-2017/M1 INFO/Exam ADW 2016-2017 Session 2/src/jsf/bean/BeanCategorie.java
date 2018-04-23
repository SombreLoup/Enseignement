package jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;

import core.Categorie;
import core.DVD;

public class BeanCategorie implements Serializable {

	public ArrayList<Categorie> getListeCategories() {
		ArrayList<Categorie> l = new ArrayList<>();
		l.add(new Categorie("Sciences Fiction"));
		l.add(new Categorie("Comédie"));
		return l;
	}
	
	private DVD dvd = new DVD();

	public DVD getDvd() {
		return dvd;
	}

	public void setDvd(DVD dvd) {
		this.dvd = dvd;
	}
	
	public String enregistrer()  {
		return "AJOUT_EFFECTUE";
	}
	
	
	public void raz()  {
		dvd = new DVD();
	}
	
	private	int	codeCategorie;

	public int getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(int codeCategorie) {
		this.codeCategorie = codeCategorie;
		
		dvd.ajouterCategorie(new Categorie("ajout"));
	}
	
	
	
	
}
