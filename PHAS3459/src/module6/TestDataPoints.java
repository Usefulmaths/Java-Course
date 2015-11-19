package module6;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TestDataPoints {
	public static void main(String[] args) {
		try {
			ArrayList<DataPoint> dataPoints = dataFromURL(
					"http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt");

			for (DataPoint dataPoint : dataPoints) {
				System.out.println(dataPoint);
			}

		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}

	public static ArrayList<DataPoint> dataFromURL(final String url) throws IOException {
		ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();

		final URL u = new URL(url);
		final InputStream stream = u.openStream();
		final Scanner scanner = new Scanner(stream);

		while (scanner.hasNextLine()) {
			final String line = scanner.nextLine();
			final String[] array = line.split("  ");

			final double x = Double.parseDouble(array[0]);
			final double y = Double.parseDouble(array[1]);
			final double ey = Double.parseDouble(array[2]);

			if (array.length == 3) {
				dataPoints.add(new DataPoint(x, y, ey));
			} else {
				final String label = array[3];
				dataPoints.add(new LabelledDataPoint(x, y, ey, label));
			}
		}
		return dataPoints;
	}
}
