package application;

import starcraft.Batiment;
import starcraft.MortUniteException;
import starcraft.UniteInterditeException;
import unite.Marauder;
import unite.Marine;

public class Main {

	public static void main(String[] args) {
		Batiment b1 = new Batiment("Caserne");
		
		b1.add(new Marine());
		
		Marine s1 = null, s2 = null;
		try {
			s1 = (Marine)b1.produire(Marine.class);
			s2 = (Marine)b1.produire(Marine.class);
		} catch ( UniteInterditeException e ) {
			System.out.println("La caserne ne peut pas produire de marine !");
		}
		
		b1.add(new Marauder());
		
		Marauder m1 = null;
		try {
			m1 = (Marauder)b1.produire(Marauder.class);
		} catch ( UniteInterditeException e ) {
			System.out.println("La caserne ne peut pas produire de maraudeur !");
		}
		
		while ( s1.getPointDeVieCourant() > 0 ) {
			try {
				m1.attaquer(s1);
				s2.attaquer(m1);
			} catch ( MortUniteException e ) {
				System.out.println(e.getMessage());
			}
		}
	}

}
