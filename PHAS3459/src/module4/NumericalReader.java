package module4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NumericalReader {
	private static double minValue;
	private static double maxValue;
	private static double sumOfValues;
	private static int nValues;

	private static String directory;
	private FileWriter fw;

	public static void main(String[] args) {

		// Creating two NumericalReader objects, one for each text file.
		final NumericalReader nr1 = new NumericalReader();
		final NumericalReader nr2 = new NumericalReader();

		// Getting directory from user, if none specified redirect to home
		// directory.
		directory = NumericalReader.getOutputDirectory();

		// Runs code for the first text file.
		NumericalReader.runProgram(nr1, "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt",
				"numbers1.txt");

		// Run code for the second text file.
		NumericalReader.runProgram(nr2, "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data2.txt",
				"numbers2.txt");

	}

	private static void runProgram(final NumericalReader nr, final String url, final String fileName) {
		BufferedReader brReadInNumbers = null;

		// Reads information from specified URL.
		try {
			brReadInNumbers = NumericalReader.brFromURL(url);
		} catch (MalformedURLException e) {
			System.out.println("Error while parsing in URL: " + e);
		} catch (IOException e) {
			System.out.println("Error reading in from BufferedReader: " + e);
		}

		// Resets variables to default and creates file in user-specified
		// directory.
		String saveFile = directory + File.separator + fileName;
		try {
			nr.analysisStart(saveFile);
		} catch (IOException e) {
			System.out.println("Error creating number file: " + e);

		}

		// Performs necessary analysis on test files, writes data to file in
		// directory.
		String line;
		try {
			while ((line = brReadInNumbers.readLine()) != null) {
				try {
					nr.analyseData(line);
				} catch (IOException e) {
					System.out.println("Error writing to number file: " + e);
				}
			}
			brReadInNumbers.close();
		} catch (IOException e) {
			System.out.println("Error reading line via BufferedReader: " + e);
		}

		// Ends analysis, prints out results, and closes FileWriter.
		try {
			nr.analysisEnd();
		} catch (IOException e) {
			System.out.println("Error closing FileWriter: " + e.getMessage());
		}
	}

	private static String getOutputDirectory() {
		System.out.print("Enter directory location: ");
		final Scanner in = new Scanner(System.in);

		String location = in.nextLine();

		if (location.equals("")) {
			return System.getProperty("user.home");
		} else {
			return location;
		}

	}

	private static BufferedReader brFromURL(final String urlName) throws MalformedURLException, IOException {
		final URL url = new URL(urlName);
		final InputStream stream = url.openStream();
		return new BufferedReader(new InputStreamReader(stream));
	}

	private void analysisStart(String dataFile) throws IOException {
		final File file = new File(dataFile);
		fw = new FileWriter(file);

		minValue = Double.POSITIVE_INFINITY;
		maxValue = Double.NEGATIVE_INFINITY;
		nValues = 0;
		sumOfValues = 0;
	}

	private void analyseData(String line) throws IOException {
		if (line.isEmpty() || Character.isLetter(line.charAt(0))) {
			return;
		}

		final Scanner in = new Scanner(line);
		while (in.hasNextDouble()) {
			final double number = in.nextDouble();
			fw.write(number + "\n");
			System.out.println(number);

			// Tests whether number is less than minValue, if so assigns.
			if (number < minValue) {
				minValue = number;
			}

			// Tests whether number is more than maxValue, if so assigns.
			if (number > maxValue) {
				maxValue = number;
			}

			++nValues;
			sumOfValues += number;
		}

	}

	private void analysisEnd() throws IOException {
		fw.close();
		System.out.println("Minimum value: " + minValue);
		System.out.println("Maximum value: " + maxValue);
		System.out.println("Average value: " + sumOfValues / nValues);
		System.out.println("Number of values read: " + nValues + "\n");
	}

}
