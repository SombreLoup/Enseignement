package dao;

import java.util.List;

import core.Album;

public interface DAOAlbum {
	Album		get(int code);
	void		save(Album album);
	void		remove(Album album);
	List<Album>	loadAll();
}
