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
		
		final List<ScalableBody> scalableBodies = new ArrayList<>();
		
		final Body centralBody = new Body("sun", 333054 * MASS_EARTH, new Vector(0, 0), new Vector(0, 0));

		final List<ScalableBody> simpleSolarSystem = createSimpleSolarSystem(centralBody);
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
	
	
	private static List<ScalableBody> createSimpleSolarSystem(final Body centralBody){
		final int defaultScaleFactor = 400;

		final Vector initialEarthPosition = new Vector(0.9833 * AU, 0);
		final Vector initialEarthVelocity = new Vector(0, -30290);
		
		List<ScalableBody> bodies = Arrays.asList(
				new ScalableBody(
						BodyFactory.create(centralBody.name, centralBody.mass, centralBody.position, centralBody.velocity, imageURL("sun.png")),
						dimensionsFactory.square(1200)
				),
				new ScalableBody(
						BodyFactory.create("mercury", 0.0553 * MASS_EARTH, new Vector(0.313 * AU, 0), new Vector(0, -58980), imageURL("mercury.png")),
						dimensionsFactory.square(300)
				),
				new ScalableBody(
						BodyFactory.create("venus", 0.815 * MASS_EARTH, new Vector(0.731 * AU, 0), new Vector(0, -35260),imageURL("venus.png")),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						BodyFactory.create("earth", MASS_EARTH, initialEarthPosition, initialEarthVelocity, imageURL("earth.png")),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						BodyFactory.create("moon", 0.0123 * MASS_EARTH, initialEarthPosition.add(new Vector(0.00247 * AU, 0)), initialEarthVelocity.add(new Vector(0, -1076)),imageURL("moon.png")),
						dimensionsFactory.square(100)
				),
				new ScalableBody(
						BodyFactory.create("mars", 0.11 * MASS_EARTH, new Vector(1.405 * AU, 0), new Vector(0, -26500), imageURL("mars.png")),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						BodyFactory.create("jupiter", 317.8 * MASS_EARTH, new Vector(5.034 * AU, 0), new Vector(0, -13720), imageURL("jupiter.png")),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						BodyFactory.create("saturn", 95.2 * MASS_EARTH, new Vector(9.2 * AU, 0), new Vector(0, -10180), imageURL("saturn.png")),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						BodyFactory.create("uranus", 14.5 * MASS_EARTH, new Vector(18.64 * AU, 0), new Vector(0, -7100), imageURL("uranus.png")),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						BodyFactory.create("neptune", 17.1 * MASS_EARTH, new Vector(29.81 * AU, 0), new Vector(0, -5500), imageURL("neptune.png")),
						dimensionsFactory.square(800)
				),
				new ScalableBody(
						BodyFactory.create("pluto (we still love you)", 0.0025 * MASS_EARTH, new Vector(30.16 * AU, 0), new Vector(0, -6100),imageURL("pluto.png")),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						BodyFactory.create("halley's Comet", 2.2 * 10e14, new Vector(35.1 * AU, 0), new Vector(0, -897), imageURL("tempel_1.png")),
						dimensionsFactory.square(defaultScaleFactor)
				),
				new ScalableBody(
						BodyFactory.create("tempel 1 (Comet)", 7.2e13, new Vector(0, 1.5 * AU), new Vector(30050, 0), imageURL("tempel_1.png")),
						dimensionsFactory.square(defaultScaleFactor)
				));
				
				return bodies;

	}
	
	private static List<ScalableBody> createAsteroids(final int numberOfAsteroids, final Body centralBody) {
		
		final List<ScalableBody> asteroids = new ArrayList<>();
		final Dimensions asteroidScaleFactor = dimensionsFactory.square(250);
		
		while (asteroids.size() < numberOfAsteroids) {
			final Body body = new Body("asteroid", 18.0e8 + 1e11 * Math.random(),
					new Vector(-4 * AU + 8 * AU * Math.random(), -4 * AU + 8 * AU * Math.random()), new Vector(0, 0));

			body.velocity = velocityPerpendicularToPosition(body, centralBody);

			if (circleBandConstraint(body.position, 2.2 * AU, 3.2 * AU)) {
				asteroids.add(new ScalableBody(body, asteroidScaleFactor));
			}
		}
		
		return asteroids;
	}
	
	private static List<ScalableBody> createImagedAsteroids(final int numberOfAsteroids, final Body centralBody, final BufferedImage image) throws IOException {

		final List<ScalableBody> asteroids = new ArrayList<>();
		final Dimensions asteroidScaleFactor = dimensionsFactory.square(250);
		
		while (asteroids.size() < numberOfAsteroids) {
			final Body body = new ImagedBody("asteroid", 18.0e8 + 1e11 * Math.random(),
					new Vector(-4 * AU + 8 * AU * Math.random(), -4 * AU + 8 * AU * Math.random()), new Vector(0, 0),image);

			body.velocity = velocityPerpendicularToPosition(body, centralBody);

			if (circleBandConstraint(body.position, 2.2 * AU, 3.2 * AU)) {
				asteroids.add(new ScalableBody(body, asteroidScaleFactor));
			}
		}
		
		return asteroids;
	}

	private static List<ScalableBody> addAsteroidBelt(final int numberOfAsteroids, final Body centralBody) {
		
		
		final ScalableBody ceres = new ScalableBody(
				BodyFactory.create("ceres", 9.393e20, new Vector(2.9773 * AU, 0), new Vector(0, -17482), imageURL("ceres.png")),
				dimensionsFactory.square(400)
		);
		
		final ScalableBody pallas = new ScalableBody(
				BodyFactory.create("pallas", 2.11e20, new Vector(0, 3.412605509 * AU), new Vector(15050, 0), imageURL("pallas.png")),
				dimensionsFactory.square(400)
		);
				
		final ScalableBody vesta = new ScalableBody(
				BodyFactory.create("vesta", 2.59076e20, new Vector(-2.57138 * AU, 0), new Vector(0, 17340), imageURL("vesta.png")),
				dimensionsFactory.square(400)
		);
		
		final ScalableBody hygiea = new ScalableBody(
				BodyFactory.create("hygiea", 8.67e19, new Vector(0, -3.5024 * AU), new Vector(-13760, 0), imageURL("hygiea.png")),
				dimensionsFactory.square(400)
		);
		
		try {
			System.out.println("Receiving images from GitHub: asteroid1.png");
			final BufferedImage asteroidImage = ImageIO
				.read(new URL(imageURL("asteroid1.png")));
	
			
			final List<ScalableBody> asteroids = createImagedAsteroids(numberOfAsteroids, centralBody, asteroidImage);
					asteroids.addAll(Arrays.asList(
							ceres, pallas, vesta, hygiea
					));
			return asteroids;
		} catch (IOException e) {
			System.out.println("Could not receive image, defaulting to circle.");
			
			final List<ScalableBody> asteroids = createAsteroids(numberOfAsteroids, centralBody);
			
			asteroids.addAll(Arrays.asList(ceres, pallas, vesta, hygiea));
			
			return asteroids;
		}
	}

	private static String imageURL(String name){
		return "https://raw.githubusercontent.com/Usefulmaths/module9images/master/" + name;
	}
}
