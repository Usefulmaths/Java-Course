package module8;

public class ThreadsMain {

	public static void main(String[] args) throws InterruptedException {
		// Instantiate both threads
		Thread countdownThread = new Thread(new CountdownTask(10));
		Thread primeNumberThread = new Thread(new PrimeNumberTask());

		// Start both threads
		countdownThread.start();
		primeNumberThread.start();

		// Once countdown finishes, interrupt primeNumberThread
		countdownThread.join();
		primeNumberThread.interrupt();
	}
}
