package dao;

import java.util.List;

import core.Auteur;

public interface DAOAuteur {
	Auteur	get(int code);
	void	save(Auteur auteur);
	void	remove(Auteur auteur);
	int		getNombreAuteurs();
	List<Auteur>	loadAll();
}
