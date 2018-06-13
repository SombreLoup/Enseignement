package bean;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cb.CarteBancaire;
import cb.Client;
import cb.Commande;
import dao.jpa.DAOCommandeJPA;

public class BeanCommande {
	
	private	Commande	commandeCourante;

	public BeanCommande() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = format.parse("24/12/2018");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Client client = new Client("LANUEL", "Yann");
		client.setNumero(2345);
		commandeCourante = new Commande(date, 1000.0, client);
		
		CarteBancaire carteBancaire = new CarteBancaire("Visa compte perso", "1234567890123456", "999", client, 10, 2020);
		client.add(carteBancaire);
		
		CarteBancaire carteBancaire2 = new CarteBancaire("Master card du compte à Monaco", "1234567890123456", "999", client, 12, 2022);
		client.add(carteBancaire2);
	}

	public Commande getCommandeCourante() {
		return commandeCourante;
	}

	public void setCommandeCourante(Commande commandeCourante) {
		this.commandeCourante = commandeCourante;
	}
	
	public void setCodeCommande(int numCde) {
		commandeCourante = DAOCommandeJPA.getInstance().get(numCde);
	}
	
	public int getCodeCommande() {
		if (commandeCourante==null)
			return -1;
		else
			return commandeCourante.getNumero();
	}

}
