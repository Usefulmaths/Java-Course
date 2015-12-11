package module9;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SolarSystem extends JFrame {
	/** Create and display a JFrame containing a LinesPanel. */
	private static void createAndDisplayGui() {
		JFrame frame = new JFrame("Swing graphics example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SolarSystemPanel panel = new SolarSystemPanel(1200, 1200);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/** Call method to create and display GUI. */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndDisplayGui();
			}
		});
	}
}
