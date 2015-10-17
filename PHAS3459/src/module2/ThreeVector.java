package module2;

public class ThreeVector {
	double x;
	double y;
	double z;

	// Constructor to set/store x, y, z through calling a new instance of a
	// ThreeVector Object.
	public ThreeVector(double x, double y, double z) {
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
	double magnitude() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}

	// Returns a ThreeVector with a direction and magnitude of 1.
	ThreeVector unitVector() {
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
	static double scalarProduct(ThreeVector threeVec1, ThreeVector threeVec2) {
		return threeVec1.x * threeVec2.x + threeVec1.y * threeVec2.y + threeVec1.z * threeVec2.z;
	}

	// Vector Product (Static). Determined by determinant of matrix with two
	// vectors.
	static ThreeVector vectorProduct(ThreeVector threeVec1, ThreeVector threeVec2) {
		return new ThreeVector(threeVec1.y * threeVec2.z - threeVec1.z * threeVec2.y,
				threeVec1.z * threeVec2.x - threeVec1.x * threeVec2.z,
				threeVec1.x * threeVec2.y - threeVec1.y * threeVec2.x);
	}

	// Vector Addition (Static)
	static ThreeVector add(ThreeVector threeVec1, ThreeVector threeVec2) {
		return new ThreeVector(threeVec1.x + threeVec2.x, threeVec1.y + threeVec2.y, threeVec1.z + threeVec2.z);
	}

	// Angle between two vectors (Static). Measured in radians (Java default)
	static double angle(ThreeVector threeVec1, ThreeVector threeVec2) {
		// Using definition of scalar product ScalarProduct =
		// Magnitude(v1)*Magnitude(v2) Cos(angle)
		return Math.acos(
				ThreeVector.scalarProduct(threeVec1, threeVec2) / (threeVec1.magnitude() * threeVec2.magnitude()));
	}

	// Scalar Product (Non-static)
	double scalarProduct(ThreeVector threeVec) {
		return ThreeVector.scalarProduct(this, threeVec);
	}

	// Vector Product (Non-static)
	ThreeVector vectorProduct(ThreeVector threeVec) {
		return ThreeVector.vectorProduct(this, threeVec);

	}

	// Vector Addition (Non-static)
	ThreeVector add(ThreeVector threeVec) {
		return ThreeVector.add(this, threeVec);
	}

	// Angle between current ThreeVector Object and another ThreeVector
	// (Non-static) in radians (Java Default)
	double angle(ThreeVector threeVec) {
		return ThreeVector.angle(this, threeVec);

	}

}
