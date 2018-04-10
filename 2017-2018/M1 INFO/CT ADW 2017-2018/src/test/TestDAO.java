package test;

import java.util.List;

import org.junit.jupiter.api.Test;

import core.Cheval;
import core.Course;
import dao.jpa.DAOChevalJPA;
import dao.jpa.DAOCourseJPA;

class TestDAO {

	@Test
	void testCourses() {
		DAOCourseJPA	dao = DAOCourseJPA.getInstance();
		
		List<Course>	l = dao.getCourses();
		System.out.println(l);
		
		
	}

	@Test
	void testChevaux() {
		DAOChevalJPA	dao = DAOChevalJPA.getInstance();
		
		List<Cheval>	l = dao.getChevaux();
		System.out.println(l);
		
		
	}

}
