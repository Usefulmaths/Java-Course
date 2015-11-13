package midtermexams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Main {
	public static void main(String[] args) {
		ArrayList<Exoplanet> exoplanets = null;

		BufferedReader readInDataBR = null;

		try {
			readInDataBR = readFromUrl("http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/exoplanets.txt");
		} catch (IOException e) {
			System.out.println("Error parsing in URL.");
		}

		try {
			exoplanets = createArraysFromData(readInDataBR);
			final Exoplanet minimumDistanceExoplanet = filterByDistance(exoplanets);
			System.out.println(minimumDistanceExoplanet + "\n");

			filterByMethod(exoplanets);

		} catch (IOException e) {
			System.out.println("Error in reading next line in BufferedReader.");
		}

	}

	public static BufferedReader readFromUrl(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream stream = url.openStream();
		final InputStreamReader isr = new InputStreamReader(stream);

		return new BufferedReader(isr);

	}

	public static ArrayList<Exoplanet> createArraysFromData(final BufferedReader br) throws IOException {
		final ArrayList<Exoplanet> exoplanets = new ArrayList<Exoplanet>();

		String line;

		// Skips the headers
		br.readLine();

		while ((line = br.readLine()) != null) {
			String[] exoplanetDetails = line.split(",");

			final String name = exoplanetDetails[0];
			final int yearOfDiscovery = Integer.parseInt(exoplanetDetails[1]);
			final String methodOfDiscovery = exoplanetDetails[2];
			final double mass = Double.parseDouble(exoplanetDetails[3]);
			final double separation = Double.parseDouble(exoplanetDetails[4]);
			double distance;

			if (exoplanetDetails.length == 6) {

				distance = Double.parseDouble(exoplanetDetails[5]);
			} else {
				distance = 0;
			}

			final Exoplanet exoplanet = new Exoplanet(name, yearOfDiscovery, methodOfDiscovery, mass, separation, distance);
			exoplanets.add(exoplanet);
		}
		return exoplanets;
	}

	public static Exoplanet filterByDistance(final ArrayList<Exoplanet> exoplanets) {
		double minimumDistance = Double.POSITIVE_INFINITY;
		int position = 0;
		int positionOfMinimum = 0;

		for (final Exoplanet p : exoplanets) {
			if (p.getDistance() == 0) {

			} else {
				if (p.getDistance() < minimumDistance) {
					minimumDistance = p.getDistance();
					positionOfMinimum = position;
				}
			}
			position++;
		}
		return exoplanets.get(positionOfMinimum);
	}

	public static void filterByMethod(final ArrayList<Exoplanet> exoplanets) {
		final HashMap<String, Integer> methodHashMap = new HashMap<String, Integer>();

		for (final Exoplanet p : exoplanets) {
			methodHashMap.put(p.getMethodOfDiscovery(), 0);
		}

		for (final String method : methodHashMap.keySet()) {
			System.out.println("Number discovered by " + method + ": " + countByMethod(exoplanets, method));
			System.out
					.println("Earliest year of discovery by " + method + ": " + earliestYearMethod(exoplanets, method));
			System.out.println(
					"Lightest planet discovered by: " + method + ": " + lightestMassByDiscovery(exoplanets, method) + "\n");
		}

	}

	public static int countByMethod(final ArrayList<Exoplanet> exoplanets, final String method) {
		int count = 0;
		for (final Exoplanet p : exoplanets) {
			if (p.getMethodOfDiscovery().compareTo(method) == 0) {
				count++;
			}
		}
		return count;
	}

	public static int earliestYearMethod(final ArrayList<Exoplanet> exoplanets, final String method) {
		int earliestYear = Integer.MAX_VALUE;

		for (final Exoplanet p : exoplanets) {
			if (p.getMethodOfDiscovery().compareTo(method) == 0) {
				if (p.getYearOfDiscovery() < earliestYear) {
					earliestYear = p.getYearOfDiscovery();
				}
			}
		}
		return earliestYear;
	}

	public static Exoplanet lightestMassByDiscovery(final ArrayList<Exoplanet> exoplanets, final String method) {
		double lightestMass = Double.POSITIVE_INFINITY;
		int position = 0;
		int lightestPosition = 0;

		for (final Exoplanet p : exoplanets) {
			if (p.getMethodOfDiscovery().compareTo(method) == 0) {
				if (p.getMass() < lightestMass) {
					lightestMass = p.getMass();
					lightestPosition = position;
				}
			}
			position++;
		}

		return exoplanets.get(lightestPosition);

	}

}
