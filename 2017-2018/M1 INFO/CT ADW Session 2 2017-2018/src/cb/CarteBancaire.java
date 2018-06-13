package cb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartebancaire")
public class CarteBancaire {

	@Id
	@Column(name="id_cb")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private	int		id = -1;
	
	@Column(name="nom_cb")
	private	String	nomCarte;
	
	@Column(name="num_cb")
	private	String	numero;
	
	@Column(name="code_cb")
	private	String 	codeSecurite;
	
	@Column(name="titulaire_cb")
	private	String	titulaire;

	@Column(name="mois_cb")
	private	int moisValidite;

	@Column(name="annee_cb")
	private	int	anneeValidite;

	@ManyToOne
	@JoinColumn(name="num_cl")
	private	Client	client;
	
	public CarteBancaire() {
	}

	public CarteBancaire(String nomCarte, String numero, String codeSecurite, Client client, int moisValidite, int anneeValidite) {
		super();
		this.nomCarte = nomCarte;
		this.numero = numero;
		this.codeSecurite = codeSecurite;
		this.client = client;
		this.moisValidite = moisValidite;
		this.anneeValidite = anneeValidite;
	}

	public String getNomCarte() {
		return nomCarte;
	}

	public void setNomCarte(String nomCarte) {
		this.nomCarte = nomCarte;
	}

	public String getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(String titulaire) {
		this.titulaire = titulaire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodeSecurite() {
		return codeSecurite;
	}

	public void setCodeSecurite(String codeSecurite) {
		this.codeSecurite = codeSecurite;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getMoisValidite() {
		return moisValidite;
	}

	public void setMoisValidite(int moisValidite) {
		this.moisValidite = moisValidite;
	}

	public int getAnneeValidite() {
		return anneeValidite;
	}

	public void setAnneeValidite(int anneeValidite) {
		this.anneeValidite = anneeValidite;
	}

	@Override
	public String toString() {
		return "CarteBancaire [id=" + id + ", nom carte=" + nomCarte + ", numero=" + numero + ", codeSecurite=" + codeSecurite + ", client="
				+ (client==null? null : client.getNom()) + ", moisValidite=" + moisValidite + ", anneeValidite=" + anneeValidite + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anneeValidite;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((codeSecurite == null) ? 0 : codeSecurite.hashCode());
		result = prime * result + id;
		result = prime * result + moisValidite;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		CarteBancaire other = (CarteBancaire) obj;
		if (anneeValidite != other.anneeValidite)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (codeSecurite == null) {
			if (other.codeSecurite != null)
				return false;
		} else if (!codeSecurite.equals(other.codeSecurite))
			return false;
		if (id != other.id)
			return false;
		if (moisValidite != other.moisValidite)
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}
	
	
	
	
}
