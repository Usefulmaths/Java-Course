package module6;

//Extends DataPoint in order to access variables and methods.
public class LabelledDataPoint extends DataPoint {

	private final String label;
	
	public LabelledDataPoint(final double x, final double y, final double ey, final String label) {
		super(x, y, ey);
		this.label = label;
	}

	@Override
	public String toString() {
		return label + ": " + "x = " + x + ", y = " + y + "+-" + ey;
	}
}
