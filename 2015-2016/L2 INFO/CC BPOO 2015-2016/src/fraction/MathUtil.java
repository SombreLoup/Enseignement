package fraction;

public class MathUtil {
	
	public static int pgcd(int a, int b) {
		if (b==0)
			return a;
		else
			return pgcd (b, a/b);
	}
}
