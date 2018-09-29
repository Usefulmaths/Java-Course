package module5;

public class Theory {
	private final int n;

	public Theory(final int n) {
		this.n = n;
	}

	public double y(final double x) {
		return Math.pow(x, n);
	}
}
