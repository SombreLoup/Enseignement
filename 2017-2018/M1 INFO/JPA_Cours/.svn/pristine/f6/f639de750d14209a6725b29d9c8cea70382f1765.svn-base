package core;

import java.util.ArrayList;
import java.util.List;






import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="album")
public class Album {
	
	private	int				code		= -1;
	private	String			titre		= null;
	private	int				annee		= 0;
	private	List<Chanson>	chansons	= new ArrayList<Chanson>();
	
	public Album() {
		super();
	}

	public Album(String titre, int annee) {
		super();
		this.titre = titre;
		this.annee = annee;
	}

	public Album(int code, String titre, int annee) {
		super();
		this.code = code;
		this.titre = titre;
		this.annee = annee;
	}

	@Id
	@Column(name="code_album")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Column(name="titre_album")
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Column(name="annee_album")
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="code_album")
	public List<Chanson> getChansons() {
		return chansons;
	}

	public void setChansons(List<Chanson> chansons) {
		this.chansons = chansons;
	}

	public boolean add(Chanson e) {
		return chansons.add(e);
	}

	@Override
	public boolean equals(Object obj) {
		boolean egal = false;
		
		if (obj instanceof Album) {
			Album album = (Album) obj;
			egal = (this.code==album.code);
		}
		return egal;
	}

	@Override
	public String toString() {
		return "["+this.code+";"+this.titre+";"+this.annee+" {"+this.chansons+"}]";
	}

	
	
}
