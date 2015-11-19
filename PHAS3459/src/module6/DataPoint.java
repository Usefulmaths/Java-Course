package module6;

public class DataPoint {
	private final double x;
	private final double y;
	private final double ey;

	public DataPoint(final double x, final double y, final double ey) {
		this.x = x;
		this.y = y;
		this.ey = ey;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getEy() {
		return ey;
	}

	@Override
	public String toString() {
		return "x = " + x + ", y = " + y + "+-" + ey;
	}
	
	
}
