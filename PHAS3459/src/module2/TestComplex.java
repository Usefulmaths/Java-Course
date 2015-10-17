package module2;

public class TestComplex {
	public static void main(String[] args) {
		// Creating two new Complex objects
		Complex c1 = new Complex(1, 2);
		Complex c2 = new Complex(-2, -1);

		// c1 multiplied by c2
		System.out.println(Complex.multiply(c1, c2));
		// c1 divided by c2
		System.out.println(Complex.divide(c1, c2));
		// c1 multiplied by I
		System.out.println(Complex.multiply(c1, Complex.I));
		// c1 divided by Zero
		System.out.println(Complex.divide(c1, Complex.ZERO));
		// c1 multiplied with the conjugate of c1
		System.out.println(Complex.multiply(c1, c1.conjugate()));
		// c2 multiplied with the conjugate of c2
		System.out.println(Complex.multiply(c2, c2.conjugate()));

	}
}
