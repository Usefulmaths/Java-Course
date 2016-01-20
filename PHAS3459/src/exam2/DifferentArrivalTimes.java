package exam2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DifferentArrivalTimes class allows the calculations necessary to find the
 * detector that has the maximum difference between two arrival time methods.
 */
public class DifferentArrivalTimes {

	// Allows access to detector map and some useful methods.
	private final DataAnalysis dataAnalysis;

	// First method of calculating arrival time.
	private final ArrivalTime timeOfMaximumVoltage;

	// Second method of calculating arrival time.
	private final ArrivalTime timeOfVoltageThreshold;

	// Map to store detector name and difference.
	private final Map<Double, String> detectorDifference = new HashMap<>();

	public DifferentArrivalTimes(DataAnalysis dataAnalysis, ArrivalTime timeOfMaximumVoltage,
			ArrivalTime timeOfVoltageThreshold) {
		this.dataAnalysis = dataAnalysis;
		this.timeOfMaximumVoltage = timeOfMaximumVoltage;
		this.timeOfVoltageThreshold = timeOfVoltageThreshold;
	}

	// Calculates and displays the detector with the maximum difference in
	// arrival time methods.
	public void printMaximumDifferenceDetector() {
		final List<Double> differenceBetweenArrivalTime = differenceBetweenArrivalTime();
		final double maxDifference = maxDifference(differenceBetweenArrivalTime);
		final String detectorIdentifier = findDetectorByDifference(maxDifference);

		System.out.println();
		System.out.println("The maximum difference in arrival time between the two methods is present in detector "
				+ detectorIdentifier + " with a difference of: " + maxDifference + " ns");
	}

	// Retrieves a detector given the difference in methods.
	private String findDetectorByDifference(final double difference) {
		return detectorDifference.get(difference);
	}

	// Calculates maximum difference of the methods for all detectors.
	private double maxDifference(final List<Double> list) {
		return list
				.stream()
				.mapToDouble(element -> element.doubleValue())
				.max()
				.getAsDouble();
	}

	// Creates a List of all time differences of methods for all detectors.
	private List<Double> differenceBetweenArrivalTime() {
		final List<Double> listOfDifferenceInArrivalTimes = new ArrayList<>();

		dataAnalysis.getDetectors()
		.forEach((identifier, distance) -> {
				listOfDifferenceInArrivalTimes.add(absoluteDifferenceBetweenArrivalTimes(identifier));
			});

		return listOfDifferenceInArrivalTimes;
	}

	// Calculates the absolute difference between two arrival time methods.
	private double absoluteDifferenceBetweenArrivalTimes(final String identifier) {
		final double absoluteDifference = Math
				.abs(speedOfPulse(identifier, timeOfMaximumVoltage) - speedOfPulse(identifier, timeOfVoltageThreshold));
		detectorDifference.put(absoluteDifference, identifier);

		return absoluteDifference;
	}

	// Calculates the speed of pulse for a detector given a method to calculate.
	private double speedOfPulse(final String identifier, final ArrivalTime method) {
		return dataAnalysis.speedOfPulse(identifier, method);
	}

}
