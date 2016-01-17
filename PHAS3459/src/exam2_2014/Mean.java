package exam2_2014;

import java.util.List;

public class Mean implements Statistic {

	@Override
	public double calculation(List<Tide> tides) {
		return tides
				.stream()
				.mapToDouble(tide->tide.getSeaLevel().getActualSeaLevel())
				.average()
				.getAsDouble();
	}
}
