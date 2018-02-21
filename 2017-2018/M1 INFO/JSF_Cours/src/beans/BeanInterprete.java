package beans;

import java.io.Serializable;
import java.util.List;

import core.Interprete;
import dao.jpa.DAOInterpreteJPA;

public class BeanInterprete implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 410717373831919568L;
	
	private Interprete	interprete = new Interprete();
	
	public BeanInterprete() {
	}
	

	public Interprete getInterprete() {
		return interprete;
	}

	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}

	public List<Interprete> getInterpretes() {
		return DAOInterpreteJPA.getInstance().loadAll();
	}
	
	public String	enregistrer() {
		DAOInterpreteJPA.getInstance().save(interprete);
		return "InterpreteEnregistre";
	}
	
}
