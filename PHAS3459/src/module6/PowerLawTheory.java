package module6;

public class PowerLawTheory implements Theory {

	private final double n;

	public PowerLawTheory(final double d) {
		this.n = d;
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
