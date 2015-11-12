package module5;

public class TestSquareMatrix {
	private static SquareMatrix matrixA;
	private static SquareMatrix matrixB;
	private static SquareMatrix matrixC;
	private static SquareMatrix matrixD;

	public static void main(String[] args) {
		// Setting up the matrix elements as 2D double arrays.
		final double[][] A = { { 1, 0, -2 }, { 0, 3, 0 }, { 2, 0, 1 } };
		final double[][] B = { { 0, 0, 1 }, { 0, 1, 0 }, { -1, 0, 1 } };
		final double[][] C = { { 4, 3 }, { 3, 2 } };
		final double[][] D = { { -2, 3 }, { 3, -4 } };

		try {
			// Instantiating SquareMatrix objects.
			matrixA = new SquareMatrix(A);
			matrixB = new SquareMatrix(B);
			matrixC = new SquareMatrix(C);
			matrixD = new SquareMatrix(D);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Call void methods to run each section of code.
		sums();
		productsABAndCommutator();
		productsCDAndCheck();
	}

	// Sum section.
	private static void sums() {
		try {
			final SquareMatrix matrixAPlusB = matrixA.add(matrixB);
			final SquareMatrix matrixAMinusB = matrixA.subtract(matrixB);
			System.out.println("A + B = " + matrixAPlusB + "\n");
			System.out.println("A - B = " + matrixAMinusB + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Products AB, BA, and commutator section.
	private static void productsABAndCommutator() {
		try {
			final SquareMatrix matrixAMultiplyB = matrixA.multiply(matrixB);
			final SquareMatrix matrixBMultiplyA = matrixB.multiply(matrixA);

			System.out.println("A * B = " + matrixAMultiplyB + "\n");
			System.out.println("B * A = " + matrixBMultiplyA + "\n");

			final SquareMatrix commutator = matrixAMultiplyB.subtract(matrixBMultiplyA);

			System.out.println("[A, B] = AB - BA = " + commutator + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Product CD and checking whether it is equal to the identity matrix.
	private static void productsCDAndCheck() {
		try {
			final SquareMatrix matrixCMultiplyD = matrixC.multiply(matrixD);

			if (matrixCMultiplyD.equals(SquareMatrix.unitMatrix(matrixCMultiplyD.dimension()))) {
				System.out.println("C * D is equal to the identity matrix: " + matrixCMultiplyD);
			} else {
				System.out.println("C * D is not equal to the identity matrix: " + matrixCMultiplyD);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
