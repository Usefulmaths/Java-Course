package module9;

public class Vector {

	public final static Vector VECTOR_ZERO = new Vector(0, 0);

	public final double x;
	public final double y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector add(Vector other) {
		return new Vector(this.x + other.x, this.y + other.y);
	}

	public Vector subtract(Vector other) {
		return new Vector(this.x - other.x, this.y - other.y);
	}

	public Vector multiply(double number) {
		return new Vector(this.x * number, this.y * number);
	}

	public Vector divide(double number) {
		return new Vector(this.x / number, this.y / number);
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public double angle() {
		return Math.atan2(y, x);
	}
	
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}

}
