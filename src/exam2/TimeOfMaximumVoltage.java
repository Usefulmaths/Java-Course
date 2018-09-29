package exam2;

/**
 * Implements ArrivalTime and uses a method to calculate arrival time of a
 * pulse. Calculates arrival time by finding the time of the maximum recorded
 * voltage.
 */
public class TimeOfMaximumVoltage implements ArrivalTime {

	// Returns arrival time by first occurrence of maximum voltage.
	@Override
	public double arrivalTimeCalculation(Pulse pulse) {
		return pulse.arrivalTime();
	}

}
