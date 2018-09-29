package module5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

// This program does not utilise classes and there is nothing stated about reusability, so everything in this class is static.
public class Minerals {
	public static void main(String[] args) {
		// Reading in data from URLs and storing in appropriate HashMaps.
		final HashMap<Integer, Double> masses = importMasses(
				"http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-samples.txt");
		final HashMap<Integer, String> locations = importLocations(
				"http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-locations.txt");

		// Finds the maximum and minimum mass and retrieves its key.
		final int minimumMassCode = getMinimumMassCode(masses);
		final int maximumMassCode = getMaximumMassCode(masses);

		// Using the found keys, displays information on the mineral.
		informationAboutCode(minimumMassCode, masses.get(minimumMassCode), locations.get(minimumMassCode));
		System.out.println();
		informationAboutCode(maximumMassCode, masses.get(maximumMassCode), locations.get(maximumMassCode));
	}

	// Reads data from URL and returns scanner
	private static Scanner urlToScanner(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream stream = url.openStream();
		return new Scanner(stream);
	}

	// Puts ID + masses in a HashMap.
	private static HashMap<Integer, Double> importMasses(final String url) {
		final HashMap<Integer, Double> masses = new HashMap<>();
		try {
			final Scanner scannerMass = urlToScanner(url);
			while (scannerMass.hasNext()) {
				final int code = scannerMass.nextInt();
				final double mass = scannerMass.nextDouble();

				masses.put(code, mass);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return masses;
	}

	// Puts ID + locations in a HashMap.
	private static HashMap<Integer, String> importLocations(final String url) {
		final HashMap<Integer, String> locations = new HashMap<>();
		try {
			final Scanner scannerLocation = urlToScanner(url);
			while (scannerLocation.hasNext()) {
				final String location = scannerLocation.next();
				final int code = scannerLocation.nextInt();

				locations.put(code, location);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return locations;
	}

	// Finds ID of minimum value in HashMap
	private static int getMinimumMassCode(final HashMap<Integer, Double> masses) {
		int minValueCode = 0;
		double minValue = Double.POSITIVE_INFINITY;

		for (HashMap.Entry<Integer, Double> mineral : masses.entrySet()) {
			if (mineral.getValue() < minValue) {
				minValue = mineral.getValue();
				minValueCode = mineral.getKey();
			}
		}
		return minValueCode;
	}

	// Finds ID of maximum value in HashMap
	private static int getMaximumMassCode(final HashMap<Integer, Double> masses) {
		int codeOfValue = 0;
		double maxValue = Double.NEGATIVE_INFINITY;

		for (HashMap.Entry<Integer, Double> mineral : masses.entrySet()) {
			if (mineral.getValue() > maxValue) {
				maxValue = mineral.getValue();
				codeOfValue = mineral.getKey();
			}
		}
		return codeOfValue;
	}

	// Summarises content of a mineral.
	private static void informationAboutCode(final int code, final double mass, final String location) {
		System.out.println("Code Number: " + code);
		System.out.println("Mass: " + mass);
		System.out.println("Location: " + location);
	}
}
