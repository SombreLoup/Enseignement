package starcraft;

public abstract class Unite {
	private	int	pointsVieInitial;
	private	int	pointsVieCourant;
	private	int	vitesse;
	
	public Unite(int pointsVie, int vitesse) {
		this.pointsVieCourant = pointsVie;
		this.pointsVieInitial = pointsVie;
		this.vitesse = vitesse;
	}
	
	public Unite(Unite unite) {
		this.pointsVieCourant = unite.pointsVieCourant;
		this.pointsVieInitial = unite.pointsVieInitial;
		this.vitesse = unite.vitesse;
	}
	
	public int getPointsVieInitial() {
		return pointsVieInitial;
	}

	public void setPointsVieInitial(int pointsVieInitial) {
		this.pointsVieInitial = pointsVieInitial;
	}

	public int getPointsVieCourant() {
		return pointsVieCourant;
	}

	public void setPointsVieCourant(int pointsVieCourant) {
		this.pointsVieCourant = pointsVieCourant;
	}

	public void reduirePointsDeVie(int nbPointsVie) throws MortUniteException {
		this.pointsVieCourant -= nbPointsVie;
		if (this.pointsVieCourant<=0)
			throw new MortUniteException(this);
	}
	
	public void soigner(int nbPointsVie) {
		this.pointsVieCourant += nbPointsVie;
		if (this.pointsVieCourant>this.pointsVieInitial)
			this.pointsVieCourant = this.pointsVieInitial;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public abstract Unite	clone();
}


