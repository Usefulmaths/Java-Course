package module9;

import java.awt.Graphics;

public class Body {

	protected String name;
	protected double mass;
	protected Vector position;
	protected Vector velocity;

	public Body(final String name, final double mass, final Vector position, final Vector velocity) {
		this.name = name;
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
	}

	public Vector separationVector(final Body other) {
		return new Vector(other.position.getX() - this.position.getX(), other.position.getY() - this.position.getY());
	}

	public Vector calculateForce(Body other) {
		final double force = Constants.G * other.mass * this.mass / Math.pow(separationVector(other).magnitude(), 2);

		final double fx = force * Math.cos(separationVector(other).angle());
		final double fy = force * Math.sin(separationVector(other).angle());

		return new Vector(fx, fy);
	}

	public void incrementMovement(final Vector overallForce, double timeStep) {
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

	public String getName() {
		return name;
	}

	public double getMass() {
		return mass;
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getVelocity() {
		return velocity;
	}
}