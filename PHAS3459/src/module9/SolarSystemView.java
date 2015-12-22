package module9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SolarSystemView extends JPanel implements MouseDragListener {
	private final SolarSystem solarSystem;
	private double zoomValue = 10;
	public static boolean toggleNames = false;

	public SolarSystemView(SolarSystem solarSystem) {
		this.solarSystem = solarSystem;

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBodies(g);
		
		BufferedImage img = null;
		try{
			img = ImageIO.read(new URL("https://raw.githubusercontent.com/Usefulmaths/PHAS3459/master/PHAS3459/starbackground.png?token=AIggDpTsyWU7qKDNB6uX4kkAC48e9Ektks5WggVvwA%3D%3D"));
			
		}
		catch(IOException e){
			
		}
		
		g.drawImage(img, 0, 0, 2000, 1000, null);
	}

	private void drawBodies(final Graphics g) {
		final int xCentre = getWidth() / 2;
		final int yCentre = getHeight() / 2;

		for (int i = 0; i < solarSystem.bodies.size(); i++) {
			final ImagedBody body = (ImagedBody) solarSystem.bodies.get(i);

			// why are these split into declaration and assignment? XD
			final int x;
			final int y;

			if (body.name.equals("moon")) {
				final ImagedBody earth = solarSystem.bodies.stream().filter(test -> test.name.equals("earth")).findAny()
						.orElseThrow(() -> new RuntimeException("what"));

				final Vector offsetFromEarth = body.separationVector(earth).unitVector().multiply(500)
						.divide(zoomValue);

				final Vector drawPosition = new Vector(xCentre + shorten(body.position.x),
						yCentre + shorten(body.position.y));

				final Vector offsetPosition = drawPosition.add(offsetFromEarth);
				x = (int) (offsetPosition.x);
				y = (int) (offsetPosition.y);
			} else {
				x = xCentre + shorten(body.position.x);
				y = yCentre + shorten(body.position.y);
			}

			final Dimensions dimensions = getDimensionsForBody(body.name);

			drawWithOffset(g, body.image, x, y, dimensions);

			g.setColor(Color.WHITE);
			if (toggleNames && body.name != "asteroid") {
				drawName(g, body.name, x, y);
			}

			drawTime(g, SolarSystem.timer, 30, 30);
		}

	}

	void drawTime(final Graphics g, double timer, final int x, final int y) {
		// g.drawString("Time elapsed (days): " + Double.toString(timer), x, y);
	}

	void drawName(final Graphics g, final String name, final int x, final int y) {
		g.drawString(Character.toUpperCase(name.charAt(0)) + name.substring(1), x + (int) offset.x, y + (int) offset.y);
	}

	Dimensions getDimensionsForBody(final String name) {
		switch (name) {
		case "sun":
			return Dimensions.square((int) (1200 / zoomValue));
		case "jupiter":
			return Dimensions.square((int) (800 / zoomValue));
		case "uranus":
			return Dimensions.square((int) (600 / zoomValue));
		case "neptune":
			return Dimensions.square((int) (600 / zoomValue));
		case "saturn":
			return Dimensions.square((int) (700 / zoomValue));
		case "asteroid":
			return Dimensions.square((int) (150 / zoomValue));
		case "mercury":
			return Dimensions.square((int) (150 / zoomValue));
		case "moon":
			return Dimensions.square((int) (100 / zoomValue));
		default:
			return Dimensions.square((int) (300 / zoomValue));
		}
	}

	void drawWithOffset(final Graphics g, final BufferedImage image, int x, int y, final Dimensions dimensions) {
		g.drawImage(image, x - dimensions.width / 2 + (int) offset.x, y - dimensions.height / 2 + (int) offset.y,
				dimensions.width, dimensions.height, null);
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
