package exam2_2014;

import java.util.List;

public class Range implements Statistic {

	@Override
	public double calculation(List<Tide> tides) {
		final double minimumTide = tides
				.stream()
				.mapToDouble(tide -> tide.getSeaLevel().getActualSeaLevel())
				.min()
				.getAsDouble();
		final double maximumTide = tides
				.stream().mapToDouble(tide -> tide.getSeaLevel().getActualSeaLevel())
				.max()
				.getAsDouble();
		return maximumTide - minimumTide;
	}

}
