package dao.jpa;

import core.Rencontre;
import dao.DAORencontre;

public class DAORencontreJPA extends DAOJPA implements DAORencontre {
	static private	DAORencontreJPA	instance = null;
	
	private  DAORencontreJPA() {
	}
	
	static public DAORencontre getInstance() {
		if (instance==null)
			instance = new DAORencontreJPA();
		return instance;
	}

	@Override
	public Rencontre get(int code) {
		Rencontre rencontre = DAOJPA.getManager().find(Rencontre.class, code);
		return rencontre;
	}

	@Override
	public void saveOrUpdate(Rencontre rencontre) {
		DAOJPA.getManager().persist(rencontre);
		DAOJPA.commit();
	}

	@Override
	public void remove(Rencontre rencontre) {
		Rencontre rencontreToRemove = DAOJPA.getManager().merge(rencontre);
		DAOJPA.getManager().remove(rencontreToRemove);
		DAOJPA.commit();
		rencontre.setCode(-1);
	}

}
