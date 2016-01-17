package exam2_2014;

public class Time {

	private final int hour;
	private final int minutes;

	public Time(int hour, int minutes) {
		this.hour = hour;
		this.minutes = minutes;
	}

	@Override
	public String toString() {
		return "Time [hour=" + hour + ", minutes=" + minutes + "]";
	}

	public int getHour() {
		return hour;
	}

	public int getMinutes() {
		return minutes;
	}

}
