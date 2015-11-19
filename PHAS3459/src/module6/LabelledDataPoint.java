package module6;

public class LabelledDataPoint extends DataPoint {

	private final String label;
	
	public LabelledDataPoint(final double x, final double y, final double ey, final String label) {
		super(x, y, ey);
		this.label = label;
	}

	@Override
	public String toString() {
		return label + ": " + "x = " + this.getX() + ", y = " + this.getY() + "+-" + this.getEy();
	}
	
	
	
	
}
