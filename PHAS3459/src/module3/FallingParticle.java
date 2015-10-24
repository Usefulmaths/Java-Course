package module3;

public class FallingParticle {
	// Field variables
	private static final double g = 9.81;
	private double z;
	private double m;
	private double d;

	// Default to 0 since they're instance fields.
	private double t;
	private double v;

	// Constructor to set/store mass (kg) and drag coefficient (no units) of
	// particle
	public FallingParticle(final double m, final double d) throws Exception {

		if (m <= 0) {
			throw new Exception("Invalid mass. Must be greater than zero: m = " + m);
		}
		if (d < 0) {
			throw new Exception("Invalid drag coefficient. Must be greater than or equal to zero: d = " + d);
		}

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
	public void setZ(double z) throws Exception {

		if (z < 0) {
			throw new Exception("Height above the ground must be greater than or equal to zero: z = " + this.z);
		}
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
	public void doTimeStep(final double deltaT) throws Exception {

		if (deltaT <= 0) {
			throw new Exception("Time increment must be greater than zero: deltaT = " + deltaT);
		}

		double a = d * v * v / m - g;

		this.setV(this.getV() + a * deltaT);
		this.setZ(this.getZ() + v * deltaT);
		this.setT(this.getT() + deltaT);

	}

	// Runs doTimeStep() until particles reaches a height of 0 (ground).
	public void drop(final double deltaT) throws Exception {
		if (deltaT <= 0) {
			throw new Exception("Time increment must be greater than zero: deltaT = " + deltaT);
		}
		while (this.z > 0) {
			this.doTimeStep(deltaT);
		}

	}

}
