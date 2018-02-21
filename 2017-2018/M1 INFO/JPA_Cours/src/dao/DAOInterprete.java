package dao;

import java.util.List;

import core.Interprete;

public interface DAOInterprete {

	Interprete			get(int code);
	Interprete			get(String nom);
	void				save(Interprete interprete);
	List<Interprete>	loadAll();
	
}
