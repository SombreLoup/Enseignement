package bean;

import cb.CarteBancaire;
import cb.Client;
import dao.jpa.DAOClientJPA;

public class BeanCarte {
	
	private	CarteBancaire	carteBancaire = new CarteBancaire();
	private	Client			client = new Client();

	public BeanCarte() {
		super();
	}

	public CarteBancaire getCarteBancaire() {
		return carteBancaire;
	}

	public void setCarteBancaire(CarteBancaire carteBancaire) {
		this.carteBancaire = carteBancaire;
	}
	
	public String ajouter() {
		System.out.println(carteBancaire);
		client.add(carteBancaire);
		carteBancaire.setClient(client);
		DAOClientJPA.getInstance().update(client);
		return "AJOUT";
	}
	
	public int getCodeClient() {
		return client.getNumero();
	}
	
	public void setCodeClient(int codeClient) {
		client = DAOClientJPA.getInstance().get(codeClient);
		System.out.println("Client reçu : "+client);
		
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


}
