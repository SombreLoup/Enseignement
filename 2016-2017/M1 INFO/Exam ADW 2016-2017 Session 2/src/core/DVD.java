package core;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;

public class DVD implements Serializable {

	private	int		code = -1;
	private	String	titre = "";
	private	Date	dateAchat = null;
	
	private	ArrayList<Categorie>	listeCategories = new ArrayList<>();

	public DVD() {
	}
	public DVD(String titre, Date dateAchat, ArrayList<Categorie> listeCategories) {
		this.titre = titre;
		this.dateAchat = dateAchat;
		this.listeCategories = listeCategories;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	public ArrayList<Categorie> getListeCategories() {
		return listeCategories;
	}
	public void ajouterCategorie(Categorie categorie) {
		listeCategories.add(categorie);
	}	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DVD other = (DVD) obj;
		if (code != other.code)
			return false;
		if (dateAchat == null) {
			if (other.dateAchat != null)
				return false;
		} else if (!dateAchat.equals(other.dateAchat))
			return false;
		if (listeCategories == null) {
			if (other.listeCategories != null)
				return false;
		} else if (!listeCategories.equals(other.listeCategories))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DVD [code=" + code + ", titre=" + titre + ", dateAchat=" + dateAchat + ", listeCategories="
				+ listeCategories + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
