package module9;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class TileImage extends JPanel {
	BufferedImage tileImage;

	public TileImage(BufferedImage image) {
		tileImage = image;
	}

	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		for (int x = 0; x < width; x += tileImage.getWidth()) {
			for (int y = 0; y < height; y += tileImage.getHeight()) {
				g.drawImage(tileImage, x, y, this);
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(2400, 2400);
	}

}
