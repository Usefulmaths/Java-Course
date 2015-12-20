package module9;

import static module9.Constants.AU;
import static module9.Constants.MASS_EARTH;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.jai.TiledImage;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		final double VIEW_WIDTH = screenSize.getWidth();
		final double VIEW_HEIGHT = screenSize.getHeight();

		Container container = new Container("Simple Solar System.");
		container.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		container.setSize((int) VIEW_WIDTH, (int) VIEW_HEIGHT);

		List<ImagedBody> bodies = new ArrayList<>();
		ImagedBody centralBody;
		try {
			centralBody = new ImagedBody("sun", 333054 * MASS_EARTH, new Vector(0, 0), new Vector(0, 0), "sun.png");

			List<ImagedBody> simpleSolarSystem = retrieveSimpleSolarSystem(centralBody);
			List<ImagedBody> asteroids = addAsteroidBelt(250, centralBody);

			bodies.addAll(simpleSolarSystem);
			bodies.addAll(asteroids);
			
			SolarSystem solarSystem = new SolarSystem(bodies);
			final SolarSystemView solarSystemView = new SolarSystemView(solarSystem);
			container.add(solarSystemView);

			final JPanel widgets = new JPanel(new GridLayout(1, 2));
			container.add(widgets, BorderLayout.SOUTH);

			final ZoomPanel zoomPanel = new ZoomPanel(solarSystemView::zoom);
			widgets.add(zoomPanel);
			
			final JToggleButton jtb = new JToggleButton("Toggle Names");
			jtb.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ev) {
					if (ev.getStateChange() == ItemEvent.SELECTED) {
						SolarSystemView.toggleNames = true;
					} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
						SolarSystemView.toggleNames = false;
					}
				}
			});
			widgets.add(jtb);

			final int fps = 200000;
			final int updateDelay = 1000 / fps;
			new Timer(updateDelay, (e) -> {
				solarSystem.tick();
				solarSystemView.draw();
				System.out.println();
			}).start();

			container.setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private static boolean circleBandConstraint(Vector v, double bandMinimum, double bandMaximum) {
		if (bandMinimum < Math.sqrt(v.x * v.x + v.y * v.y) && Math.sqrt(v.x * v.x + v.y * v.y) < bandMaximum) {
			return true;
		}
		return false;
	}

	private static Vector velocityPerpendicularToPosition(ImagedBody body, ImagedBody centralBody) {
		double velocityAngle = -Math.PI / 2 + body.position.angle();
		double velocity = Math.sqrt(Constants.G * centralBody.mass / body.separationVector(centralBody).magnitude());
		return new Vector(velocity * Math.cos(velocityAngle), velocity * Math.sin(velocityAngle));
	}

	private static List<ImagedBody> addAsteroidBelt(int numberOfAsteroids, ImagedBody centralBody) throws IOException {
		List<ImagedBody> asteroids = new ArrayList<>();

		while (asteroids.size() < numberOfAsteroids) {
			ImagedBody body = new ImagedBody("asteroid", 18.0e8 + 1e11 * Math.random(),
					new Vector(-4 * AU + 8 * AU * Math.random(), -4 * AU + 8 * AU * Math.random()), new Vector(0, 0),
					"asteroid.png");

			body.velocity = velocityPerpendicularToPosition(body, centralBody);

			if (circleBandConstraint(body.position, 2.2 * AU, 3.2 * AU)) {
				asteroids.add(body);
			}
		}

		ImagedBody ceres = new ImagedBody("ceres", 9.393e20, new Vector(2.9773 * AU, 0), new Vector(0, -17482),
				"ceres.png");
		ImagedBody pallas = new ImagedBody("pallas", 2.11e20, new Vector(0, 3.412605509 * AU), new Vector(15050, 0),
				"pallas.png");
		ImagedBody vesta = new ImagedBody("vesta", 2.59076e20, new Vector(-2.57138 * AU, 0), new Vector(0, 17340),
				"vesta.png");
		ImagedBody hygiea = new ImagedBody("hygiea", 8.67e19, new Vector(0, -3.5024 * AU), new Vector(-13760, 0),
				"hygiea.png");

		asteroids.add(ceres);
		asteroids.add(pallas);
		asteroids.add(vesta);
		asteroids.add(hygiea);

		return asteroids;
	}

	private static List<ImagedBody> retrieveSimpleSolarSystem(ImagedBody centralBody) throws IOException {

		Vector initialEarthPosition = new Vector(Constants.AU, 0);
		Vector initialEarthVelocity = new Vector(0, -30290);

		List<ImagedBody> simpleSolarSystem = Arrays.asList(centralBody,
				new ImagedBody("mercury", 0.0553 * MASS_EARTH, new Vector(0.313 * AU, 0), new Vector(0, -58980),
						"mercury.png"),
				new ImagedBody("venus", 0.815 * MASS_EARTH, new Vector(0.731 * AU, 0), new Vector(0, -35260),
						"venus.png"),
				new ImagedBody("earth", MASS_EARTH, new Vector(0.9833 * AU, 0), initialEarthVelocity, "earth.png"),

				// new Body("moon", 0.0123 * MASS_EARTH,
				// initialEarthPosition.add(new Vector(0.00257 * AU, 0)),
				// initialEarthVelocity.add(new Vector(0, 1022 ))),

				new ImagedBody("mars", 0.11 * MASS_EARTH, new Vector(1.405 * AU, 0), new Vector(0, -26500), "mars.png"),
				new ImagedBody("jupiter", 317.8 * MASS_EARTH, new Vector(5.034 * AU, 0), new Vector(0, -13720),
						"jupiter.png"),
				new ImagedBody("saturn", 95.2 * MASS_EARTH, new Vector(9.2 * AU, 0), new Vector(0, -10180),
						"saturn.png"),
				new ImagedBody("uranus", 14.5 * MASS_EARTH, new Vector(18.64 * AU, 0), new Vector(0, -7100),
						"uranus.png"),
				new ImagedBody("neptune", 17.1 * MASS_EARTH, new Vector(29.81 * AU, 0), new Vector(0, -5500),
						"neptune.png"),
				new ImagedBody("pluto(we still love you)", 0.0025 * MASS_EARTH, new Vector(30.16 * AU, 0),
						new Vector(0, -6100), "pluto.png"),
				new ImagedBody("halley", 2.2 * 10e14, new Vector(35.1 * AU, 0), new Vector(0, -897), "halley.png"));

		return simpleSolarSystem;
	}
}
