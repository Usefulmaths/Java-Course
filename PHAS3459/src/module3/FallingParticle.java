package module3;

public class FallingParticle {
	// Field variables
	private static final double g = 9.81;
	private double z, m, d;

	// Default to 0 since they're instance fields.
	private double t;
	private double v;

	// Constructor to set/store mass (kg) and drag coefficient (no units) of
	// particle
	public FallingParticle(final double m, final double d) throws Exception {

		try {
			this.isVariableLessThanOrEqualToZero(m);
			this.isVariableLessThanZero(d);

			this.m = m;
			this.d = d;
		}

		catch (Exception e) {
			System.out.println(e);
			this.m = 0;
			this.d = 0;

		}
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

		try {
			this.isVariableLessThanZero(z);
			this.z = z;
		} catch (Exception e) {
			System.out.println(e);
		}
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

		try {
			this.isVariableLessThanZero(deltaT);

			double a = d * v * v / m - g;

			this.setV(this.getV() + a * deltaT);
			this.setZ(this.getZ() + v * deltaT);
			this.setT(this.getT() + deltaT);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Runs doTimeStep() until particles reaches a height of 0 (ground).
	public void drop(final double deltaT) throws Exception {
		try {
			this.isVariableLessThanZero(deltaT);

			while (this.z > 0) {
				this.doTimeStep(deltaT);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void isVariableLessThanOrEqualToZero(double c) throws Exception {
		if (c <= 0) {
			throw new Exception("Inappropriate choice of variable (must be greater than zero): " + c);
		}
	}

	public void isVariableLessThanZero(double c) throws Exception {
		if (c < 0) {
			throw new Exception("Inappropriate choice of variable (must be atleast zero): " + c);
		}
	}

}
