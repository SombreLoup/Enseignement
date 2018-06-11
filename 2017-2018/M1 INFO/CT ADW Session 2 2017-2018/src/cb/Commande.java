package cb;

import java.sql.Date;

public class Commande {
	
	private	int		numero = -1;
	private	Date		date;
	private	double	montant;
	
	private	Client	client;
	private	CarteBancaire	carteBancaire;
	

	public Commande() {
		super();
	}


	public Commande(Date date, double montant, Client client) {
		super();
		this.date = date;
		this.montant = montant;
		this.client = client;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public CarteBancaire getCarteBancaire() {
		return carteBancaire;
	}


	public void setCarteBancaire(CarteBancaire carteBancaire) {
		this.carteBancaire = carteBancaire;
	}


	@Override
	public String toString() {
		return "Commande [numero=" + numero + ", date=" + date + ", montant=" + montant + ", client=" + client
				+ ", carteBancaire=" + carteBancaire + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carteBancaire == null) ? 0 : carteBancaire.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		long temp;
		temp = Double.doubleToLongBits(montant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numero;
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
		Commande other = (Commande) obj;
		if (carteBancaire == null) {
			if (other.carteBancaire != null)
				return false;
		} else if (!carteBancaire.equals(other.carteBancaire))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(montant) != Double.doubleToLongBits(other.montant))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	
	

}