package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Participation {
	private	Cheval	cheval;
	private	Course	course;
	private	Date		dateCourse;
	private	int		place;
	
	
	public Participation(Cheval c1, Course course2, String string, int place) {
		this.cheval = c1;
		this.course = course2;
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dateCourse = format.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.place = place;
	}
	public Cheval getCheval() {
		return cheval;
	}
	public void setCheval(Cheval cheval) {
		this.cheval = cheval;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Date getDateCourse() {
		return dateCourse;
	}
	public void setDateCourse(Date dateCourse) {
		this.dateCourse = dateCourse;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	

}
