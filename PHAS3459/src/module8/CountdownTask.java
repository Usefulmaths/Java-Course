package module8;

public class CountdownTask implements Runnable {
	private int numberOfSecondsCountDown;

	public CountdownTask(int numberOfSecondsCountDown) {
		this.numberOfSecondsCountDown = numberOfSecondsCountDown;
	}

	@Override
	public void run() {
		// Counts down from numberOfSecondsCountDown to 0
		while (numberOfSecondsCountDown >= 0) {
			System.out.println("Seconds remaining: " + numberOfSecondsCountDown);
			numberOfSecondsCountDown--;

			// Delays thread by a second each time.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return;
	}
}
