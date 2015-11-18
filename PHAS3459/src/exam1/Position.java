package exam1;

public class Position {
	private double latitude;
	private double longitude;
	private double depth;
	private double horizontalMajorAxis;
	private double horizontalMinorAxis;
	private int azimuth;
	private double depthError;

	public Position(double latitude, double longitude, double depth, double horizontalMajorAxis,
			double horizontalMinorAxis, int azimuth, double depthError) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.depth = depth;
		this.horizontalMajorAxis = horizontalMajorAxis;
		this.horizontalMinorAxis = horizontalMinorAxis;
		this.azimuth = azimuth;
		this.depthError = depthError;
	}
	
	@Override
	// Allows object to be printed into a string. 
	// Doesn't print horizontalMajorAxis, horizontalMinorAxis, or azimuth.
	public String toString() {
		return "Position [latitude=" + latitude + ", longitude=" + longitude + ", depth=" + depth + ", depthError="
				+ depthError + "]";
	}

	// Retrieves depth of earthquake.
	public double getDepth() {
		return depth;
	}

	// Retrieves depth error of earthquake.
	public double getDepthError() {
		return depthError;
	}
}
