package module9;

import java.awt.Graphics;

public class Body {

	public String name;
	public double mass;
	public Vector position;
	public Vector velocity;

	public Body(String name, double mass, Vector position, Vector velocity) {
		this.name = name;
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
	}

	public Vector separationVector(Body other) {
		return new Vector(other.position.x - this.position.x, other.position.y - this.position.y);
	}

	public Vector calculateForce(Body other) {
		final double force = Constants.G * other.mass * this.mass / Math.pow(separationVector(other).magnitude(), 2);

		final double fx = force * Math.cos(separationVector(other).angle());
		final double fy = force * Math.sin(separationVector(other).angle());

		return new Vector(fx, fy);
	}

	public void incrementMovement(Vector overallForce, double timeStep) {
		final Vector acceleration = overallForce.divide(this.mass);

		final Vector changeVelocity = acceleration.multiply(timeStep);
		this.velocity = this.velocity.add(changeVelocity);

		final Vector changePosition = this.velocity.multiply(timeStep);
		this.position = this.position.add(changePosition);
	}

	@Override
	public String toString() {
		return "Body [name=" + name + ", mass=" + mass + ", position=" + position + ", velocity=" + velocity + "]";
	}

	public void draw(int x, int y, int radius, Graphics g) {
		SolarSystemView.colourBodies(g, this);
		g.fillOval(x, y, radius * 2, radius * 2);
	}

}
