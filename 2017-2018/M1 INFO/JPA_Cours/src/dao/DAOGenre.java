package dao;

import java.util.List;

import core.Genre;

public interface DAOGenre {
	Genre	get(int code);
	Genre 	get(String libelle);
	void	save(Genre genre);
	List<Genre>	loadAll();
	int getNombreGenre();
}
