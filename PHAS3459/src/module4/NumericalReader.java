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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NumericalReader {

	private static double minValue;
	private static double maxValue;
	private static double sumOfValues;
	private static int nValues;
	private static ArrayList<String> keepTrackOfNumbers = new ArrayList<String>();
	private static String directory;
	private File file;
	private FileWriter fw;

	public static void main(String[] args) {

		// Creating two NumericalReader objects, one for each text file.
		NumericalReader nr1 = new NumericalReader();
		NumericalReader nr2 = new NumericalReader();

		// Getting directory from user, if none specified redirect to home
		// directory.
		try {
			directory = NumericalReader.getOutputDirectory();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			directory = System.getProperty("user.home");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			directory = System.getProperty("user.home");
		}

		// Runs code for the first text file.
		NumericalReader.runProgram(nr1, "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data1.txt",
				"numbers1.txt");

		// Run code for the second text file.
		NumericalReader.runProgram(nr2, "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_data2.txt",
				"numbers2.txt");

	}

	private static void runProgram(NumericalReader nr, String URL, String fileName) {

		BufferedReader brReadInNumbers = null;

		// Reads information from specified URL.
		try {
			brReadInNumbers = NumericalReader.brFromURL(URL);
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
		} catch (IOException e) {
			System.out.println("Error reading line via BufferedReader: " + e);
		}

		// Finds min, max, number of variables, and their sum.
		nr.updateVariblesFromArray(keepTrackOfNumbers);

		// Ends analysis, prints out results.
		nr.analysisEnd();
	}

	private static String getOutputDirectory() throws Exception, IOException {

		System.out.print("Enter directory location: ");
		Scanner scanner = new Scanner(System.in);
		String location = scanner.nextLine();

		if (location.equals("")) {
			throw new Exception(
					"Invalid directory name. Redirecting to home directory: " + System.getProperty("user.home"));
		} else {
			return location;
		}

	}

	private static BufferedReader brFromURL(String urlName) throws IOException, MalformedURLException {

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

	private void updateVariblesFromArray(ArrayList<String> list) {

		ArrayList<String> listString = list;
		ArrayList<Double> listDouble = new ArrayList<Double>();

		for (String i : listString) {
			double stringToDouble = Double.parseDouble(i);
			listDouble.add(stringToDouble);
		}

		minValue = Collections.min(listDouble);
		maxValue = Collections.max(listDouble);
		nValues = listDouble.size();
		sumOfValues = sumOfArray(listDouble);

	}

	private static double sumOfArray(ArrayList<Double> list) {

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
		System.out.println("Number of values read: " + nValues + "\n");
	}

}
