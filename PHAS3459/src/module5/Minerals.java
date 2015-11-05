package module5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class Minerals {
	private final HashMap<Integer, Double> hashMapMass = new HashMap<Integer, Double>();
	private final HashMap<Integer, String> hashMapLocation = new HashMap<Integer, String>();

	public static void main(String[] args) {
		final Minerals minerals = new Minerals();

		minerals.importMass("http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-samples.txt");
		minerals.importLocation("http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-locations.txt");

		final int minimumMassCode = minerals.minimumMassCode();
		final int maximumMassCode = minerals.maximumMassCode();

		minerals.informationAboutCode(minimumMassCode);
		minerals.informationAboutCode(maximumMassCode);
	}

	private static Scanner urlToBr(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream stream = url.openStream();
		return new Scanner(stream);
	}

	private void importMass(final String url) {
		Scanner scannerMass = null;
		try {
			scannerMass = urlToBr(url);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		while (scannerMass.hasNext()) {
			final int code = scannerMass.nextInt();
			final double mass = scannerMass.nextDouble();

			hashMapMass.put(code, mass);
		}
	}

	private void importLocation(final String url) {
		Scanner scannerLocation = null;
		try {
			scannerLocation = urlToBr(url);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		while (scannerLocation.hasNext()) {
			final String location = scannerLocation.next("[A-Za-z]*");
			final int code = scannerLocation.nextInt();

			hashMapLocation.put(code, location);
		}
	}

	private int minimumMassCode() {
		int codeOfValue = 0;
		double minValue = Double.POSITIVE_INFINITY;

		for (HashMap.Entry<Integer, Double> mineral : hashMapMass.entrySet()) {
			if (mineral.getValue() < minValue) {
				minValue = mineral.getValue();
				codeOfValue = mineral.getKey();
			}
		}
		return codeOfValue;
	}

	private int maximumMassCode() {
		int codeOfValue = 0;
		double maxValue = Double.NEGATIVE_INFINITY;

		for (HashMap.Entry<Integer, Double> mineral : hashMapMass.entrySet()) {
			if (mineral.getValue() > maxValue) {
				maxValue = mineral.getValue();
				codeOfValue = mineral.getKey();
			}
		}
		return codeOfValue;
	}

	private void informationAboutCode(final int code) {
		System.out.println("Code Number: " + code + "\n" + "Mass: " + this.hashMapMass.get(code) + "\n" + "Location: "
				+ this.hashMapLocation.get(code) + "\n");
	}
}
