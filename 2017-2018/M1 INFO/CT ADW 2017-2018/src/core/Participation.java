package core;

import java.util.Date;

public class Participation {
	private	Cheval	cheval;
	private	Course	course;
	private	Date		dateCourse;
	private	int		place;
	
	
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
