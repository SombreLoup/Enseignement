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
			emf = Persistence.createEntityManagerFactory("TP_NOTE_2018");
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
		em.flush();
		em.clear();
		tx.commit();
	}
	
	public static void rollback() {
		tx.rollback();
	}
	
	public static void close() {
		em.close();	em = null;
		emf.close();emf = null;
	}
	
	public static void viderBase() {
		getManager().createNativeQuery("DELETE FROM contient").executeUpdate();

		getManager().createQuery("DELETE FROM Article").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Article AUTO_INCREMENT = 1").executeUpdate();

		getManager().createQuery("DELETE FROM Page").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Page AUTO_INCREMENT = 1").executeUpdate();

		getManager().createQuery("DELETE FROM Auteur").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE Auteur AUTO_INCREMENT = 1").executeUpdate();
	}
	

}
