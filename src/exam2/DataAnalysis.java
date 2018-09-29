package exam2;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * DataAnalysis class deals with any calculations that may need to be done with
 * a list of pulses such as: calculating the mean amplitudes of the pulses.
 */

public class DataAnalysis {

	// A list of all pulses in the data file.
	private final List<Pulse> signals;

	// A map of all detectors.
	private final Map<String, Double> detectors;

	public DataAnalysis(List<Pulse> signals, Map<String, Double> detectors) {
		this.signals = signals;
		this.detectors = detectors;
	}

	// Calculates a summary of each detector and prints them out.
	public void detectorSummary() {
		detectors.forEach((identifier, distance) ->
			{
				final int numberOfSignals = numberOfPulses(identifier);
				final double meanAmplitudeOfPulse = meanAmplitudeOfPulses(identifier);
				final double meanArrivalTime = meanArrivalTime(identifier);
				final double speedOfPulse = speedOfPulse(identifier);

				System.out.println();
				System.out.println(identifier + ": ");
				System.out.println("\tNumber of signals: " + numberOfSignals);
				System.out.println("\tMean amplitude of pulse: " + meanAmplitudeOfPulse + " V");
				System.out.println("\tMean arrival time: " + meanArrivalTime + " ns");
				System.out.println("\tSpeed of pulse: " + speedOfPulse + " m/ns");
				System.out.println();
			});
	}

	// Prints out speed of particles given an ArrivalTime object for each
	// detectors.
	public void printSpeedOfParticlesUsingArrivalTime(final ArrivalTime arrivalTime) {
		detectors.forEach((identifier, distance) -> {
				final double speedOfPulse = speedOfPulse(identifier, arrivalTime);
				System.out.println(identifier + ": " + "Speed of pulse: " + speedOfPulse + " m/ns");
			});
	}

	// Calculates the speed of pulse for a single detector given an ArrivalTime
	// object.
	public double speedOfPulse(final String identifier, final ArrivalTime arrivalTime) {
		final List<Pulse> filteredPulse = filterByIdentifier(identifier);

		final double distance = detectors.get(identifier);
		final double time = filteredPulse
				.stream()
				.mapToDouble(thePulse -> arrivalTime.arrivalTimeCalculation(thePulse))
				.average()
				.getAsDouble();

		return speed(distance, time);
	}

	// Calculates the speed of pulse for a single detector using mean arrival
	// time
	// obtained by maximum voltages.
	public double speedOfPulse(final String identifier) {
		final double distance = detectors.get(identifier);
		final double time = meanArrivalTime(identifier);

		return speed(distance, time);
	}

	// Calculates speed using distance and time.
	public double speed(final double distance, final double time) {
		return distance / time;
	}

	// Calculate mean arrival time for a single detector.
	public double meanArrivalTime(final String identifier) {
		final List<Pulse> filteredList = filterByIdentifier(identifier);
		final double totalArrivalTime = filteredList
				.stream()
				.mapToDouble(pulse -> pulse.arrivalTime())
				.sum();
		
		final int countOfList = (int) filteredList
				.stream()
				.count();

		return totalArrivalTime / countOfList;
	}

	// Calculates the mean amplitude of all pulses in data.
	public double meanAmplitudeOfAllPulses() {

		final int totalCountVoltages = signals
				.stream()
				.mapToInt(voltage -> voltage.countOfVoltage())
				.sum();

		final double totalVoltage = signals
				.stream()
				.mapToDouble(pulse -> pulse.sumOfVoltage())
				.sum();

		return totalVoltage / totalCountVoltages;
	}

	// Calculates the mean amplitude of pulses for a single detector.
	public double meanAmplitudeOfPulses(final String identifier) {
		final List<Pulse> pulseFilter = filterByIdentifier(identifier);

		final int totalCountVoltage = pulseFilter
				.stream()
				.mapToInt(voltage -> voltage.countOfVoltage())
				.sum();

		final double totalVoltage = signals
				.stream()
				.mapToDouble(pulse -> pulse.sumOfVoltage())
				.sum();

		return totalVoltage / totalCountVoltage;
	}

	// Returns a list of pulses filtered by a single detector.
	public List<Pulse> filterByIdentifier(final String identifier) {
		return signals
				.stream()
				.filter(pulse -> pulse.getIdentifier().equals(identifier))
				.collect(Collectors.toList());
	}

	// Retrieves amount of pulses in data.
	public int totalNumberOfPulses() {
		return detectors
				.keySet()
				.stream()
				.mapToInt(identifier -> numberOfPulses(identifier))
				.sum();
	}

	// Retrieves amount of pulses in data by a single detector.
	public int numberOfPulses(final String identifier) {
		return (int) signals
				.stream()
				.filter(signal -> signal.getIdentifier().equals(identifier))
				.count();
	}

	// Allows for object to be printed by a string.
	@Override
	public String toString() {
		return "DataAnalysis [signals=" + signals + ", detectors=" + detectors + "]";
	}

	// Retrieves list of pulses.
	public List<Pulse> getSignals() {
		return signals;
	}

	// Retrieves map of detectors and distances.
	public Map<String, Double> getDetectors() {
		return detectors;
	}

}
