package module9;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class ZoomPanel extends JPanel {
    public ZoomPanel(Callback<Integer> callback) {
        final JSlider slider = new JSlider(1, 10, 1);
        slider.addChangeListener(e -> {
            callback.apply(slider.getValue()/2);
        });
        add(slider);
    }
}
