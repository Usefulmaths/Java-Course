package module6;

import java.util.Collection;

public class ChiSquared implements GoodnessOfFitCalculator {

	public double goodnessOfFit(final Collection<DataPoint> data, final Theory theory) {
		return data.stream()
				.mapToDouble(dataPoint -> {
					final double yTheory = theory.y(dataPoint.getX());
					return Math.pow((dataPoint.getY() - yTheory), 2) / (Math.pow(dataPoint.getEy(), 2));
				})
				.sum();
	}
}
