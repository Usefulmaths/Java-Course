package module6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

// Extends TestDataPoints to access dataFromURL method.
public class DataAnalysis extends TestDataPoints {

	public static void main(String[] args) {
		try {
			// Reads data in from TestDataPoints method
			final ArrayList<DataPoint> dataPoints = TestDataPoints
					.dataFromURL("http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt");
			final ArrayList<Theory> theories = new ArrayList<Theory>();
			final GoodnessOfFitCalculator gofCalculator = new ChiSquared();

			// Theories
			final Theory theory1 = new PowerLawTheory(2);
			final Theory theory2 = new PowerLawTheory(2.05);
			final Theory theory3 = new QuadraticTheory(1, 10, 0);

			theories.add(theory1);
			theories.add(theory2);
			theories.add(theory3);

			final Theory bestTheory = bestTheory(dataPoints, theories, gofCalculator);

			System.out.println("The best theory is: " + bestTheory);

		} catch (IOException e) {
			System.out.println("Error parsing in URL: " + e.getMessage());
		}
	}

	// Chooses best theory from a collection of theories.
	private static Theory bestTheory(Collection<DataPoint> data, Collection<Theory> theories,
			GoodnessOfFitCalculator gofCalculator) {
		boolean first = true;
		double bestGoodnessOfFit = 0.0;
		Theory bestTheory = null;
		for (Theory theory : theories) {
			double gof = gofCalculator.goodnessOfFit(data, theory);
			if (first) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
				first = false;
			} else if (gof < bestGoodnessOfFit) {
				bestTheory = theory;
				bestGoodnessOfFit = gof;
			}
		}
		return bestTheory;
	}
}
