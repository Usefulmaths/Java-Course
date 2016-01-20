package exam2;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements ArrivalTime and uses a method to calculate arrival time of a
 * pulse. Calculates arrival time by finding the time of the first occurrence
 * when the voltage exceeds a given threshold.
 * 
 * @param thresholdVoltage
 */
public class TimeOfVoltageThreshold implements ArrivalTime {

	// Threshold Voltage value.
	double thresholdVoltage;

	public TimeOfVoltageThreshold(double thresholdVoltage) {
		this.thresholdVoltage = thresholdVoltage;
	}

	// Calculates arrival time by finding the time of the first occurrence hwen
	// the voltage exceeds a given threshold.
	@Override
	public double arrivalTimeCalculation(Pulse pulse) {
		final List<Voltage> voltages = pulse.getVoltages();

		final List<Voltage> voltagesAboveThreshold = voltages
				.stream()
				.filter(voltage -> voltage.getVoltage() >= thresholdVoltage)
				.collect(Collectors.toList());

		final Voltage firstOccurrence = voltagesAboveThreshold.get(0);
		return firstOccurrence.getTime();
	}

}
