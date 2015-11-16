package module5;

public class TestSquareMatrix {
	private static SquareMatrix A;
	private static SquareMatrix B;
	private static SquareMatrix C;
	private static SquareMatrix D;

	public static final int UNIT_MATRIX_2X2_SIZE = 2;

	public static void main(String[] args) {
		// Setting up the matrix elements as 2D double arrays.
		final double[][] doubleA = {
				{ 1, 0, -2 }, 
				{ 0, 3,  0 },
				{ 2, 0,  1 }
		};
		final double[][] doubleB = { 
				{  0, 0, 1 }, 
				{  0, 1, 0 }, 
				{ -1, 0, 1 }
		};
		final double[][] doubleC = { 
				{ 4, 3 }, 
				{ 3, 2 } 
		};
		final double[][] doubleD = { 
				{ -2,  3 },
				{  3, -4 }
		};
		
		try {
			// Instantiating SquareMatrix objects.
			A = new SquareMatrix(doubleA);
			B = new SquareMatrix(doubleB);
			C = new SquareMatrix(doubleC);
			D = new SquareMatrix(doubleD);

			// Call void methods to run each section of code.
			sums();
			productsABAndCommutator();
			productsCDAndCheck();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	// Sum section.
	private static void sums() {
		try {
			final SquareMatrix matrixAPlusB = A.add(B);
			final SquareMatrix matrixAMinusB = A.subtract(B);
			System.out.println("A + B = \n" + matrixAPlusB + "\n");
			System.out.println("A - B = \n" + matrixAMinusB + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// Products AB, BA, and commutator section.
	private static void productsABAndCommutator() {
		try {
			final SquareMatrix aMultiplyB = A.multiply(B);
			final SquareMatrix bMultiplyA = B.multiply(A);

			System.out.println("A * B = \n" + aMultiplyB + "\n");
			System.out.println("B * A = \n" + bMultiplyA + "\n");

			final SquareMatrix commutator = aMultiplyB.subtract(bMultiplyA);

			System.out.println("[A, B] = AB - BA = \n" + commutator + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Product CD and checking whether it is equal to the identity matrix.
	private static void productsCDAndCheck() {
		try {
			final SquareMatrix cMultiplyD = C.multiply(D);

			// 2x2 unit matrix
			if (cMultiplyD.equals(SquareMatrix.unitMatrix(UNIT_MATRIX_2X2_SIZE))) {
				System.out.println("C * D is equal to the identity matrix: \n" + cMultiplyD);
			} else {
				System.out.println("C * D is not equal to the identity matrix: \n" + cMultiplyD);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}
