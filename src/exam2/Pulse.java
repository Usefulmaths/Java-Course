package exam2;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Stores the identifier and list of voltages given by a pulse.
 */
public class Pulse {

	// Pulse detector identifier.
	private final String identifier;

	// List of voltages in pulse recorded by detector.
	private final List<Voltage> voltages;

	public Pulse(String identifier, List<Voltage> voltages) {
		this.identifier = identifier;
		this.voltages = voltages;
	}

	// Allows for the object field variables to be printed into a string.
	@Override
	public String toString() {
		return "Signal [identifier=" + identifier + ", voltages=" + voltages + "]";
	}

	// Retrieves detector identifier
	public String getIdentifier() {
		return identifier;
	}

	// Retrieves list of voltages in pulse;
	public List<Voltage> getVoltages() {
		return voltages;
	}

	// Calculates arrival time as the time of the maximum recorded voltage in
	// pulse..
	public double arrivalTime() {
		final List<Voltage> listOfMaxVoltages = voltages
				.stream()
				.filter(voltage -> voltage.getVoltage() == maxVoltage())
				.collect(Collectors.toList());

		return listOfMaxVoltages.get(0).getTime();
	}

	// Retrieves max voltage in the list of voltages.
	public double maxVoltage() {
		return voltages
				.stream()
				.mapToDouble(voltage -> voltage.getVoltage())
				.max()
				.getAsDouble();
	}

	// Retrieves how many voltages are in the pulse.
	public int countOfVoltage() {
		return (int) voltages
				.stream()
				.count();
	}

	// Retrieves the sum of all voltages in the pulse.
	public double sumOfVoltage() {
		return voltages
				.stream()
				.mapToDouble(voltage -> voltage.getVoltage())
				.sum();
	}
}
