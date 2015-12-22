package module9;

import static module9.Constants.AU;
import static module9.Constants.MASS_EARTH;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
			List<ImagedBody> asteroids = addAsteroidBelt(150, centralBody);

			bodies.addAll(simpleSolarSystem);
			bodies.addAll(asteroids);

			SolarSystem solarSystem = new SolarSystem(bodies);
			final SolarSystemView solarSystemView = new SolarSystemView(solarSystem);
			container.add(solarSystemView);
			
			BufferedImage img = ImageIO.read(new File("starbackground.png"));
			JLabel background = new JLabel(new ImageIcon(img));
			
			
			final JPanel widgets = new JPanel(new FlowLayout(FlowLayout.CENTER));
			container.add(widgets, BorderLayout.SOUTH);
			final JLabel timerLabel = new JLabel(Double.toString(solarSystem.timer));
			widgets.add(new JPanel(new GridLayout()) {
				{
					add(new JLabel("Time elapsed (years): "));
					add(timerLabel);
				}
			}, BorderLayout.NORTH);

			final ZoomPanel zoomPanel = new ZoomPanel((value) -> solarSystemView.zoom(value));
			final JLabel zoomLabel = new JLabel("Zoom: ");
			widgets.add(zoomLabel);
			widgets.add(zoomPanel);

			final JRadioButton toggleRadioButton = new JRadioButton();
			final JLabel nameLabel = new JLabel("Toggle Names: ");
			ItemListener toggleNames = solarSystemView.toggleNames();
			toggleRadioButton.addItemListener(toggleNames);
			widgets.add(nameLabel);
			widgets.add(toggleRadioButton);

			// Timer stuff to get the application running
			final int fps = 60;
			final int updateFrequency = 60;

			final Timer updateTimer = new Timer(1000 / updateFrequency, e -> {
				solarSystem.tick();

				final int days = (int) solarSystem.timer;

				final int years = (int) (days / 365.24);
				final int months = (int) (days % 365.24 / (365.24 / 12));

				final String time = String.format("%d years, %d months", years, months);
				timerLabel.setText(time);
			});
			// setTimer(updateFrequency,
			// solarSystem,
			// solarSystemView);
			updateTimer.start();

			final Timer drawTimer = new Timer(1000 / fps, e -> solarSystemView.draw());
			drawTimer.start();

			container.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
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

		BufferedImage asteroidImage = ImageIO
				.read(new URL("https://raw.githubusercontent.com/Usefulmaths/module9images/master/asteroid1.png"));
		System.out.println("Receiving images from GitHub: " + "asteroid1.png");

		while (asteroids.size() < numberOfAsteroids) {
			ImagedBody body = new ImagedBody("asteroid", 18.0e8 + 1e11 * Math.random(),
					new Vector(-4 * AU + 8 * AU * Math.random(), -4 * AU + 8 * AU * Math.random()), new Vector(0, 0));
			body.image = asteroidImage;

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

		Vector initialEarthPosition = new Vector(0.9833 * AU, 0);
		Vector initialEarthVelocity = new Vector(0, -30290);

		List<ImagedBody> simpleSolarSystem = Arrays.asList(centralBody,
				new ImagedBody("mercury", 0.0553 * MASS_EARTH, new Vector(0.313 * AU, 0), new Vector(0, -58980),
						"mercury.png"),
				new ImagedBody("venus", 0.815 * MASS_EARTH, new Vector(0.731 * AU, 0), new Vector(0, -35260),
						"venus.png"),
				new ImagedBody("earth", MASS_EARTH, initialEarthPosition, initialEarthVelocity, "earth.png"),

				new ImagedBody("moon", 0.0123 * MASS_EARTH, initialEarthPosition.add(new Vector(0.00247 * AU, 0)),
						initialEarthVelocity.add(new Vector(0, -1076)), "moon.png"),

				new ImagedBody("mars", 0.11 * MASS_EARTH, new Vector(1.405 * AU, 0), new Vector(0, -26500), "mars.png"),
				new ImagedBody("jupiter", 317.8 * MASS_EARTH, new Vector(5.034 * AU, 0), new Vector(0, -13720),
						"jupiter.png"),
				new ImagedBody("saturn", 95.2 * MASS_EARTH, new Vector(9.2 * AU, 0), new Vector(0, -10180),
						"saturn.png"),
				new ImagedBody("uranus", 14.5 * MASS_EARTH, new Vector(18.64 * AU, 0), new Vector(0, -7100),
						"uranus.png"),
				new ImagedBody("neptune", 17.1 * MASS_EARTH, new Vector(29.81 * AU, 0), new Vector(0, -5500),
						"neptune.png"),
				new ImagedBody("pluto (we still love you)", 0.0025 * MASS_EARTH, new Vector(30.16 * AU, 0),
						new Vector(0, -6100), "pluto.png"),
				new ImagedBody("halley's Comet", 2.2 * 10e14, new Vector(35.1 * AU, 0), new Vector(0, -897),
						"tempel_1.png"),
				new ImagedBody("tempel 1 (Comet)", 7.2e13, new Vector(0, 1.5 * AU), new Vector(30050, 0),
						"tempel_1.png"));

		return simpleSolarSystem;
	}

	private static Timer setTimer(int framesPerSecond, SolarSystem solarSystem, SolarSystemView solarSystemView) {
		final int updateDelay = 1000 / framesPerSecond;
		return new Timer(updateDelay, (e) -> {
			solarSystem.tick();
			solarSystemView.draw();
		});
	}
}
