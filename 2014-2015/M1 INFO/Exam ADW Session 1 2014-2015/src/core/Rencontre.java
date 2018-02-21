package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@Table(name="rencontre")
public class Rencontre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_ren")
	private	int		code = -1;

	@Column(name="desc_ren")
	private String	description;

	@Column(name="date_ren")
	@Temporal(TemporalType.DATE)
	private	Date	date;

	@Column(name="note_ren")
	private	double	note;
	
	@ManyToMany(mappedBy="rencontres",cascade=CascadeType.ALL)
	private	List<Celibataire>	participants = new ArrayList<Celibataire>();

	public Rencontre(String description, Date date) {
		super();
		this.description = description;
		this.date = date;
	}

	public Rencontre() {
		super();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public List<Celibataire> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Celibataire> participants) {
		this.participants = participants;
	}

	public boolean add(Celibataire e) {
		if (! e.getRencontres().contains(this))
			e.getRencontres().add(this);
		return participants.add(e);
	}

	public boolean remove(Object o) {
		return participants.remove(o);
	}

	public Celibataire get(int index) {
		return participants.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(note);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((participants == null) ? 0 : participants.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rencontre other = (Rencontre) obj;
		if (code != other.code)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(note) != Double
				.doubleToLongBits(other.note))
			return false;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rencontre [code=" + code + ", description=" + description
				+ ", date=" + date + ", note=" + note + ", participants="
				+ participants + "]";
	}
	
	
}