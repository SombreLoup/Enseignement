package dao.jpa.test;



import static org.junit.Assert.assertEquals;

import java.sql.Date;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import cb.Client;
import cb.Commande;
import dao.DAOClient;
import dao.jpa.DAOClientJPA;


class TestDAO {

	@Test
	void testConnection() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CT ADW SESSION 2 2017-2018");
		emf.createEntityManager();
	}


	@Test
	void testGetClient() {
		DAOClient	daoClient = DAOClientJPA.getInstance();
		Client client = daoClient.get(1);
		assertEquals("LANUEL", client.getNom());
		System.out.println(client);
	}

	@Test
	void testUpdateClient() {
		DAOClient	daoClient = DAOClientJPA.getInstance();
		Client client = daoClient.get(1);
		
		int nbCde = client.getCommandes().size();
		System.out.println("Nb avant = "+nbCde);
		
		Date date = Date.valueOf( "2010-01-31" );
		Commande cde = new Commande(date,1000,client);
		client.add(cde);
		
		daoClient.update(client);
		
		Client lememe = daoClient.get(1);
		System.out.println("Nb après = "+lememe.getCommandes().size());
		nbCde++;
		assertEquals(nbCde, lememe.getCommandes().size());
		
	}
}
