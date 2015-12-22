package module9;

import java.util.function.IntConsumer;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class ZoomPanel extends JPanel {
	public ZoomPanel(final IntConsumer callback) {
		final JSlider slider = new JSlider(0, 1000, 1);
		slider.addChangeListener(e -> {
			callback.accept(1 + slider.getValue() / 100);
		});
		add(slider);
	}
}
