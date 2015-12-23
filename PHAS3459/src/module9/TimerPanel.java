package module9;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerPanel extends JPanel {
	private final JLabel timerLabel;
	
	public TimerPanel(final String initialTime) {
		super(new GridLayout());
		
		add(new JLabel("Time elapsed (years): "));
		timerLabel = new JLabel(initialTime);
		add(timerLabel);
	}
	
	public void setTimeIndicator(final String time) {
		timerLabel.setText(time);
	}
}
