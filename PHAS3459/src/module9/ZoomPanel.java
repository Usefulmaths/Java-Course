package module9;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class ZoomPanel extends JPanel {
	public ZoomPanel(Callback<Integer> callback) {
		final JSlider slider = new JSlider(0, 1000, 1);
		slider.addChangeListener(e -> {
			callback.apply(1 + slider.getValue() / 100);
		});
		add(slider);
	}
}
