package module6;

// Implements Theory to inherit abstract method y.
public class PowerLawTheory implements Theory {

	private final double n;

	public PowerLawTheory(final double n) {
		this.n = n;
	}

	@Override
	public double y(final double x) {
		return Math.pow(x, n);
	}

	@Override
	public String toString() {
		return "x^" + n;
	}
}
