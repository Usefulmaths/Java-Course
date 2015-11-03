package module5;

public class DataPoint {

	private double x;
	private double y;
	private double ey;

	public DataPoint(double x, double y, double ey) {
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
		return "DataPoint [x=" + x + ", y=" + y + ", ey=" + ey + "]";
	}
	
	

}
