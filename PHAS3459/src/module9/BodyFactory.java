package module9;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BodyFactory {

	public static Body create(final String name, final double mass, Vector position, Vector velocity, String filename) {
		try {
			System.out.println("Requesting image from GitHub: " + filename);
			BufferedImage image = ImageIO.read(new URL(filename));
			ImagedBody imagedBody = new ImagedBody(name, mass, position, velocity, image);
			return imagedBody;
		} catch (IOException e) {
			Body body = new Body(name, mass, position, velocity);
			return body;
		}
	}
}
