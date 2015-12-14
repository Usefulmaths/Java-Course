package exam1;

public class Earthquake {

	private Time date;
	private Position position;
	private double magnitude;
	private int ID;

	public Earthquake(Time date, Position position, double magnitude, int ID) {
		this.date = date;
		this.position = position;
		this.magnitude = magnitude;
		this.ID = ID;
	}

	// Allows object to be printed into a string.
	@Override
	public String toString() {
		return "Earthquake [date=" + date + ", position=" + position + ", magnitude=" + magnitude + ", ID=" + ID + "]";
	}

	// Retrieves magnitude of earthquake.
	public double getMagnitude() {
		return magnitude;
	}

	// Retrieves Time object that contains all information about when the earthquake happened.
	public Time getDate() {
		return date;
	}

	// Retrieves Position object that contains all information about where the earthquake happened.
	public Position getPosition() {
		return position;
	}
}
