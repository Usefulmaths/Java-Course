package module9;

import static module9.Constants.AU;
import static module9.Constants.MASS_EARTH;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Main {
	
	private static final DimensionsFactory dimensionsFactory = new DimensionsFactory();

	public static void main(String[] args) {
		
		try {
			final List<ScalableBody> scalableBodies = new ArrayList<>();
			
			final ImagedBody centralBody = new ImagedBody("sun", 333054 * MASS_EARTH, new Vector(0, 0), new Vector(0, 0), "sun.png");

			final List<ScalableBody> simpleSolarSystem = retrieveSimpleSolarSystem(centralBody);
			final List<ScalableBody> asteroids = addAsteroidBelt(150, centralBody);

			scalableBodies.addAll(simpleSolarSystem);
			scalableBodies.addAll(asteroids);

			final int secondsInDay = 60 * 60 * 24;
			
			final List<Body> justTheBodies = scalableBodies.stream()
					.map(ScalableBody::getBody)
					.collect(Collectors.toList());
			
			final SolarSystem solarSystem = new SolarSystem(justTheBodies, secondsInDay);
			
			final Container container = setupContainer(solarSystem, scalableBodies);
			
			startTimers(solarSystem, container);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Container setupContainer(final SolarSystem solarSystem, final List<ScalableBody> scalableBodies) {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		final double VIEW_WIDTH = screenSize.getWidth();
		final double VIEW_HEIGHT = screenSize.getHeight();

		final Container container = new Container("Simple Solar System.", solarSystem, scalableBodies);
		container.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		container.setSize((int) VIEW_WIDTH, (int) VIEW_HEIGHT);
		container.setVisible(true);
		
		return container;
	}
	
	// Timer things to get the application running
	private static void startTimers(final SolarSystem solarSystem, final Container container) {
		final int fps = 60;
		final int updateFrequency = 60;

		final Timer updateTimer = new Timer(1000 / updateFrequency, e -> {
			solarSystem.tick();

			final int days = (int) solarSystem.getElapsedTicks();

			final int years = (int) (days / 365.24);
			final int months = (int) (days % 365.24 / (365.24 / 12));

			final String time = String.format("%d years, %d months", years, months);
			container.setTimeIndicator(time);
		});
		
		updateTimer.start();

		final Timer drawTimer = new Timer(1000 / fps, e -> container.draw());
		drawTimer.start();
	}

	private static boolean circleBandConstraint(Vector v, double bandMinimum, double bandMaximum) {
		if (bandMinimum < Math.sqrt(v.getX() * v.getX() + v.getY()* v.getY()) && Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY()) < bandMaximum) {
			return true;
		}
		return false;
	}

	private static Vector velocityPerpendicularToPosition(final Body body, final Body centralBody) {
		final double velocityAngle = -Math.PI / 2 + body.position.angle();
		final double velocity = Math.sqrt(Constants.G * centralBody.mass / body.separationVector(centralBody).magnitude());
		return new Vector(velocity * Math.cos(velocityAngle), velocity * Math.sin(velocityAngle));
	}

	private static List<ScalableBody> addAsteroidBelt(int numberOfAsteroids, final Body centralBody) throws IOException {
		final Dimensions asteroidScaleFactor = dimensionsFactory.square(150);
		
		final List<ScalableBody> asteroids = new ArrayList<>();

		final BufferedImage asteroidImage = ImageIO
				.read(new URL("https://raw.githubusercontent.com/Usefulmaths/module9images/master/asteroid1.png"));
		
		System.out.println("Receiving images from GitHub: asteroid1.png");

		// TODO: What does this loop do, and can it be a method?
		while (asteroids.size() < numberOfAsteroids) {
			final ImagedBody body = new ImagedBody("asteroid", 18.0e8 + 1e11 * Math.random(),
					new Vector(-4 * AU + 8 * AU * Math.random(), -4 * AU + 8 * AU * Math.random()), new Vector(0, 0));
			body.setImage(asteroidImage);

			body.velocity = velocityPerpendicularToPosition(body, centralBody);

			if (circleBandConstraint(body.position, 2.2 * AU, 3.2 * AU)) {
				asteroids.add(new ScalableBody(body, asteroidScaleFactor));
			}
		}

		final ScalableBody ceres = new ScalableBody(
				new ImagedBody("ceres", 9.393e20, new Vector(2.9773 * AU, 0), new Vector(0, -17482), "ceres.png"),
				asteroidScaleFactor
		);
		
		final ScalableBody pallas = new ScalableBody(
				new ImagedBody("pallas", 2.11e20, new Vector(0, 3.412605509 * AU), new Vector(15050, 0), "pallas.png"),
				asteroidScaleFactor
		);
				
		final ScalableBody vesta = new ScalableBody(
				new ImagedBody("vesta", 2.59076e20, new Vector(-2.57138 * AU, 0), new Vector(0, 17340), "vesta.png"),
				asteroidScaleFactor
		);
		
		final ScalableBody hygiea = new ScalableBody(
				new ImagedBody("hygiea", 8.67e19, new Vector(0, -3.5024 * AU), new Vector(-13760, 0), "hygiea.png"),
				asteroidScaleFactor
		);

		asteroids.add(ceres);
		asteroids.add(pallas);
		asteroids.add(vesta);
		asteroids.add(hygiea);

		return asteroids;
	}

	private static List<ScalableBody> retrieveSimpleSolarSystem(final Body centralBody) throws IOException {

		final Vector initialEarthPosition = new Vector(0.9833 * AU, 0);
		final Vector initialEarthVelocity = new Vector(0, -30290);
		
		final int defaultScaleFactor = 400;

		final List<ScalableBody> simpleSolarSystem = Arrays.asList(
				new ScalableBody(
						centralBody,
						dimensionsFactory.square(1200)
				),
				new ScalableBody(
						new ImagedBody("mercury", 0.0553 * MASS_EARTH, new Vector(0.313 * AU, 0), new Vector(0, -58980), "mercury.png"),
						dimensionsFactory.square(100)
				),
				new ScalableBody(
						new ImagedBody("venus", 0.815 * MASS_EARTH, new Vector(0.731 * AU, 0), new Vector(0, -35260), "venus.png"),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						new ImagedBody("earth", MASS_EARTH, initialEarthPosition, initialEarthVelocity, "earth.png"),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						new ImagedBody("moon", 0.0123 * MASS_EARTH, initialEarthPosition.add(new Vector(0.00247 * AU, 0)), initialEarthVelocity.add(new Vector(0, -1076)), "moon.png"),
						dimensionsFactory.square(100)
				),
				new ScalableBody(
						new ImagedBody("mars", 0.11 * MASS_EARTH, new Vector(1.405 * AU, 0), new Vector(0, -26500), "mars.png"),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						new ImagedBody("jupiter", 317.8 * MASS_EARTH, new Vector(5.034 * AU, 0), new Vector(0, -13720), "jupiter.png"),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						new ImagedBody("saturn", 95.2 * MASS_EARTH, new Vector(9.2 * AU, 0), new Vector(0, -10180), "saturn.png"),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						new ImagedBody("uranus", 14.5 * MASS_EARTH, new Vector(18.64 * AU, 0), new Vector(0, -7100), "uranus.png"),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						new ImagedBody("neptune", 17.1 * MASS_EARTH, new Vector(29.81 * AU, 0), new Vector(0, -5500), "neptune.png"),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						new ImagedBody("pluto (we still love you)", 0.0025 * MASS_EARTH, new Vector(30.16 * AU, 0), new Vector(0, -6100), "pluto.png"),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						new ImagedBody("halley's Comet", 2.2 * 10e14, new Vector(35.1 * AU, 0), new Vector(0, -897),"tempel_1.png"),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						new ImagedBody("tempel 1 (Comet)", 7.2e13, new Vector(0, 1.5 * AU), new Vector(30050, 0), "tempel_1.png"),
						dimensionsFactory.square(defaultScaleFactor)
				)
		);

		return simpleSolarSystem;
	}
}
