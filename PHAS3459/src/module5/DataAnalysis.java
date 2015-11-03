package module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class DataAnalysis {

	public static void main(String[] args) {
		try {
			ArrayList<DataPoint> dataPoints = dataFromURL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/data/module5/module5-xy.txt");

			Theory theoryXSquared = new Theory(2);
			Theory theoryXFourth = new Theory(4);

			double modelXSquare = goodnessOfFit(theoryXSquared, dataPoints);
			double modelXFourth = goodnessOfFit(theoryXFourth, dataPoints);

			if (modelXSquare > modelXFourth) {
				System.out.println(
						"x^4 is a better fit than x^2 \n \t x^2: " + modelXSquare + "\n \t x^4: " + modelXFourth);
			} else {
				System.out.println(
						"x^2 is a better fit than x^4 \n\n \t x^2: " + modelXSquare + "\n \t x^4: " + modelXFourth);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static ArrayList<DataPoint> dataFromURL(final String urlName) throws MalformedURLException, IOException {
		final URL url = new URL(urlName);
		final InputStream stream = url.openStream();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();

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
