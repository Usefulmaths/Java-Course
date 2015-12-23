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

	@Override
	public String toString() {
		return "ImagedBody [name=" + name + ", mass=" + mass + ", position=" + position + ", velocity=" + velocity
				+ ", image=" + image + "]";
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
