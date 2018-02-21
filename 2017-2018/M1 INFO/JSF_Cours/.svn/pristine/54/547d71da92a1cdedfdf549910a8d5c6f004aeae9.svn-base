package beans;

import java.util.List;

import core.Genre;
import dao.jpa.DAOGenreJPA;

public class ActionGenre {
	public List<Genre> getGenres() {
		return DAOGenreJPA.getInstance().loadAll();
	}
}
