package exam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class deals with reading in data from URL, stores them in appropriate
 * objects and carries out the necessary analysis for the tasks given.
 */
public class Main {

	// The base URL for accessing necessary files.
	private static String URL_BASE = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/2015-16/";

	public static void main(String[] args) {

		// ArrivalTime objects contains two different methods in calculating
		// arrival time.
		final ArrivalTime arrivalTimeByMaximumVoltage = new TimeOfMaximumVoltage();

		// Threshold of 1V
		final ArrivalTime arrivalTimeByThreshold = new TimeOfVoltageThreshold(1.0);

		try {
			// BufferedReaders that contain data read from URLs.
			final BufferedReader detectorsReader = readDataFromURL(URL_BASE + "detectors.txt");
			final BufferedReader signalsReader = readDataFromURL(URL_BASE + "signals.txt");

			// List that contains all Pulse information in data.
			final List<Pulse> pulses = createListOfSignals(signalsReader);

			// Map stores detector identifiers and distances from data.
			final Map<String, Double> detectorMap = createHashMapOfDetectors(detectorsReader);

			// DataAnalysis allows analysis to be done given the list of pulses
			// and detector map
			final DataAnalysis analysis = new DataAnalysis(pulses, detectorMap);

			// Calculates total number of pulses in data.
			final int totalNumberOfPulses = analysis.totalNumberOfPulses();

			// Calculates mean amplitude of all pulses combined.
			final double meanAmplitudeOfAllPulses = analysis.meanAmplitudeOfAllPulses();

			// Prints out the total number of pulses and the mean amplitude of
			// all pulses.
			System.out.println("The total number of pulses: " + totalNumberOfPulses);
			System.out.println("The mean amplitude of all pulses: " + meanAmplitudeOfAllPulses + " V");

			// Prints out a summary of properties for each detector in data.
			analysis.detectorSummary();

			// Print out the average speed of each pulse/ particles for each
			// detector.
			analysis.printSpeedOfParticlesUsingArrivalTime(arrivalTimeByThreshold);

			// DifferentArrivalTimes object to allow us to calculate differences
			// in arrival time calculation methods.
			final DifferentArrivalTimes differentArrivalTimes = new DifferentArrivalTimes(analysis,
					arrivalTimeByMaximumVoltage, arrivalTimeByThreshold);

			// Prints detector time with the maximum difference in arrival time
			// calculation methods.
			differentArrivalTimes.printMaximumDifferenceDetector();

		} catch (IOException e) {
			// If unable to access the URL
			System.out.println("Unable to access URL");
			e.printStackTrace();
		}

	}

	// Creates HashMap of detectors to store identifier and distance from source
	// using BufferedReader.
	private static Map<String, Double> createHashMapOfDetectors(final BufferedReader reader) {
		final Map<String, Double> detectorMap = new HashMap<>();

		reader
		.lines()
		.forEach(line -> {
				final Scanner scanner = new Scanner(line);

				final String identifier = scanner.next();
				final double distance = scanner.nextDouble();
				detectorMap.put(identifier, distance);
				
				scanner.close();
			});

		return detectorMap;
	}

	// Create list of Pulse objects using data from BufferedReader.
	private static List<Pulse> createListOfSignals(final BufferedReader reader) {
		final List<Pulse> pulses = new ArrayList<>();

		reader.lines().forEach(line ->
			{
				final Scanner scanner = new Scanner(line);

				final String identifier = scanner.next();
				final List<Voltage> voltages = new ArrayList<>();
				int timePosition = 0;

				while (scanner.hasNext()) {
					final double voltage = scanner.nextDouble();

					final Voltage voltageObject = new Voltage(timePosition, voltage);
					voltages.add(voltageObject);

					timePosition++;
				}
				
				scanner.close();

				pulses.add(new Pulse(identifier, voltages));
			});

		return pulses;
	}

	// Creates a BufferedReader of data from data from a URL.
	private static BufferedReader readDataFromURL(final String urlString) throws IOException {
		final URL url = new URL(urlString);
		final InputStream is = url.openStream();
		return new BufferedReader(new InputStreamReader(is));
	}
}
