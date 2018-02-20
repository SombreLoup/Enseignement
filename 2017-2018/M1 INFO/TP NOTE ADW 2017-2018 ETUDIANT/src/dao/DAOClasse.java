package dao;

import java.util.List;

import core.Classe;

public interface DAOClasse {

	Classe get(int i);

	List<Classe> getToutesLesClasses();
	
	void update(Classe classe);

}
