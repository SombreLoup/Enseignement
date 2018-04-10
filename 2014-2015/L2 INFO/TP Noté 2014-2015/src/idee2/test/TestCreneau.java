package idee2.test;

import static org.junit.Assert.*;

import java.text.ParseException;

import idee2.rdv.Creneau;

import org.junit.Test;

public class TestCreneau {

	@Test
	public void test() {
		try {
			Creneau c1 = new Creneau("23/12/2014");
			c1.setHeureDebut("08:00");
			c1.setHeureFin("10:00");
			System.out.println("c1="+c1);
			
			Creneau c2 = new Creneau("23/12/2014");
			c2.setHeureDebut("9:00");
			c2.setHeureFin("09:30");
			System.out.println("c2="+c2);
			
			assertTrue(c2.chevauche(c1));
			
			Creneau c3 = new Creneau("23/12/2014");
			c3.setHeureDebut("07:00");
			c3.setHeureFin("13:00");
			System.out.println("c3="+c3);

			assertTrue(c3.chevauche(c1));
			
			Creneau c4 = new Creneau("23/12/2014");
			c4.setHeureDebut("12:00");
			c4.setHeureFin("14:00");
			System.out.println("c4="+c4);

			assertFalse(c4.chevauche(c1));

			
			Creneau c5 = new Creneau("22/12/2014");
			c5.setHeureDebut("08:00");
			c5.setHeureFin("10:00");
			System.out.println("c5="+c5);
			
			assertFalse(c5.chevauche(c1));
			
		} catch (ParseException e) {
			e.printStackTrace();
			fail();
		}
	}

}
