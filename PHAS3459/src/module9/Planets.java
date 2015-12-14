package module9;

public class Planets extends SpacialBody {

	private double velocity;
	private double angularVelocity;
	private SpacialBody sun;
	private double distanceFromSun;

	public Planets(double x, double y, double radius, double distanceFromSun, SpacialBody sun) {
		super(x, y, radius);
		this.sun = sun;

		this.distanceFromSun = distanceFromSun;
		this.velocity = this.setVelocity(sun);
		this.angularVelocity = this.setAngularVelocity();
		
		this.x = sun.getX() + x;
		this.y = sun.getY() + y;
	}

	public double setVelocity(SpacialBody sun) {
		return Math.sqrt(1 / distanceFromSun);
	}

	public double setAngularVelocity() {
		return velocity/distanceFromSun;
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}

	public double getVelocity() {
		return velocity;
	}

	public double getDistanceFromSun() {
		return distanceFromSun;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setAngularVelocity(double angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	public void setDistanceFromSun(double distanceFromSun) {
		this.distanceFromSun = distanceFromSun;
	}

	
}
