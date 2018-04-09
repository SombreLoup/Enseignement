package dao.jpa;

import java.util.List;

import javax.persistence.Query;

import core.Course;

public class DAOCourseJPA extends DAOJPA {
	
	private	static DAOCourseJPA		instance = null;
	
	public static DAOCourseJPA getInstance() {
		if (instance==null)
			instance = new DAOCourseJPA();
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Course>	getCourses() {
		Query query = getManager().createQuery("SELECT course FROM Course course");
		return query.getResultList();
	}

	public Course get(int i) {
		return getManager().find(Course.class, i);
	}

	public void update(Course course) {
		Course c = getManager().merge(course);
		getManager().persist(c);
		commit();
	}
}
