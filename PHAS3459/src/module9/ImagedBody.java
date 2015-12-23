package module9;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImagedBody extends Body {
	private BufferedImage image;

	public ImagedBody(final	String name, final double mass, final Vector position, final Vector velocity, final BufferedImage image) throws IOException {
		super(name, mass, position, velocity);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
