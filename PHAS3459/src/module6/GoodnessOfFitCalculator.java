package module6;

import java.util.Collection;

public interface GoodnessOfFitCalculator {
	double goodnessOfFit(final Collection<DataPoint> data, final Theory theory);
}
