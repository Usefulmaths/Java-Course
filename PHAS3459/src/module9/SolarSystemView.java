package module9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SolarSystemView extends JPanel implements MouseDragListener {
	private static final long serialVersionUID = 1L;

	private final SolarSystem solarSystem;
	private double zoomValue = 1;
	public static boolean toggleNames = false;

	// private final double startTime = System.currentTimeMillis();

	public SolarSystemView(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;

		addMouseListener(this);
		addMouseMotionListener(this);
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
			final ImagedBody body = (ImagedBody) solarSystem.bodies.get(i);

			final int x = xCentre + shorten(body.position.x);
			final int y = yCentre + shorten(body.position.y);

			final Dimensions dimensions = getDimensionsForBody(body.name);
			drawWithOffset(g, body.image, x, y, dimensions);

			g.setColor(Color.WHITE);
			if (toggleNames && body.name != "asteroid") {
				drawName(g, body.name, x, y);
			}
		}

	}

	void drawName(final Graphics g, final String name, final int x, final int y) {
		g.drawString(Character.toUpperCase(name.charAt(0)) + name.substring(1), x + (int) offset.x, y + (int) offset.y);
		
	}

	Dimensions getDimensionsForBody(final String name) {
		switch (name) {
		case "sun":
			return Dimensions.square((int) (100 / zoomValue));
		case "jupiter":
			return Dimensions.square((int) (60 / zoomValue));
		case "uranus":
			return Dimensions.square((int) (60 / zoomValue));
		case "neptune":
			return Dimensions.square((int) (60 / zoomValue));
		case "saturn":
			return new Dimensions((int) (70 / zoomValue), (int) (40 / zoomValue));
		case "asteroid":
			return Dimensions.square((int) (10 / zoomValue));
		case "mercury":
			return Dimensions.square((int) (10 / zoomValue));
		default:
			return Dimensions.square((int) (30 / zoomValue));
		}
	}

	static class Dimensions {
		public final int width;
		public final int height;

		public static Dimensions square(final int length) {
			return new Dimensions(length, length);
		}

		public Dimensions(final int width, final int height) {
			this.width = width;
			this.height = height;
		}

	}

	void drawWithOffset(final Graphics g, final BufferedImage image, int x, int y, final Dimensions dimensions) {
		g.drawImage(image, x - dimensions.width / 2 + (int) offset.x, y - dimensions.height / 2 + (int) offset.y,
				dimensions.width, dimensions.height, null);
	}

	private int shorten(double value) {
		return (int) (250 / zoomValue * value / Constants.AU);
	}

	public void draw() {
		repaint();
	}

	public void zoom(final int auPixels) {
		System.out.println("zoom called with value: " + auPixels);
		zoomValue = auPixels;
	}

	Vector previous;
	Vector offset = new Vector(0, 0);

	@Override
	public void mousePressed(MouseEvent e) {
		previous = new Vector(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		final int x = e.getX();
		final int y = e.getY();

		final Vector delta = new Vector(x - previous.x, y - previous.y);
		offset = offset.add(delta);
		previous = new Vector(x, y);

		System.out.println("Absolute: (" + x + ", " + y + ")");
		System.out.println("Previous: (" + previous.x + ", " + previous.y + ")");
		System.out.println("Delta: (" + delta.x + ", " + delta.y + ")");
		System.out.println();
		// System.out.println("Initial x: " + initialX + "difference: " +
		// dragX);
	}
}
