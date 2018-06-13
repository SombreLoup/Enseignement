package dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DAOJPA {
	private static EntityManagerFactory emf = null;
	public static EntityManager em = null;
	public static EntityTransaction tx = null;
	
	public static EntityManager	getManager() {
		if (em == null) {
			emf = Persistence.createEntityManagerFactory("CT ADW SESSION 2 2017-2018");
			em = emf.createEntityManager();
			tx = em.getTransaction();
		}
		
		if (!tx.isActive()) {
			tx.begin();
		}
		
		return em;
	}

	public static void begin() {
		if (!tx.isActive()) {
			tx.begin();
		}
	}
	
	public static void commit() {
		if (tx.isActive()) {
				em.flush();
				em.clear();
				tx.commit();
		}
		else
			System.out.println("Transaction non  active");
	}
	
	public static void rollback() {
		tx.rollback();
	}
	
	public static void close() {
		em.close();	em = null;
		emf.close();emf = null;
	}
	
	public static void viderBase() {
	}

	

}
