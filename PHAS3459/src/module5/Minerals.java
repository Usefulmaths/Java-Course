package module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Minerals {
	public static void main(String[] args) {

		HashMap<Integer, Double> hashMap1 = new HashMap<Integer, Double>();
		HashMap<Integer, String> hashMap2 = new HashMap<Integer, String>();

		try {
			BufferedReader br1 = urlToBr("http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-samples.txt");
			BufferedReader br2 = urlToBr("http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-locations.txt");

			Scanner scanner1 = new Scanner(br1);
			Scanner scanner2 = new Scanner(br2);

			while (scanner1.hasNext()) {
				String line = scanner1.nextLine();
				String[] pairArray = line.split("  ");

				hashMap1.put(Integer.parseInt(pairArray[0]), Double.parseDouble(pairArray[1]));

			}

			while (scanner2.hasNext()) {
				String line = scanner2.nextLine();
				String[] pairArray = line.split("  ");

				hashMap2.put(Integer.parseInt(pairArray[1]), pairArray[0]);
			}

			double minValue = Double.POSITIVE_INFINITY;
			double maxValue = Double.NEGATIVE_INFINITY;
			for (double key : hashMap1.values()) {
				if (key < minValue) {
					minValue = key;
				}
				if (key > maxValue) {
					maxValue = key;
				}
			}

			int minValueKey = 0;
			int maxValueKey = 0;
			for (Entry<Integer, Double> e : hashMap1.entrySet()) {
				if (e.getValue() == minValue) {
					minValueKey = e.getKey();
				} else if (e.getValue() == maxValue) {
					maxValueKey = e.getKey();
				}
			}

			if (hashMap1.containsKey(minValueKey) && hashMap2.containsKey(minValueKey)) {
				System.out.println("Code Number: " + minValueKey + "\nMass: " + minValue + "\nLocation: "
						+ hashMap2.get(minValueKey) + "\n");
			}

			if (hashMap1.containsKey(maxValueKey) && hashMap2.containsKey(maxValueKey)) {
				System.out.println("Code Number: " + maxValueKey + "\nMass: " + maxValue + "\nLocation: "
						+ hashMap2.get(maxValueKey));
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private static BufferedReader urlToBr(String urlString) throws IOException {
		URL url = new URL(urlString);
		InputStream stream = url.openStream();

		return new BufferedReader(new InputStreamReader(stream));
	}

}
