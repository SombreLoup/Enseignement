package instruction;

import expression.ExprAr;
import expression.VariableAr;

public class Affectation implements Instruction {

	private	VariableAr	variable;
	private	ExprAr		expression;

	public Affectation(VariableAr v, ExprAr e) {
		variable = v;
		expression = e;
	}
	
	public void setVariable(VariableAr variable) {
		this.variable = variable;
	}

	public void setExpression(ExprAr expression) {
		this.expression = expression;
	}

	@Override
	public void executer() {
		variable.setValeur(expression.evaluer());
	}

	@Override
	public String traduire(String langage) {
		String s = "";
		
		if (langage.equals("C"))
			s = variable.getNom()+"="+expression.toString()+";";
		else if (langage.equals("COBOL"))
			s = "COMPUTE "+variable.getNom()+"="+expression.toString();
		
		return s;
	}

}
