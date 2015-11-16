package module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class DataAnalysis {

	public static void main(String[] args) {
		try {
			// New instance of an ArrayList to contain DataPoints.
			final ArrayList<DataPoint> dataPoints = dataFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-xy.txt");
			
			// Creates a quadratic and quartic function to test theory.
			final Theory theoryXSquared = new Theory(2);
			final Theory theoryXFourth = new Theory(4);

			// Finds chiSquared between both theory models and dataPoints.
			final double modelXSquare = goodnessOfFit(theoryXSquared, dataPoints);
			final double modelXFourth = goodnessOfFit(theoryXFourth, dataPoints);

			System.out.println("Goodness of fit for quadratic model: " + modelXSquare);
			System.out.println("Goodness of fit for quartic model: " + modelXFourth);

			// Depending on chiSquared values, chooses the best model.
			final String betterModel;
			
			if (modelXSquare < modelXFourth) {
				betterModel = "quadratic";
			} else {
				betterModel = "quartic";
			}
			System.out.println("The theory that fits the best is the " + betterModel + " model.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Reads url and creates DataPoint objects for each line of scanner.
	private static ArrayList<DataPoint> dataFromURL(final String url) throws MalformedURLException, IOException {
		final URL u = new URL(url);
		final InputStream stream = u.openStream();
		final Scanner scanner = new Scanner(stream);

		final ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();

		while (scanner.hasNext()) {
			final double x = scanner.nextDouble();
			final double y = scanner.nextDouble();
			final double ey = scanner.nextDouble();

			dataPoints.add(new DataPoint(x, y, ey));
		}
		
		scanner.close();
		return dataPoints;
	}

	
	// Finds ChiSquared and sums to find goodnessOfFit using functional programming.
	private static double goodnessOfFit(final Theory theory, final ArrayList<DataPoint> dataPoints) {
		return dataPoints.stream()
				.mapToDouble(dataPoint -> {
					final double yTheory = theory.y(dataPoint.getX());
					final double yMeasured = dataPoint.getY();
					final double yError = dataPoint.getEy();

					return Math.pow(yMeasured - yTheory, 2) / Math.pow(yError, 2);
				})
				.sum();
	}
}
