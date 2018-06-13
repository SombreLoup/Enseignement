package cb.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cb.CarteBancaire;
import cb.Client;

class TestCB {

	@Test
	void test() {
		Client	yann = new Client("LANUEL", "Yann");
		System.out.println("Yann : "+yann);
		
		CarteBancaire cb1Yann = new CarteBancaire("Ma carte Visa","1234567891234567", "547", yann, 12, 2020);
		System.out.println("CB Yann : "+cb1Yann);
		
		yann.add(cb1Yann);
		System.out.println("Yann : "+yann);
	}

}
