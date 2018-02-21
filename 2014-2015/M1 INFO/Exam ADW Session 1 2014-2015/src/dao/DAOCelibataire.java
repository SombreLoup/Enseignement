package dao;

import java.util.List;

import core.Celibataire;

public interface DAOCelibataire {
	Celibataire	get(int code);
	void saveOrUpdate(Celibataire celibataire);
	void remove(Celibataire celibataire);
	List<Celibataire> load(String string);
}
