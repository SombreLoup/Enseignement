package cb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@Column(name="num_cl")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private	int		numero = -1;
	
	@Column(name="nom_cl")
	private	String	nom;

	@Column(name="prenom_cl")
	private	String	prenom;
	
	@OneToMany(mappedBy="client")
	private	List<Commande>	commandes = new ArrayList<Commande>();

	@OneToMany(mappedBy="client")
	private	List<CarteBancaire>	cartesBancaires = new ArrayList<CarteBancaire>();

	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public Client() {
		super();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<CarteBancaire> getCartesBancaires() {
		return cartesBancaires;
	}

	public void setCartesBancaires(List<CarteBancaire> cartesBancaires) {
		this.cartesBancaires = cartesBancaires;
	}

	public boolean add(Commande e) {
		return commandes.add(e);
	}
	
	public boolean add(CarteBancaire cb) {
		return cartesBancaires.add(cb);
	}
	
	@Override
	public String toString() {
		return "Client [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", commandes=" + commandes + ", cartes bancaires=" + cartesBancaires + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandes == null) ? 0 : commandes.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + numero;
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Client other = (Client) obj;
		if (commandes == null) {
			if (other.commandes != null)
				return false;
		} else if (!commandes.equals(other.commandes))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero != other.numero)
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
}
