package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="chanson")
public class Chanson {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_chanson")
	private	int		code	= -1;
	
	@Column(name="titre_chanson")
	private	String	titre	= null;

	
	@OneToOne // Facultatif car c'est le mapping par d�faut
	@JoinColumn(name="code_genre") // Obligatoire car le nom du champ ne correspond pas � celui de l'attribut
	// Ici, la cl� �trang�re a �t� nomm�e GENRE_code_genre
	private	Genre	genre	= null;
	
	@ManyToOne
	@JoinColumn(name="code_int")
	private	Interprete	interprete = null;
	
	public Interprete getInterprete() {
		return interprete;
	}

	public void setInterprete(Interprete interprete) {
		this.interprete = interprete;
	}

	public Chanson() {
		super();
	}
	
	public Chanson(String titre, Genre genre) {
		super();
		this.titre = titre;
		this.genre = genre;
	}	
	
	public Chanson(String titre, Genre genre, Interprete interprete) {
		super();
		this.titre = titre;
		this.genre = genre;
		this.interprete = interprete;
		interprete.add(this);
	}	
	
	public Chanson(int code, String titre, Genre genre) {
		super();
		this.code = code;
		this.titre = titre;
		this.genre = genre;
	}	
	
	
	public Chanson(int code, String titre, Genre genre, Interprete interprete) {
		super();
		this.code = code;
		this.titre = titre;
		this.genre = genre;
		this.interprete = interprete;
	}	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ ((interprete == null) ? 0 : interprete.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chanson other = (Chanson) obj;
		if (code != other.code)
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (interprete == null) {
			if (other.interprete != null)
				return false;
		} else if (!interprete.equals(other.interprete))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "["+this.code+";"+this.titre+";"+this.genre+";"+this.interprete.getNom()+"]";
	}

	
	
}
