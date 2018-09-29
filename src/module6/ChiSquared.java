package module6;

import java.util.Collection;

public class ChiSquared implements GoodnessOfFitCalculator {

	// Calculates ChiSquared.
	public double goodnessOfFit(final Collection<DataPoint> data, final Theory theory) {
		// Maps each dataPoint to its variation from the model and sums them up
		return data.stream()
			.mapToDouble(dataPoint -> {
				final double yTheory = theory.y(dataPoint.getX());
				return Math.pow((dataPoint.getY() - yTheory), 2) / (Math.pow(dataPoint.getEy(), 2));
			})
			.sum();
	}
}
