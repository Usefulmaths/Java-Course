package module9;

import java.util.function.IntConsumer;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class ZoomPanel extends JPanel {
	public ZoomPanel(final IntConsumer callback) {
		final JSlider slider = new JSlider(100, 10000, 1000);
		slider.addChangeListener(e -> {
			callback.accept(slider.getValue() / 100);	
		});
		add(slider);
	}
}
