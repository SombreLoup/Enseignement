package idee1;


public class App {
	public static void main(String[] args) {
		Balise	machin = new Balise("Machin");
		
		machin.addBalise(new Balise("Truc"));
		machin.addAttribut(new Attribut("param1", "val1"));
		machin.addAttribut(new Attribut("param2", "val2"));
		
		System.out.println(machin);
	}
}
