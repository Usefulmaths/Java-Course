package module8;

import java.util.ArrayList;
import java.util.List;

public class ThreadsTimer {
	public static void main(String[] args) throws InterruptedException {

		// Number of points to test.
		final int points = 10_000_000;

		// Single thread execution
		final double startTimerSingle = System.currentTimeMillis();
		final double estimateSinglePi = testWithOneThread(points);
		final double endTimerSingle = System.currentTimeMillis();

		// Multiple (four) threads execution
		final double startTimerMultiple = System.currentTimeMillis();
		final double estimateMultiplePi = testWithMultipleThreads(points, 4);
		final double endTimerMultiple = System.currentTimeMillis();

		// Prints all information needed to the console.
		System.out.println("Estimated Pi with single thread: " + estimateSinglePi);
		System.out.println("Execution time for single thread: " + (endTimerSingle - startTimerSingle) + "ms");

		System.out.println();

		System.out.println("Estimated Pi with four threads: " + estimateMultiplePi);
		System.out.println("Execution time for four threads: " + (endTimerMultiple - startTimerMultiple) + "ms");

		System.out.println();
		
		System.out.println("By distributing the work load over multiple CPU cores (by threading), it dramatically decreases the time in which the task is carried out. In this case, using four threads is around two to three times faster than using a single thread.");
	}

	// Runs MonteCarloPiCalculatorTask with single thread.
	public static double testWithOneThread(final int totalPointsCount) {
		MonteCarloPiCalculatorTask piCalc = new MonteCarloPiCalculatorTask(totalPointsCount);
		return piCalc.call();
	}

	// Runs multiple MonteCarloPiCalculatorTask in different threads.
	private static double testWithMultipleThreads(final int totalPointsCount, final int workCount)
			throws InterruptedException {

		// Evenly distributes work loads across threads.
		final int pointCountPerWorker = totalPointsCount / workCount;

		// ArrayLists to hold our threads and runnables.
		final List<Thread> threads = new ArrayList<>();
		final List<MonteCarloRunnable> monteCarloRunnables = new ArrayList<>();

		// For all workers, make a thread and runnable and start running.
		for (int i = 0; i < workCount; i++) {
			final MonteCarloRunnable monteCarloRunnable = new MonteCarloRunnable(pointCountPerWorker);
			final Thread thread = new Thread(monteCarloRunnable);

			monteCarloRunnables.add(monteCarloRunnable);
			threads.add(thread);

			thread.start();
		}

		// For all threads, wait until they finish
		for (final Thread thread : threads) {
			thread.join();
		}

		// Return the average pi estimate using functional programming.
		return monteCarloRunnables.stream()
				.mapToDouble(MonteCarloRunnable::getEstimate)
				.average()
				.orElse(-1);
	}

	// A runnable to run MonteCarloPiCalcuatorTask and to retrieve the estimates.
	private static  class MonteCarloRunnable implements Runnable {
		private final int limit;
		private double estimate;

		public MonteCarloRunnable(final int limit) {
			this.limit = limit;
		}

		@Override
		public void run() {
			this.estimate = new MonteCarloPiCalculatorTask(limit).call();
		}

		public double getEstimate() {
			return estimate;
		}
	}
}
