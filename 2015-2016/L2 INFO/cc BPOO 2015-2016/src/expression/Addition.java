package expression;

public class Addition extends OpBinAr {

	
	public Addition(ExprAr fg, ExprAr fd) {
		super(fg,fd);
	}
	@Override
	public double evaluer() {
		return getFilsGauche().evaluer()+getFilsDroit().evaluer();
	}
	
	public String toString() {
		return "("+getFilsGauche().toString()+"+"+getFilsDroit().toString()+")";
	}

}
