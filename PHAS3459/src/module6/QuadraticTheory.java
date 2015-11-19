package module6;

public class QuadraticTheory implements Theory {

	private final double a;
	private final double b;
	private final double c;

	public QuadraticTheory(final double a, final double b, final double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public double y(final double x) {
		return a * Math.pow(x, 2) + b * x + c;
	}

	@Override
	public String toString() {
		return a + "x^2 + " + b + "x + " + c;
	}

}
