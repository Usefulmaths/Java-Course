package module9;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SolarSystemPanel extends JPanel implements ActionListener {

	private int width;
	private int height;
	private double theta = 0;
	double thetaDisplay = 0;

	private Timer animationTimer;

	public SolarSystemPanel(int width, int height) {
		this.width = width;
		this.height = height;

		setPreferredSize(new Dimension(width, height));

		animationTimer = new Timer(50, this);
		animationTimer.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		SpacialBody sun = new SpacialBody(width / 2, height / 2, 5 * 8);
		Planets mercury = new Planets(width / 2, height / 2, 8 * 0.383, 300 * 0.387, sun);
		Planets venus = new Planets(width / 2, height / 2, 8 * 0.949, 300 * 0.723, sun);
		Planets earth = new Planets(width / 2, height / 2, 8, 300, sun);
		Planets mars = new Planets(width / 2, height / 2, 8 * 0.532, 300 * 1.52, sun);

		mercury.setX((int) (width / 2 + mercury.getDistanceFromSun() * Math.cos(theta * mercury.getAngularVelocity())));
		mercury.setY((int) (width / 2 + mercury.getDistanceFromSun() * Math.sin(theta * mercury.getAngularVelocity())));

		venus.setX((int) (width / 2 + venus.getDistanceFromSun() * Math.cos(theta * venus.getAngularVelocity())));
		venus.setY((int) (width / 2 + venus.getDistanceFromSun() * Math.sin(theta * venus.getAngularVelocity())));

		earth.setX((int) (width / 2 + earth.getDistanceFromSun() * Math.cos(theta * earth.getAngularVelocity())));
		earth.setY((int) (width / 2 + earth.getDistanceFromSun() * Math.sin(theta * earth.getAngularVelocity())));

		mars.setX((int) (width / 2 + mars.getDistanceFromSun() * Math.cos(theta * mars.getAngularVelocity())));
		mars.setY((int) (width / 2 + mars.getDistanceFromSun() * Math.sin(theta * mars.getAngularVelocity())));

		drawPlanet(g, sun, Color.YELLOW);
		drawPlanet(g, mercury, Color.ORANGE);
		drawPlanet(g, venus, Color.PINK);
		drawPlanet(g, earth, Color.GREEN);
		drawPlanet(g, mars, Color.RED);

		g.drawString(
				"Years elapsed: " + Double
						.toString(Math.round(1.00 * (theta * earth.getAngularVelocity()) / (2 * Math.PI) * 1000) / 1000),
				75, 75);

	}

	private void drawPlanet(Graphics g, SpacialBody planet, Color color) {
		g.setColor(color);
		g.fillOval((int) (planet.getX() - planet.getRadius()), (int) (planet.getY() - planet.getRadius()),
				(int) planet.getRadius() * 2, (int) planet.getRadius() * 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		theta += 365;

		repaint();
	}

	/** Start the animation */
	public void start() {
		animationTimer.start();
	}

	/** Stop the animation */
	public void stop() {
		animationTimer.stop();
	}
}
