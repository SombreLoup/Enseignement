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
			emf = Persistence.createEntityManagerFactory("GestionAlbum");
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
		getManager().createQuery("DELETE FROM Chanson").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE chanson AUTO_INCREMENT = 1").executeUpdate();

		getManager().createQuery("DELETE FROM Album").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE album AUTO_INCREMENT = 1").executeUpdate();

		getManager().createQuery("DELETE FROM Interprete").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE interprete AUTO_INCREMENT = 1").executeUpdate();

		getManager().createQuery("DELETE FROM Genre").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE genre AUTO_INCREMENT = 1").executeUpdate();

		getManager().createQuery("DELETE FROM Concert").executeUpdate();
		getManager().createNativeQuery("ALTER TABLE concert AUTO_INCREMENT = 1").executeUpdate();
	}
	

}
