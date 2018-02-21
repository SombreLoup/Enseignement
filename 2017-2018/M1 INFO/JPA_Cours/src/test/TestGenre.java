package test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Genre;

public class TestGenre {

	@Test
	public void test() {
		Genre	genre1 = new Genre("Jazz");
		Genre	genre2 = new Genre(1, "Pop");
		
		assertEquals(-1, genre1.getCode());
		assertEquals("Jazz", genre1.getLibelle());
		assertEquals(1, genre2.getCode());
		assertEquals("Pop", genre2.getLibelle());
	}

}
