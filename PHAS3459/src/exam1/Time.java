package exam1;

public class Time {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private double second;

	public Time(int year, int month, int day, int hour, int minute, double second) {

		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	// Allows object to be printed into a string.
	@Override
	public String toString() {
		return "Time [year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute=" + minute
				+ ", second=" + second + "]";
	}

	// Retrieves month when earthquake happened.
	public int getMonth() {
		return month;
	}

}
