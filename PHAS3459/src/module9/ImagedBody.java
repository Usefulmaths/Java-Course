package module9;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagedBody extends Body {

	public BufferedImage image;

	public ImagedBody(String name, double mass, Vector position, Vector velocity, String image) throws IOException {
		super(name, mass, position, velocity);
		this.image = ImageIO.read(new File(image));
	}

	public void draw(int x, int y, Graphics g, int width, int height) {
		g.drawImage(this.image, x - width / 2, y - height / 2, width, height, null);
	}

}
