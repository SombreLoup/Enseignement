package instruction;

import java.util.ArrayList;

public class Sequence implements Instruction {

	private	ArrayList<Instruction> bloc;
	
	public Sequence() {
		bloc = new ArrayList<Instruction>();
	}
	
	public void add(Instruction i) {
		bloc.add(i);
	}
	
	@Override
	public void executer() {
		for (Instruction i:bloc) {
			i.executer();
		}
	}

	@Override
	public String traduire(String langage) {
		String s = "";
		
		if (langage.equals("C"))
			s = "{\n";
		
		for (Instruction i:bloc) {
			s = s+i.traduire(langage)+"\n";
		}

		if (langage.equals("C"))
			s = s+"}";

		return s;
	}

}
