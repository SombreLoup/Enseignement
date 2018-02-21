package dao.jpa;

import java.util.List;

import core.Album;
import dao.DAOAlbum;


public class DAOAlbumJPA extends DAOJPA implements DAOAlbum {
	static private	DAOAlbumJPA	instance = null;
	
	private  DAOAlbumJPA() {
	}
	
	static public DAOAlbum getInstance() {
		if (instance==null)
			instance = new DAOAlbumJPA();
		return instance;
	}
	
	
	@Override
	public Album get(int code) {
		Album album = DAOJPA.getManager().find(Album.class, code);
		return album;
	}

	@Override
	public void save(Album album) {
		DAOJPA.getManager().persist(album);
		DAOJPA.commit();
	}

	@Override
	public List<Album> loadAll() {
		return DAOJPA.getManager().createQuery("SELECT a FROM Album a",Album.class).getResultList();
	}

	@Override
	public void remove(Album album) { // A tous les coups, comme on commit le save, l'album est d�tach�. D'o� le merge pour retrouver une copie attach�e qui peut alors �tre supprim�e de la base
		Album albumToRemove = DAOJPA.getManager().merge(album);
		DAOJPA.getManager().remove(albumToRemove);
		DAOJPA.commit();
		album.setCode(-1);
	}

}
