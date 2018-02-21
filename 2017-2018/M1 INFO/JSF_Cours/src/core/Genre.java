package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="genre")
public class Genre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_genre")
	private	int		code		= -1;

	@Column(name = "lib_genre")
	private	String	libelle		= null;
	
	
	public Genre() {
		super();
	}


	public Genre(String libelle) {
		super();
		this.libelle = libelle;
	}


	public Genre(int code, String libelle) {
		super();
		this.code = code;
		this.libelle = libelle;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public boolean equals(Object arg0) {
		boolean egal = false;
		
		if (arg0 instanceof Genre) {
			Genre genre = (Genre) arg0;
			egal = (this.code==genre.code);
		}
		
		return egal;
	}


	@Override
	public String toString() {
		return "["+this.code+";"+this.libelle+"]";
	}
}
