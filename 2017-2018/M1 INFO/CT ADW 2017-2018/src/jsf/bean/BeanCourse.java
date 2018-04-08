package jsf.bean;

import core.Cheval;
import core.Course;
import core.Participation;

public class BeanCourse {

	private	Course	course = new Course();
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public BeanCourse() {
		Cheval c1 = new Cheval(1, "Cheval A");
		course = new Course(3,"Prix de l'arc de triomphe",1000);

		Participation	p1 = new Participation(c1,course,"23/11/2017",3);

		c1.getParticipations().add(p1);
		course.getParticipants().add(p1);
	}
	
}
