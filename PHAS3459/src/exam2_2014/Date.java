package exam2_2014;

public class Date {

	private final int year;
	private final int month;
	private final int day;
	private final Time time;

	public Date(int year, int month, int day, Time time) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.time = time;
	}

	@Override
	public String toString() {
		return "Date [year=" + year + ", month=" + month + ", day=" + day + ", time=" + time + "]";
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public Time getTime() {
		return time;
	}

}
