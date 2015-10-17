package module2;

public class FallingParticle {
	// Field variables
	private final double m;
	private final double d;
	private double t = 0;
	public double z;
	private double v = 0;
	private final double g = 9.81;

	// Constructor to set/store mass (kg) and drag coefficient (no units) of
	// particle
	public FallingParticle(final double m, final double d) {
		this.m = m;
		this.d = d;
	}

	// Returns time (seconds)
	public double getT() {
		return t;
	}

	// Sets time (seconds)
	public void setT(double t) {
		this.t = t;
	}

	// Returns height (metres)
	public double getZ() {
		return z;
	}

	// Sets height (metres)
	public void setZ(double z) {
		this.z = z;
	}

	// Returns velocity (metres per second)
	public double getV() {
		return v;
	}

	// Sets velocity (metres per second)
	public void setV(double v) {
		this.v = v;
	}

	// Calculates new V, Z, and T due to change in acceleration
	// in deltaT time steps
	public void doTimeStep(final double deltaT) {
		double a = d * v * v / m - g;

		this.setV(this.getV() + a * deltaT);
		this.setZ(this.getZ() + v * deltaT);
		this.setT(this.getT() + deltaT);
	}

	// Runs doTimeStep() until particles reaches a height of 0 (ground).
	public void drop(final double deltaT) {
		while (this.z > 0) {
			this.doTimeStep(deltaT);
		}
	}

}
