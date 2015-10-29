package module4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NumericalReader {
	private static double minValue;
	private static double maxValue;
	private static int nValues;
	private static double sumOfValues;
	private static ArrayList<String> keepTrackOfNumbers = new ArrayList();
	private File file;
	private FileWriter fw;

	public static void main(String[] args) {
		numbers1();
		numbers2();

	}

	private static void numbers1() {
		NumericalReader nr = new NumericalReader();
		BufferedReader brNumbers1 = null;

		try {
			brNumbers1 = brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt");
		} catch (IOException e) {
			System.out.println("Error reading in from BufferedReader: " + e);
		}

		String dir;
		dir = getOutputDirectory();

		try {
			nr.analysisStart(dir + File.separator + "numbers1.txt");
		} catch (IOException e) {
			System.out.println("Error creating number file: " + e);
		}

		String line;
		try {
			while ((line = brNumbers1.readLine()) != null) {
				try {
					nr.analyseData(line);
				} catch (IOException e) {
					System.out.println("Error writing to number file: " + e);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading line via BufferedReader: " + e);
		}
		updateVariblesFromArray(keepTrackOfNumbers);

		nr.analysisEnd();
	}

	private static void numbers2() {
		NumericalReader nr = new NumericalReader();
		BufferedReader brNumbers1 = null;

		try {
			brNumbers1 = brFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data2.txt");
		} catch (IOException e) {
			System.out.println("Error reading in from BufferedReader: " + e);
		}

		String dir;
		dir = getOutputDirectory();

		try {
			nr.analysisStart(dir + File.separator + "numbers2.txt");
		} catch (IOException e) {
			System.out.println("Error creating number file: " + e);
		}

		String line;
		try {
			while ((line = brNumbers1.readLine()) != null) {
				try {
					nr.analyseData(line);
				} catch (IOException e) {
					System.out.println("Error writing to number file: " + e);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading line via BufferedReader: " + e);
		}
		updateVariblesFromArray(keepTrackOfNumbers);

		nr.analysisEnd();
	}

	private static String getOutputDirectory() {

		System.out.print("Enter directory location: ");
		Scanner scanner = new Scanner(System.in);
		String location = scanner.nextLine();
		if (location != "this") {
			return location;
		} else {
			location = System.getProperty("user.home");
			return location;
		}

	}

	private static BufferedReader brFromURL(String urlName) throws IOException {
		URL u = new URL(urlName);
		InputStream in = u.openStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);

		return br;

	}

	private void analysisStart(String dataFile) throws IOException {
		file = new File(dataFile);
		fw = new FileWriter(file);

		minValue = 0;
		maxValue = 0;
		nValues = 0;
		sumOfValues = 0;

		keepTrackOfNumbers.removeAll(keepTrackOfNumbers);
	}

	private void analyseData(String line) throws IOException {
		if (!line.matches("([a-zA-Z].*)|\\W")) {

			Scanner scanner = new Scanner(line);
			BufferedWriter bw = new BufferedWriter(fw);

			while (scanner.hasNext()) {
				String str = scanner.next();

				keepTrackOfNumbers.add(str);

				System.out.println(str);

				bw.write(str + "\n");

			}
			bw.flush();
		}

	}

	private static void updateVariblesFromArray(ArrayList list) {
		ArrayList<String> listString = list;
		ArrayList<Double> listDouble = new ArrayList();

		for (String i : listString) {
			double stringToDouble = Double.parseDouble(i);
			listDouble.add(stringToDouble);
		}

		minValue = Collections.min(listDouble);
		maxValue = Collections.max(listDouble);
		nValues = listDouble.size();
		sumOfValues = sumOfArray(listDouble);

	}

	private static double sumOfArray(ArrayList list) {
		ArrayList<Double> listDouble = list;
		double sum = 0;
		for (double i : listDouble) {
			sum += i;
		}

		return sum;
	}

	private void analysisEnd() {
		System.out.println("Minimum value: " + minValue);
		System.out.println("Maximum value: " + maxValue);
		System.out.println("Average value: " + sumOfValues / nValues);
		System.out.println("Number of values read: " + nValues);

		System.out.println(System.getProperty("user.home"));
	}

}
