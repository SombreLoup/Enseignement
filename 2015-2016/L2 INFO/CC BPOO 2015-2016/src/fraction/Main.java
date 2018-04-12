package fraction;

public class Main {

	public static void main(String[] args) {
		Console es = new Console();
		
		int	a,b;
		
		System.out.print("Numérateur   :");
		a = es.readInt();
		
		System.out.print("Dénominateur :");
		b = es.readInt();
		
		Fraction f=null;
		try {
			f = new Fraction(a, b);
			System.out.println("Fraction : "+f);
		} catch (DenominateurNulException e) {
			System.out.println("Fraction impossible : dénominateur nul");
			System.exit(0);
		} catch (DenominateurNegatifException e) {
			System.out.println("Fraction impossible : dénominateur négatif");
			System.exit(0);
		}		

		
		Fraction inverse;
		try {
			inverse = f.inverse();
			System.out.println("Fraction inverse : "+inverse);
		} catch (DenominateurNulException e) {
			System.out.println("La fraction "+f+" ne peut être inversée");
		}
	}

}
