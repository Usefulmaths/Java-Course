package module9;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SolarSystemView extends JPanel {

	private final SolarSystem solarSystem;
	private int zoomValue = 1;
	public static boolean toggleNames = false;
	// private final double startTime = System.currentTimeMillis();

	public SolarSystemView(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		drawBodies(g);
		// g.drawString("Time Elapsed: " + SolarSystem.secondsPerTick *
		// (System.currentTimeMillis() - startTime)/1000 , 30, 30);
	}

	private void drawBodies(final Graphics g) {
		final int xCentre = getWidth() / 2;
		final int yCentre = getHeight() / 2;

		for (int i = 0; i < solarSystem.bodies.size(); i++) {
			final Body body = solarSystem.bodies.get(i);

			final int x = xCentre + shorten(body.position.x);
			final int y = yCentre + shorten(body.position.y);

			final int radius;
			if (body instanceof ImagedBody) {
				ImagedBody ib = (ImagedBody) body;

				if (ib.name == "sun") {
					ib.draw(x, y, g, zoomValue * 100, zoomValue * 100);
				} else if (ib.name == "jupiter") {
					ib.draw(x, y, g, zoomValue * 40, zoomValue * 40);
				} else if (ib.name == "saturn") {
					ib.draw(x, y, g, zoomValue * 70, zoomValue * 40);
				} else if (ib.name == "asteroid") {
					ib.draw(x, y, g, zoomValue * 10, zoomValue * 10);

				} else if (ib.name == "mercury" || ib.name == "venus") {
					ib.draw(x, y, g, zoomValue * 10, zoomValue * 10);

				} else {
					ib.draw(x, y, g, zoomValue * 20, zoomValue * 20);
				}

			} else {
				if (body.name == "asteroid") {
					radius = setObjectSize(1.2);
					body.draw(x, y, radius, g);
				} else if (body.name == "jupiter" || body.name == "saturn" || body.name == "uranus") {
					radius = setObjectSize(8);
					body.draw(x, y, radius, g);
				} else {
					radius = setObjectSize(2);
					body.draw(x, y, radius, g);
				}
			}

			g.setColor(Color.WHITE);
			if (body.name != "asteroid" && toggleNames) {
				g.drawString(body.name, x, y);
			}
		}

	}

	private void drawCircle(Graphics g, int x, int y, int diameter, Body body) {
		colourBodies(g, body);
		g.fillOval(x, y, diameter, diameter);
	}

	private int shorten(double value) {
		return (int) (175 * zoomValue * value / Constants.AU);
	}

	public static void colourBodies(final Graphics g, Body body) {
		if (body.name == "mercury") {
			g.setColor(Color.GRAY);
		} else if (body.name == "venus") {
			g.setColor(Color.YELLOW);
		} else if (body.name == "earth") {
			g.setColor(Color.BLUE);
		} else if (body.name == "mars") {
			g.setColor(Color.RED);
		} else if (body.name == "jupiter") {
			g.setColor(Color.ORANGE);
		} else if (body.name == "saturn") {
			g.setColor(Color.ORANGE);
		} else if (body.name == "uranus") {
			g.setColor(Color.CYAN);
		} else if (body.name == "sun") {
			g.setColor(Color.ORANGE);
		} else if (body.name == "asteroid") {
			g.setColor(Color.WHITE);
		} else if (body.name == "moon") {
			g.setColor(Color.GRAY);
		}
	}

	public void draw() {
		repaint();
	}

	public void zoom(final int auPixels) {
		System.out.println("zoom called with value: " + auPixels);
		zoomValue = auPixels;
	}

	private int setObjectSize(double size) {
		return (int) (zoomValue * size);
	}
}
