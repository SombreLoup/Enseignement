package core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Page")
public class Page {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="code_p")
	private	int		code = -1;
	
	@Column(name="theme_p")
	private	String	theme = "Thème";

	@Column(name="date_parution_p")
	@Temporal(TemporalType.DATE)
	private	Date	dateParution = Calendar.getInstance().getTime();
	
	@Column(name="coul_p")
	private	boolean	couleur = false;
	
	@ManyToMany
	@JoinTable(name="contient",
	joinColumns={@JoinColumn(table="Page", name="code_p", referencedColumnName="code_p")},
	inverseJoinColumns={@JoinColumn(table="Article", name="code_a", referencedColumnName="code_a")}
	)
	private	List<Article>	listeArticles = new ArrayList<Article>();

	public Page() {
		super();
	}

	public Page(String theme, Date dateParution, boolean couleur) {
		super();
		testerTheme(theme);
		testerDate(dateParution);
		
		this.theme = theme;
		this.dateParution = dateParution;
		this.couleur = couleur;
	}

	public void testerDate(Date dateParution) {
		if ((dateParution==null) || dateParution.before(Calendar.getInstance().getTime()))
			throw new IllegalArgumentException("Date antérieure à la date du jour");
	}

	public void testerTheme(String theme) {
		if ((theme==null) || (theme.trim().length()==0))
			throw new IllegalArgumentException("Theme vide ou null");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		testerTheme(theme);
		this.theme = theme;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		testerDate(dateParution);
		this.dateParution = dateParution;
	}

	public boolean isCouleur() {
		return couleur;
	}

	public void setCouleur(boolean couleur) {
		this.couleur = couleur;
	}

	public List<Article> getListeArticles() {
		return listeArticles;
	}

	public void setListeArticles(List<Article> listeArticles) {
		this.listeArticles = listeArticles;
	}

	public int size() {
		return listeArticles.size();
	}

	public boolean isEmpty() {
		return listeArticles.isEmpty();
	}

	public boolean add(Article e) {
		return listeArticles.add(e);
	}

	public Article get(int index) {
		return listeArticles.get(index);
	}

	public boolean contains(Object o) {
		return listeArticles.contains(o);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
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
		Page other = (Page) obj;
		if (code != other.code)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Page [code=" + code + ", theme=" + theme + ", dateParution="
				+ dateParution + ", couleur=" + couleur + ", listeArticles="
				+ listeArticles + "]";
	}
	
	public double	getTarif() {
		double tarif = 0;
		
		for (Article article : listeArticles) {
			tarif += article.getTarif();
		}
		
		return tarif;
	}

}
