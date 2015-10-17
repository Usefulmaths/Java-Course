package module2;

public class TestThreeVector {
	public static void main(String[] args) {
		// Creating three instances of the ThreeVector class
		ThreeVector v1 = new ThreeVector(4, 3, 3);
		ThreeVector v2 = new ThreeVector(3, 5, 2);
		ThreeVector v3 = new ThreeVector(0, 0, 0);

		// Printing the ThreeVectors and their corresponding unit vectors
		System.out.println("Vector \n" + v1 + "\n" + "Unit \n" + v1.unitVector() + "\n");
		System.out.println("Vector \n" + v2 + "\n" + "Unit \n" + v2.unitVector() + "\n");
		System.out.println("Vector \n" + v3 + "\n" + "Unit \n" + v3.unitVector() + "\n");

		// Scalar Product between v1, v2 and v1, v3 using static method.
		System.out.println("Scalar Product of v1, v2 (static): " + ThreeVector.scalarProduct(v1, v2));
		System.out.println("Scalar Product of v1, v2 (static): " + ThreeVector.scalarProduct(v1, v3));

		// Scalar Product between v1, v2 and v1, v3 using non-static method.
		System.out.println("Scalar Product of v1, v2 (non-static): " + v1.scalarProduct(v2));
		System.out.println("Scalar Product of v1, v3 (non-static): " + v1.scalarProduct(v3));

		// Vector Product between v1, v2 and v1, v3 using static method.
		System.out.println("Vector Product of v1, v2 (static): " + ThreeVector.vectorProduct(v1, v2));
		System.out.println("Vector Product of v1, v3 (static): " + ThreeVector.vectorProduct(v1, v3));

		// Vector Product between v1, v2 and v1, v2 using non-static method.
		System.out.println("Vector Product of v1, v2 (non-static): " + v1.vectorProduct(v2));
		System.out.println("Vector Product of v1, v3 (non-static): " + v1.vectorProduct(v3));

		// Angle between v1, v2 and v1, v3 using static method (radians).
		// Radians.
		System.out.println("Angle between v1, v2 (static), in radians: " + ThreeVector.angle(v1, v2));
		System.out.println("Angle between v1, v3 (static), in radians: " + ThreeVector.angle(v1, v3));

		// Angle between v1, v2 and v1, v3 using non-static method (radians).
		System.out.println("Angle between v1, v2 (non-static) in radians: " + v1.angle(v2));
		System.out.println("Angle between v1, v3 (non-static) in radians: " + v1.angle(v3));

		// Printing out without toString() method.
		System.out.println(v1);
		System.out.println("Instead of displaying a nice formatted string, it displays the objects ID.");

	}

}
