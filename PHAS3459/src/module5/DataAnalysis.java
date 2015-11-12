package module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataAnalysis {

	public static void main(String[] args) {
		// New instance of an ArrayList to contain DataPoints.
		ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
		try {
			// Reads in data from URL and stores within the ArrayList.
			dataPoints = dataFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-xy.txt");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Creates a quadratic and quartic function to test theory.
		final Theory theoryXSquared = new Theory(2);
		final Theory theoryXFourth = new Theory(4);

		// Finds chiSquared between both theory models and dataPoints.
		final double modelXSquare = goodnessOfFit(theoryXSquared, dataPoints);
		final double modelXFourth = goodnessOfFit(theoryXFourth, dataPoints);

		System.out.println("Goodness of fit for quadratic model: " + modelXSquare);
		System.out.println("Goodness of fit for quartic model: " + modelXFourth);

		// Depending on chiSquared values, chooses the best model.
		String betterModel = (modelXSquare < modelXFourth) ? "quadratic" : "quartic";

		System.out.println("The theory that fits the best is the " + betterModel + " model.");
	}

	private static ArrayList<DataPoint> dataFromURL(final String urlName) throws MalformedURLException, IOException {
		final URL url = new URL(urlName);
		final InputStream stream = url.openStream();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		final ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();

		String line;
		while ((line = reader.readLine()) != null) {
			final String[] valuesString = line.split("  ");
			final double[] valuesDouble = new double[3];

			for (int i = 0; i < valuesString.length; i++) {
				valuesDouble[i] = Double.parseDouble(valuesString[i]);

			}

			dataPoints.add(new DataPoint(valuesDouble[0], valuesDouble[1], valuesDouble[2]));
		}
		return dataPoints;
	}

	private static double goodnessOfFit(final Theory theory, final ArrayList<DataPoint> dataPoints) {
		double chiSquared = 0;

		for (DataPoint dp : dataPoints) {
			final double yTheory = theory.y(dp.getX());
			final double yMeasured = dp.getY();
			final double yError = dp.getEy();

			final double chiSquaredDataPoint = Math.pow(yMeasured - yTheory, 2) / Math.pow(yError, 2);
			chiSquared += chiSquaredDataPoint;
		}
		return chiSquared;
	}
}
