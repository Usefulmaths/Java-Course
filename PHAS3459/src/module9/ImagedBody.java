package module9;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagedBody {

	public String name;
	public double mass;
	public Vector position;
	public Vector velocity;
	public BufferedImage image;

	public ImagedBody(String name, double mass, Vector position, Vector velocity, String image) throws IOException {

		this.name = name;
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.image = ImageIO.read(new File(image));
	}
	public Vector separationVector(ImagedBody other) {
		return new Vector(other.position.x - this.position.x, other.position.y - this.position.y);
	}

	public Vector calculateForce(ImagedBody other) {
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
		return "ImagedBody [name=" + name + ", mass=" + mass + ", position=" + position + ", velocity=" + velocity
				+ ", image=" + image + "]";
	}

	
}
