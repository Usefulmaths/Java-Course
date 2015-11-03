package module5;

public class TestSquareMatrix {

	private static SquareMatrix matrixA;
	private static SquareMatrix matrixB;
	private static SquareMatrix matrixC;
	private static SquareMatrix matrixD;

	public static void main(String[] args) {

		double[][] A = { { 1, 0, -2 }, { 0, 3, 0 }, { 2, 0, 1 } };
		double[][] B = { { 0, 0, 1 }, { 0, 1, 0 }, { -1, 0, 1 } };
		double[][] C = { { 4, 3 }, { 3, 2 } };
		double[][] D = { { -2, 3 }, { 3, -4 } };

		try {
			matrixA = new SquareMatrix(A);
			matrixB = new SquareMatrix(B);
			matrixC = new SquareMatrix(C);
			matrixD = new SquareMatrix(D);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		sums();
		productsABAndCommutator();
		productsCDAndCheck();
	}

	private static void sums() {
		try {
			SquareMatrix matrixAPlusB = matrixA.add(matrixB);
			SquareMatrix matrixAMinusB = matrixA.subtract(matrixB);

			System.out.println("A + B = " + matrixAPlusB + "\n");
			System.out.println("A - B = " + matrixAMinusB + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void productsABAndCommutator() {
		try {
			SquareMatrix matrixAMultiplyB = matrixA.multiply(matrixB);
			SquareMatrix matrixBMultiplyA = matrixB.multiply(matrixA);

			System.out.println("A * B = " + matrixAMultiplyB + "\n");
			System.out.println("B * A = " + matrixBMultiplyA + "\n");

			SquareMatrix commutator = matrixAMultiplyB.subtract(matrixBMultiplyA);

			System.out.println("[A, B] = AB - BA = " + commutator + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void productsCDAndCheck() {
		try {
			SquareMatrix matrixCMultiplyD = matrixC.multiply(matrixD);

			if (matrixCMultiplyD.equals(SquareMatrix.unitMatrix(matrixCMultiplyD.elements.length))) {
				System.out.println("C * D is equal to the identity matrix: " + matrixCMultiplyD);
			} else {
				System.out.println("C * D is not equal to the identity matrix: " + matrixCMultiplyD);
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
