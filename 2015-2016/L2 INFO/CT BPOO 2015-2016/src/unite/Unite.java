package unite;

public abstract class Unite {
	private int pointDeVieInitial;
	private int pointDeVieCourant;
	private int vitesse;
	
	public Unite(int pointDeVieInitial, int pointDeVieCourant, int vitesse) {
		setPointDeVieInitial(pointDeVieInitial);
		setPointDeVieCourant(pointDeVieCourant);
		setVitesse(vitesse);
	}
	
	public Unite(Unite unite) {
		setPointDeVieInitial(unite.getPointDeVieInitial());
		setPointDeVieCourant(unite.getPointDeVieCourant());
		setVitesse(unite.getVitesse());
	}

	public int getPointDeVieInitial() {
		return pointDeVieInitial;
	}

	protected void setPointDeVieInitial(int pointDeVieInitial) {
		this.pointDeVieInitial = pointDeVieInitial;
	}

	public int getPointDeVieCourant() {
		return pointDeVieCourant;
	}

	protected void setPointDeVieCourant(int pointDeVieCourant) {
		this.pointDeVieCourant = pointDeVieCourant;
	}

	public int getVitesse() {
		return vitesse;
	}

	protected void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
	
	protected void reduirePointsDeVie(int nbPts) {
		setPointDeVieCourant(getPointDeVieCourant() - nbPts);
	}
	
	protected void soigner(int nbPts) {
		setPointDeVieCourant(getPointDeVieCourant() + nbPts);
	}
	
	protected void ameliorer(int nouveauNbPtsInit) {
		setPointDeVieInitial(nouveauNbPtsInit);
	}
	
	@Override
	public String toString() {
		return "Unite "+getClass().getName()+" [pointDeVieInitial=" + pointDeVieInitial + ", pointDeVieCourant=" + pointDeVieCourant
				+ ", vitesse=" + vitesse + "]";
	}

	@Override
	public abstract Unite clone();
}
