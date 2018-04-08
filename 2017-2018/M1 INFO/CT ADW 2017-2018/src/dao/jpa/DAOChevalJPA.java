package dao.jpa;

import java.util.List;

import javax.persistence.Query;

import core.Cheval;
import core.Course;

public class DAOChevalJPA extends DAOJPA{

private	static DAOChevalJPA		instance = null;
	
	public static DAOChevalJPA getInstance() {
		if (instance==null)
			instance = new DAOChevalJPA();
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cheval>	getChevaux() {
		Query query = getManager().createQuery("SELECT cheval FROM Cheval cheval");
		return query.getResultList();
	}
	
}
