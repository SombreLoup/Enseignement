package main;

import expression.Addition;
import expression.ExprAr;
import expression.Litteral;
import expression.VariableAr;
import instruction.Affectation;
import instruction.Instruction;
import instruction.Sequence;

public class Princ {

	public static void main(String[] args) {
		
		VariableAr	v1 = new VariableAr("v1");
		VariableAr	v2 = new VariableAr("v2");
		VariableAr	v3 = new VariableAr("v3");
		ExprAr	_1 = new Litteral(1);
		
		// Suite d'instructions qui calcule le début de la suite de Fibonacci
		Instruction instr1 = new Affectation(v1, _1);
		Instruction instr2 = new Affectation(v2, _1);
		Instruction instr3 = new Affectation(v3, new Addition(v2,v1));
		Instruction instr4 = new Affectation(v1, v2);
		Instruction instr5 = new Affectation(v2, v3);
		Instruction instr6 = new Affectation(v3, new Addition(v2,v1));
		
		Sequence seq = new Sequence();
		seq.add(instr1);
		seq.add(instr2);
		seq.add(instr3);
		seq.add(instr4);
		seq.add(instr5);
		seq.add(instr6);
		
		System.out.println("--- Langage C ---");
		System.out.println(seq.traduire("C"));
		System.out.println();
		
		System.out.println("--- COBOL ---");
		System.out.println(seq.traduire("COBOL"));

		System.out.println("--- Après l'exécution du programme ---");
		seq.executer();
		System.out.println("v1 vaut "+v1.evaluer());
		System.out.println("v2 vaut "+v2.evaluer());
		System.out.println("v3 vaut "+v3.evaluer());		
	}

}
