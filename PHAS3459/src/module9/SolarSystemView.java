package module9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SolarSystemView extends JPanel implements MouseDragListener {
	private final SolarSystem solarSystem;
	private double zoomValue = 10;
	private static boolean toggleNames = false;
	private BufferedImage backgroundImage;
	
	private Vector previous;
	private Vector offset = new Vector(0, 0);
	
	private final DimensionsFactory dimensionsFactory = new DimensionsFactory(); 

	public SolarSystemView(SolarSystem solarSystem) throws MalformedURLException, IOException {
		this.solarSystem = solarSystem;

		addMouseListener(this);
		addMouseMotionListener(this);

		backgroundImage = ImageIO.read(new URL(
				"https://raw.githubusercontent.com/Usefulmaths/PHAS3459/master/PHAS3459/starbackground.png?token=AIggDmvwT53JTjCXM4w-CMNfsJIE627Iks5WggaiwA%3D%3D"));

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		drawBodies(g);

		g.drawImage(backgroundImage, 0, 0, null);
	}

	private void drawBodies(final Graphics g) {
		final int xCentre = getWidth() / 2;
		final int yCentre = getHeight() / 2;

		for (int i = 0; i < solarSystem.getBodies().size(); i++) {
			final ImagedBody body = (ImagedBody) solarSystem.getBodies().get(i);

			// why are these split into declaration and assignment? XD
			final int x;
			final int y;

			if (body.name.equals("moon")) {
				final ImagedBody earth = solarSystem.getBodies().stream().filter(test -> test.name.equals("earth")).findAny()
						.orElseThrow(() -> new RuntimeException("what"));

				final Vector offsetFromEarth = body.separationVector(earth).unitVector().multiply(500)
						.divide(zoomValue);

				final Vector drawPosition = new Vector(xCentre + shorten(body.position.getX()),
						yCentre + shorten(body.position.getY()));

				final Vector offsetPosition = drawPosition.add(offsetFromEarth);
				x = (int) (offsetPosition.getX());
				y = (int) (offsetPosition.getY());
			} else {
				x = xCentre + shorten(body.position.getX());
				y = yCentre + shorten(body.position.getY());
			}

			final Dimensions dimensions = getDimensionsForBody(body.name);

			drawWithOffset(g, body.getImage(), x, y, dimensions);

			g.setColor(Color.WHITE);
			if (toggleNames && body.name != "asteroid") {
				drawName(g, body.name, x, y);
			}

			drawTime(g, SolarSystem.getElapsedTicks(), 30, 30);
		}

	}

	void drawTime(final Graphics g, double timer, final int x, final int y) {
		// g.drawString("Time elapsed (days): " + Double.toString(timer), x, y);
	}

	void drawName(final Graphics g, final String name, final int x, final int y) {
		g.drawString(Character.toUpperCase(name.charAt(0)) + name.substring(1), x + (int) offset.getX(), y + (int) offset.getY());
	}

	Dimensions getDimensionsForBody(final String name) {
		switch (name) {
		case "sun":
			return dimensionsFactory.square((int) (1200 / zoomValue));
		case "jupiter":
			return dimensionsFactory.square((int) (800 / zoomValue));
		case "uranus":
			return dimensionsFactory.square((int) (800 / zoomValue));
		case "neptune":
			return dimensionsFactory.square((int) (800 / zoomValue));
		case "saturn":
			return dimensionsFactory.square((int) (800 / zoomValue));
		case "asteroid":
			return dimensionsFactory.square((int) (150 / zoomValue));
		case "moon":
			return dimensionsFactory.square((int) (100 / zoomValue));
		default:
			return dimensionsFactory.square((int) (400 / zoomValue));
		}
	}

	void drawWithOffset(final Graphics g, final BufferedImage image, int x, int y, final Dimensions dimensions) {
		g.drawImage(image, x - dimensions.getWidth() / 2 + (int) offset.getX(), y - dimensions.getHeight() / 2 + (int) offset.getY(),
				dimensions.getWidth(), dimensions.getHeight(), null);
	}

	private int shorten(double value) {
		return (int) (2500 / zoomValue * value / Constants.AU);
	}

	public void draw() {
		repaint();
	}

	public void zoom(final int auPixels) {
		System.out.println("zoom called with value: " + auPixels);
		zoomValue = auPixels;
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		previous = new Vector(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		final int x = e.getX();
		final int y = e.getY();

		final Vector delta = new Vector(x - previous.getX(), y - previous.getY());
		offset = offset.add(delta);
		previous = new Vector(x, y);
	}

	public ItemListener toggleNames() {
		return new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == ItemEvent.SELECTED) {
					SolarSystemView.toggleNames = true;
				} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
					SolarSystemView.toggleNames = false;
				}
			}
		};
	}
}
