package dao.jpa;

import java.util.List;

import core.Chanson;
import dao.DAOChanson;

public class DAOChansonJPA extends DAOJPA implements DAOChanson {

	static private	DAOChansonJPA	instance = null;
	
	private  DAOChansonJPA() {
	}
	
	static public DAOChanson getInstance() {
		if (instance==null)
			instance = new DAOChansonJPA();
		return instance;
	}
	
	@Override
	public Chanson get(int code) {
		Chanson chanson = DAOJPA.getManager().find(Chanson.class, code);
		return chanson;
	}

	@Override
	public void save(Chanson chanson) {
		DAOJPA.getManager().persist(chanson);
		DAOJPA.commit();
	}

	@Override
	public List<Chanson> loadAll() {
		return DAOJPA.getManager().createQuery("SELECT c FROM Chanson c",Chanson.class).getResultList();
	}

	@Override
	public List<Chanson> laodChansonSansAlbum() {
		return DAOJPA.getManager().createQuery("SELECT c FROM Chanson c WHERE c.code_alb=-1",Chanson.class).getResultList();
	}	
}
