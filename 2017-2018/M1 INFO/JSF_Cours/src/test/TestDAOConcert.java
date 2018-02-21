package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import core.Concert;
import core.Interprete;
import dao.DAOInterprete;
import dao.jpa.DAOConcertJPA;
import dao.jpa.DAOInterpreteJPA;
import dao.jpa.DAOJPA;

public class TestDAOConcert {
	
	private Interprete renaud;
	private Interprete nougaro;
	private Interprete phil;

	@Before
	public void init() {
		DAOJPA.viderBase();
		
		DAOInterprete	daoInterprete = DAOInterpreteJPA.getInstance();
		
		renaud = new Interprete("Renaud");
		daoInterprete.save(renaud);
		nougaro = new Interprete("Nougaro");
		daoInterprete.save(nougaro);
		phil = new Interprete("Phil COLLINS");
		daoInterprete.save(phil);
	}

	@Test
	public void test() throws ParseException {
		assertEquals(0, DAOConcertJPA.getInstance().loadAll().size());
		assertEquals(3, DAOInterpreteJPA.getInstance().loadAll().size());
		
		Date d = convertDate("21/10/2014");
		
		Concert	concert = new Concert("Les enfoirés", d);
		concert.add(renaud);
		concert.add(nougaro);
		concert.add(phil);
		
		assertEquals(3, concert.getInterpretes().size());
		
		DAOConcertJPA.getInstance().save(concert);
		
		assertEquals(1, concert.getCode());
		assertEquals(1, DAOConcertJPA.getInstance().loadAll().size());
		
		Concert	concert2 = DAOConcertJPA.getInstance().get(1);
		assertEquals(concert, concert2);
		assertEquals(3, concert2.getInterpretes().size());
	}

	public Date convertDate(String maDateString) throws ParseException {
		SimpleDateFormat typeFormat = new SimpleDateFormat( "dd/MM/yyyy" );
		java.util.Date maDateFinale = typeFormat.parse(maDateString);
		return new Date(maDateFinale.getTime());
	}

}
