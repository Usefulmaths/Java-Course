package module3;
 
public class ThreeVector {
	private final double x;
	private final double y;
	private final double z;

	// Constructor to set/store x, y, z through calling a new instance of a
	// ThreeVector Object
	public ThreeVector(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	// Returns X component
	public double getX() {
		return x;
	}

	// Returns Y component
	public double getY() {
		return y;
	}

	// Returns Z component
	public double getZ() {
		return z;
	}

	// Magnitude of vector. Square root of the sum of the components squared.
	public double magnitude() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}

	// Returns a ThreeVector with a direction and magnitude of 1.
	public ThreeVector unitVector() throws Exception {

		if (this.x == 0 && this.y == 0 && this.z == 0) {
			throw new Exception("There exists no unit vector for: " + this);
		}
		// Normalising components
		double normx = this.x / this.magnitude();
		double normy = this.y / this.magnitude();
		double normz = this.z / this.magnitude();

		// Creating new ThreeVector with these normalised components
		return new ThreeVector(normx, normy, normz);

	}

	// toString to allows us to print the object to the console
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	// Scalar Product (Static)
	public static double scalarProduct(final ThreeVector threeVec1, final ThreeVector threeVec2) {
		return threeVec1.x * threeVec2.x + threeVec1.y * threeVec2.y + threeVec1.z * threeVec2.z;
	}

	// Vector Product (Static). Determined by determinant of matrix with two
	// vectors.
	public static ThreeVector vectorProduct(final ThreeVector threeVec1, final ThreeVector threeVec2) {
		return new ThreeVector(threeVec1.y * threeVec2.z - threeVec1.z * threeVec2.y,
				threeVec1.z * threeVec2.x - threeVec1.x * threeVec2.z,
				threeVec1.x * threeVec2.y - threeVec1.y * threeVec2.x);
	}

	// Vector Addition (Static)
	public static ThreeVector add(final ThreeVector threeVec1, final ThreeVector threeVec2) {
		return new ThreeVector(threeVec1.x + threeVec2.x, threeVec1.y + threeVec2.y, threeVec1.z + threeVec2.z);
	}

	// Angle between two vectors (Static). Measured in radians (Java default)
	public static double angle(final ThreeVector threeVec1, final ThreeVector threeVec2) {
		return Math.acos(
				ThreeVector.scalarProduct(threeVec1, threeVec2) / (threeVec1.magnitude() * threeVec2.magnitude()));
	}

	// Scalar Product (Non-static)
	public double scalarProduct(final ThreeVector threeVec) {
		return ThreeVector.scalarProduct(this, threeVec);
	}

	// Vector Product (Non-static)
	public ThreeVector vectorProduct(final ThreeVector threeVec) {
		return ThreeVector.vectorProduct(this, threeVec);

	}

	// Vector Addition (Non-static)
	public ThreeVector add(final ThreeVector threeVec) {
		return ThreeVector.add(this, threeVec);
	}

	// Angle between current ThreeVector Object and another ThreeVector
	// (Non-static) in radians (Java Default)
	public double angle(final ThreeVector threeVec) throws Exception {

		if ((this.x == 0 && this.y == 0 && this.z == 0) || (threeVec.x == 0 && threeVec.y == 0 && threeVec.z == 0)) {
			throw new Exception("One of the vectors have no size. Therefore, no angle exists.");
		}

		return ThreeVector.angle(this, threeVec);
	}

}
