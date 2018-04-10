package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="participation")
public class Participation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="num_p")
	private	int		num;
	
	@ManyToOne
	@JoinColumn(name="code_ch")
	private	Cheval	cheval;
	
	@ManyToOne
	@JoinColumn(name="num_c")
	private	Course	course;
	
	@Column(name="date_p")
	private	Date		dateCourse;
	
	@Column(name="place")
	private	int		place;
	
	public Participation() {
	}
	
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

	@Override
	public String toString() {
		return "Participation [num=" + num + ", cheval=" + cheval.getNom() + ", course=" + course.getNom() + ", dateCourse=" + dateCourse
				+ ", place=" + place + "]";
	}
	
	
}
