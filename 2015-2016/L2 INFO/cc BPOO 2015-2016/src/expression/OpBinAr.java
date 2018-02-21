package expression;

public abstract class OpBinAr implements ExprAr {
	private	ExprAr	filsGauche, filsDroit;
	
	public OpBinAr(ExprAr fg, ExprAr fd) {
		filsGauche = fg;
		filsDroit = fd;
	}

	public ExprAr getFilsGauche() {
		return filsGauche;
	}

	public ExprAr getFilsDroit() {
		return filsDroit;
	}
	

}
