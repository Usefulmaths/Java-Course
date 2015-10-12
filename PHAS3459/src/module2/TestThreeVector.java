package module2;

public class TestThreeVector {
	public static void main(String[] args) {
		ThreeVector v1 = new ThreeVector(4, 3, 3);
		ThreeVector v2 = new ThreeVector(3, 5, 2);
		ThreeVector v3 = new ThreeVector(0, 0, 0);

		System.out.println(v1 + "\n" + v1.unitVector() + "\n");
		System.out.println(v2 + "\n" + v2.unitVector() + "\n");
		System.out.println(v3 + "\n" + v3.unitVector());

		// Scalar Product
		System.out.println(ThreeVector.scalarProduct(v1, v2));
		System.out.println(ThreeVector.scalarProduct(v1, v3));

		System.out.println(v1.scalarProduct(v2));
		System.out.println(v1.scalarProduct(v3));

		// Vector Product
		System.out.println(ThreeVector.vectorProduct(v1, v2));
		System.out.println(ThreeVector.vectorProduct(v1, v3));

		System.out.println(v1.vectorProduct(v2));
		System.out.println(v1.vectorProduct(v3));

		// Angle
		System.out.println(ThreeVector.angle(v1, v2));
		System.out.println(ThreeVector.angle(v1, v3));

		System.out.println(v1.angle(v2));
		System.out.println(v1.angle(v3));

		// Printing out without toString() method.
		System.out.println(v1);
		System.out.println("Something to do with the memory, ***LOOK THIS UP LATER***");

	}

}
