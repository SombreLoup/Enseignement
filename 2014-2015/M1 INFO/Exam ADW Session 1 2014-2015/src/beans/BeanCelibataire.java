package beans;

import java.util.List;

import core.Celibataire;
import dao.jpa.DAOCelibataireJPA;

public class BeanCelibataire {
	public List<Celibataire>	getCelibataires() {
		return DAOCelibataireJPA.getInstance().load("");
	}
}
