package jsf.bean;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.jboss.weld.exceptions.IllegalArgumentException;

import core.Cheval;
import core.Course;
import core.Participation;
import dao.jpa.DAOChevalJPA;
import dao.jpa.DAOCourseJPA;

public class BeanParticipation {
	private	Cheval	cheval;
	private	Course	course;
	private	Participation	participation;
	
	
	public BeanParticipation() {
		participation = new Participation();
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		participation.setDateCourse(date);
	}
	
	public Cheval getCheval() {
		return cheval;
	}
	
	public void setCheval(Cheval cheval) {
		this.cheval = cheval;
	}
	
	public List<Cheval> getChevaux() {
		return DAOChevalJPA.getInstance().getChevaux();
	}
	
	public Course getCourse() {
		return course;
	}
	
	public List<Course> getCourses() {
		return DAOCourseJPA.getInstance().getCourses();
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}

	public Participation getParticipation() {
		return participation;
	}

	public void setParticipation(Participation participation) {
		this.participation = participation;
	}
	
	public void setCodeCheval(int num) {
		cheval = DAOChevalJPA.getInstance().get(num);
	}
	
	public int getCodeCheval() {
		return 0;
	}
	
	public void setNumeroCourse(int num) {
		course = DAOCourseJPA.getInstance().get(num);
	}
	
	public int getNumeroCourse() {
		return 0;
	}
	
	public String update() {
		if (cheval == null)
			throw new IllegalArgumentException("Le cheval est nul");
		
		participation.setCheval(cheval);
		
		if (course == null)
			throw new IllegalArgumentException("La course est nulle");
		
		participation.setCourse(course);
		
		cheval.getParticipations().add(participation);
		course.getParticipants().add(participation);
		
		DAOCourseJPA.getInstance().update(course);
		
		return "METTREAJOUR";
	}
}
