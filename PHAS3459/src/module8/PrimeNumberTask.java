package module8;

public class PrimeNumberTask implements Runnable {

	@Override
	public void run() {
		int currentNumber = 2;
		int totalNumberOfPrimes = 0;
		int largestPrimeFound = 0;
		boolean isRunning = true;

		while (isRunning) {

			// Time to check whether number is prime is longer than it takes to
			// flick through all different numbers, therefore
			// largestIntegerChecked and largestPrimeNumber are equal most of
			// the time
			if (Thread.currentThread().isInterrupted()) {
				isRunning = false;

				// Largest number is one before currentNumber
				int largestIntegerChecked = currentNumber - 1;

				System.out.println("The largest integer checked: " + largestIntegerChecked);
				System.out.println("The largest prime number found: " + largestPrimeFound);
				System.out.println("Total number of primes found: " + totalNumberOfPrimes);

			}
			// Set largest prime to current number if a prime.
			// Adds one to totalNumberOfPrimes to count primes.
			if (isPrimeNumber(currentNumber)) {
				largestPrimeFound = currentNumber;
				totalNumberOfPrimes++;
			}
			currentNumber++;
		}
	}

	private static boolean isPrimeNumber(final int i) {
		boolean isPrime = true;

		// Tests all numbers up to sqrt of that number to see if divisor.
		for (int j = 2; j <= Math.sqrt(i); j++) {
			if (i % j == 0) {
				// If divisor found, immediately break the for loop.
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}
}
