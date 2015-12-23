package module9;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Container extends JFrame {
	private final List<ScalableBody> bodies;
	private SolarSystemView solarSystemView;
	private TimerPanel timerPanel;
	
	public Container(final String title, final SolarSystem solarSystem, final List<ScalableBody> bodies) {
		super(title);
		
		this.bodies = bodies;
		
		try {
			setupViews(solarSystem);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void draw() {
		solarSystemView.draw();
	}
	
	public void setTimeIndicator(final String time) {
		timerPanel.setTimeIndicator(time);
	}
	
	private void setupViews(final SolarSystem solarSystem) throws IOException {
		this.solarSystemView = new SolarSystemView(bodies);
		add(solarSystemView);
		
		final JPanel widgets = new JPanel(new FlowLayout(FlowLayout.CENTER));
		add(widgets, BorderLayout.SOUTH);
		
		timerPanel = new TimerPanel(Double.toString(
				solarSystem.getElapsedTicks() * solarSystem.getSecondsPerTick()
		));
		
		add(timerPanel, BorderLayout.NORTH);

		final ZoomPanel zoomPanel = new ZoomPanel((value) -> solarSystemView.zoom(value));
		final JLabel zoomLabel = new JLabel("Zoom: ");
		widgets.add(zoomLabel);
		widgets.add(zoomPanel);

		final JRadioButton toggleRadioButton = new JRadioButton();
		final JLabel nameLabel = new JLabel("Toggle Names: ");
		final ItemListener toggleNames = solarSystemView.toggleNames();
		toggleRadioButton.addItemListener(toggleNames);
		widgets.add(nameLabel);
		widgets.add(toggleRadioButton);
	}
}