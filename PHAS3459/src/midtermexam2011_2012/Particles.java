package midtermexam2011_2012;

public class Particles {
	private double x;
	private double y;
	private int z;

	public Particles(double x, double y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "Particles [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
}
