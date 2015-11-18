package exam1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Array of integer months
		final int months[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		try {
			// Reads in data from URL and creates a scanner
			final Scanner data = readInData(
					" http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2015-16/earthquakesCA1989.txt");

			// ArrayList of all the Earthquakes as an Earthquake object in the
			// data.
			final ArrayList<Earthquake> earthquakes = earthquakeArrayList(data);

			// Calls a method to retrieve the largest magnitude of Earthquake in
			// the data.
			final Earthquake earthquakeLargestMagnitude = largestMagnitude(earthquakes);

			System.out.println("Number of earthquakes recorded in the file: " + earthquakes.size());
			System.out
					.println("Details of earthquake with largest magnitude: \n\t" + earthquakeLargestMagnitude + "\n");

			// Calls a void method that prints out no. of earthquakes, deepest
			// earthquake, and most accurate depth for each month in data.
			filterByMonth(earthquakes, months);

		} catch (IOException e) {
			System.out.println("Error in parsing URL to stream: " + e.getMessage());
		}
	}

	// Reads in data into a Scanner.
	private static Scanner readInData(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream stream = url.openStream();
		return new Scanner(stream);
	}

	// Creates an ArrayList that stores Earthquake objects from a scanner.
	private static ArrayList<Earthquake> earthquakeArrayList(final Scanner data) {
		final ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

		// Skips past the two headers;
		data.nextLine();
		data.nextLine();

		while (data.hasNext()) {
			final String dataLine = data.nextLine();

			// Scanner is used to read through each line.
			final Scanner scanLine = new Scanner(dataLine);

			// Setting variables for each member variable to parse into an
			// Earthquake object.
			while (scanLine.hasNext()) {
				final int year = scanLine.nextInt();
				final int month = scanLine.nextInt();
				final int day = scanLine.nextInt();
				final int hour = scanLine.nextInt();
				final int minute = scanLine.nextInt();
				final double second = scanLine.nextDouble();
				final Time date = new Time(year, month, day, hour, minute, second);

				final double latitude = scanLine.nextDouble();
				final double longitude = scanLine.nextDouble();
				final double depth = scanLine.nextDouble();
				final double horizontalMajorAxis = scanLine.nextDouble();
				final double horizontalMinorAxis = scanLine.nextDouble();
				final int azimuth = scanLine.nextInt();
				final double depthError = scanLine.nextDouble();
				final Position position = new Position(latitude, longitude, depth, horizontalMajorAxis,
						horizontalMinorAxis, azimuth, depthError);

				final double magnitude = scanLine.nextDouble();
				final int ID = scanLine.nextInt();

				final Earthquake earthquake = new Earthquake(date, position, magnitude, ID);

				// Add the Earthquake objects into an ArrayList.
				earthquakes.add(earthquake);
			}
		}
		return earthquakes;
	}

	// Finds the Earthquake with the largest magnitude and returns that
	// Earthquake object.
	private static Earthquake largestMagnitude(final ArrayList<Earthquake> earthquakes) {

		double largestMagnitude = Double.NEGATIVE_INFINITY;
		int indexOfMagnitude = 0;
		int indexOfLargestMagnitude = 0;

		// Tests each earthquake and compares with largestMagnitude. If larger,
		// stores value. If not, rejects.
		for (Earthquake e : earthquakes) {
			if (e.getMagnitude() > largestMagnitude) {
				largestMagnitude = e.getMagnitude();
				indexOfLargestMagnitude = indexOfMagnitude;
			}
			indexOfMagnitude++;
		}

		return earthquakes.get(indexOfLargestMagnitude);
	}

	// Finds the number of earthquakes produced in a given month.
	private static int numberOfEarthquakesPerMonth(final ArrayList<Earthquake> earthquakes, final int month) {
		int count = 0;

		for (Earthquake e : earthquakes) {
			if (e.getDate().getMonth() == month) {
				count++;
			}
		}
		return count;
	}

	// Takes an ArrayList with all Earthquake data and splits into an ArrayList
	// of all earthquakes in a given month.
	private static ArrayList<Earthquake> earthquakesInMonth(final ArrayList<Earthquake> earthquakes, final int month) {
		final ArrayList<Earthquake> earthquakesInMonth = new ArrayList<Earthquake>();
		for (Earthquake e : earthquakes) {
			if (e.getDate().getMonth() == month) {
				earthquakesInMonth.add(e);
			}
		}
		return earthquakesInMonth;
	}

	// Finds the deepest earthquake in a given month and returns full details as
	// an Earthquake object.
	private static Earthquake deepestEarthquakePerMonth(final ArrayList<Earthquake> earthquakes, final int month) {
		final ArrayList<Earthquake> earthquakesInMonth = earthquakesInMonth(earthquakes, month);
		double deepestEarthquake = Double.NEGATIVE_INFINITY;
		int indexOfDepth = 0;
		int indexOfLargestDepth = 0;

		for (Earthquake e : earthquakesInMonth) {
			if (e.getPosition().getDepth() > deepestEarthquake) {
				deepestEarthquake = e.getPosition().getDepth();
				indexOfLargestDepth = indexOfDepth;
			}
			indexOfDepth++;
		}
		return earthquakesInMonth.get(indexOfLargestDepth);
	}

	// Finds the Earthquake with the most accurate depth measurement and returns
	// full details as an Earthquake object.
	private static Earthquake mostAccurateDepthEarthquakeMonth(final ArrayList<Earthquake> earthquakes,
			final int month) {
		final ArrayList<Earthquake> earthquakesInMonth = earthquakesInMonth(earthquakes, month);
		double mostAccurateDepth = Double.POSITIVE_INFINITY;
		int indexOfDepthError = 0;
		int indexOfMinimumDepthError = 0;

		for (Earthquake e : earthquakesInMonth) {
			if (e.getPosition().getDepthError() < mostAccurateDepth && e.getPosition().getDepthError() != -1) {
				mostAccurateDepth = e.getPosition().getDepthError();
				indexOfMinimumDepthError = indexOfDepthError;
			}
			indexOfDepthError++;
		}
		return earthquakesInMonth.get(indexOfMinimumDepthError);
	}

	// Runs over each month and executes monthly methods and prints details to
	// screen.
	private static void filterByMonth(final ArrayList<Earthquake> earthquakes, final int[] months) {
		for (int month : months) {
			final int numberOfEarthquakes = numberOfEarthquakesPerMonth(earthquakes, month);
			System.out.println("Number of Earthquakes in month " + month + ": " + numberOfEarthquakes);

			final Earthquake deepestEarthquakeMonth = deepestEarthquakePerMonth(earthquakes, month);
			System.out.println("Deepest Earthquake in month " + month + ": " + deepestEarthquakeMonth);

			final Earthquake mostAccurateDepthErrorEarthquake = mostAccurateDepthEarthquakeMonth(earthquakes, month);
			System.out.println("Most accurate depth error Earthquake in month " + month + ": "
					+ mostAccurateDepthErrorEarthquake + "\n");

		}
	}

}
