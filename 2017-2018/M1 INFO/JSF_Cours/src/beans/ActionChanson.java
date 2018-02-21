package beans;

import java.util.List;

import core.Chanson;
import dao.jpa.DAOChansonJPA;
import dao.jpa.DAOGenreJPA;

public class ActionChanson {
	
	private	Chanson	chanson = new Chanson();
	
	public String getTitre() {
		return chanson.getTitre();
	}

	public void setTitre(String titre) {
		chanson.setTitre(titre);
	}

	public void setGenre(int code) {
		chanson.setGenre(DAOGenreJPA.getInstance().get(code));
	}
	
	public int getGenre() {
		if (chanson.getGenre()==null)
			return -1;
		else	
			return chanson.getGenre().getCode();
	}
	
	public List<Chanson>	getChansons() {
		return DAOChansonJPA.getInstance().loadAll();
	}
	
	
	public String	enregistrer() {
		DAOChansonJPA.getInstance().save(chanson);
		return "ChansonEnregistrée";
		
	}
}
