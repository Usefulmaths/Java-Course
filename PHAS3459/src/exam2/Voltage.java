package exam2;

public class Voltage {

	/**
	 * Voltage object stores the value of voltage and the time that it occurred
	 * at.
	 */

	// Time voltage occurred.
	private final int time;

	// Value of voltage;
	private final double voltage;

	public Voltage(int time, double voltage) {
		this.time = time;
		this.voltage = voltage;
	}

	// Retrieves time of voltage occurrence.
	public int getTime() {
		return time;
	}

	// Retrieves value of voltage.
	public double getVoltage() {
		return voltage;
	}

	// Allows for Voltage object to be printed into a string.
	@Override
	public String toString() {
		return "Voltage [time=" + time + ", voltage=" + voltage + "]";
	}

}
