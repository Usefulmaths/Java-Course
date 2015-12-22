package module9;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

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
		// this.image = ImageIO.read(new File(image));
		this.image = readPictureFromURL(image);
	}

	public ImagedBody(String name, double mass, Vector position, Vector velocity) {

		this.name = name;
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		// this.image = ImageIO.read(new File(image));
	}

	public Vector separationVector(ImagedBody other) {
		return other.position.subtract(this.position);
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

	private static BufferedImage readPictureFromURL(String image) throws IOException {
		System.out.println("Receiving images from GitHub: " + image);
		URL url = new URL("https://raw.githubusercontent.com/Usefulmaths/module9images/master/" + image);
		return ImageIO.read(url);

		//return ImageIO.read(new File(image));
	}

	@Override
	public String toString() {
		return "ImagedBody [name=" + name + ", mass=" + mass + ", position=" + position + ", velocity=" + velocity
				+ ", image=" + image + "]";
	}

}
