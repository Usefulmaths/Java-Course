package module9;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImagedBody extends Body {
	private BufferedImage image;

	public ImagedBody(final	String name, final double mass, final Vector position, final Vector velocity, final String image) throws IOException {
		super(name, mass, position, velocity);
		this.image = readPictureFromURL(image);
	}

	public ImagedBody(final String name, final double mass, final Vector position, final Vector velocity) {
		super(name, mass, position, velocity);
	}
		
	private static BufferedImage readPictureFromURL(String image) throws IOException {
		System.out.println("Receiving images from GitHub: " + image);
		URL url = new URL("https://raw.githubusercontent.com/Usefulmaths/module9images/master/" + image);
		return ImageIO.read(url);
	}

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 * <p>
	 * This is required in order to allow setting the Asteroid image
	 * after loading it once.
	 * 
	 * @param image the image to use.
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
