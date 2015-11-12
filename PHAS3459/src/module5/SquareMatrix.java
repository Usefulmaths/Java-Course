package module5;

import java.util.Arrays;

public class SquareMatrix {
	private final double[][] elements;

	public SquareMatrix(final double[][] elements) throws Exception {
		this.elements = elements;

		if (isSquareMatrix(this)) {
			throw new Exception("Invalid matrix, must be a square matrix.");
		}
	}

	// Method to test whether matrix is square.
	private static boolean isSquareMatrix(final SquareMatrix sm) {
		return Math.pow(numberRows(sm.elements), 2) != numberElements(sm.elements);
	}

	// Method to test whether two matrices are of the same dimensions.
	private static boolean areMatricesSameSize(final SquareMatrix sm1, final SquareMatrix sm2) {
		return SquareMatrix.numberRows(sm1.elements) != SquareMatrix.numberRows(sm2.elements)
				&& SquareMatrix.numberElements(sm1.elements) != SquareMatrix.numberElements(sm2.elements);
	}

	public int dimension() {
		return this.elements.length;
	}

	// Number of rows in matrix.
	private static int numberRows(final double[][] elements) {
		int nRows = 0;
		for (double[] rows : elements) {
			nRows++;
		}
		return nRows;
	}

	// Number of elements within the matrix.
	private static int numberElements(final double[][] elements) {
		int nEle = 0;
		for (double[] rows : elements) {
			for (double ele : rows) {
				nEle++;
			}
		}
		return nEle;
	}

	// Sets up a unit matrix corresponding to a given size.
	public static SquareMatrix unitMatrix(final int size) throws Exception {
		final double[][] matrixDouble = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					matrixDouble[i][j] = 1;
				}
			}
		}

		return new SquareMatrix(matrixDouble);
	}

	// Static methods for adding, subtracting, and multiplying matrices of the
	// same size.
	public static SquareMatrix add(final SquareMatrix sm1, final SquareMatrix sm2) throws Exception {
		if (areMatricesSameSize(sm1, sm2)) {
			throw new Exception("Arrays are not the same size.");
		}

		final double[][] sm1Double = sm1.elements;
		final double[][] sm2Double = sm2.elements;
		final int size = sm1Double.length;

		final double[][] sm1AddSm2 = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sm1AddSm2[i][j] = sm1Double[i][j] + sm2Double[i][j];
			}
		}

		return new SquareMatrix(sm1AddSm2);
	}

	public static SquareMatrix subtract(final SquareMatrix sm1, final SquareMatrix sm2) throws Exception {
		if (areMatricesSameSize(sm1, sm2)) {
			throw new Exception("Arrays are not the same size.");
		}

		final double[][] sm1Double = sm1.elements;
		final double[][] sm2Double = sm2.elements;
		final int size = sm1Double.length;

		final double[][] sm1SubtractSm2 = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sm1SubtractSm2[i][j] = sm1Double[i][j] - sm2Double[i][j];
			}
		}

		return new SquareMatrix(sm1SubtractSm2);
	}

	public static SquareMatrix multiply(final SquareMatrix sm1, final SquareMatrix sm2) throws Exception {
		if (areMatricesSameSize(sm1, sm2)) {
			throw new Exception("Arrays are not the same size.");
		}
		final double[][] sm1Double = sm1.elements;
		final double[][] sm2Double = sm2.elements;
		final int size = sm1Double.length;

		final double[][] sm1MultiplySm2 = new double[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				double sumOverK = 0;
				for (int k = 0; k < size; k++) {
					sumOverK += sm1Double[i][k] * sm2Double[k][j];
				}
				sm1MultiplySm2[i][j] = sumOverK;
			}
		}
		return new SquareMatrix(sm1MultiplySm2);
	}

	// Non-static methods for add, subtract, and multiply.
	public SquareMatrix add(final SquareMatrix sm) throws Exception {
		return SquareMatrix.add(this, sm);
	}

	public SquareMatrix subtract(final SquareMatrix sm) throws Exception {
		return SquareMatrix.subtract(this, sm);
	}

	public SquareMatrix multiply(final SquareMatrix sm) throws Exception {
		return SquareMatrix.multiply(this, sm);
	}

	// toString method to print matrix to the console. Takes care of 2x2 and 3x3
	// matrices only.
	public String toString() {
		if (elements.length == 3 && elements[0].length == 3) {
			return "\n" + elements[0][0] + "\t" + elements[0][1] + "\t" + elements[0][2] + "\n" + elements[1][0] + "\t"
					+ elements[1][1] + "\t" + elements[1][2] + "\n" + elements[2][0] + "\t" + elements[2][1] + "\t"
					+ elements[2][2];
		} else if (elements.length == 2 && elements[0].length == 2) {
			return "\n" + elements[0][0] + "\t" + elements[0][1] + "\n" + elements[1][0] + "\t" + elements[1][1];
		} else {
			return "No formatted matrix for higher than 3x3 matrices.";
		}
	}

	// Tests whether two SquareMatrix objects are equal.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(elements);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SquareMatrix other = (SquareMatrix) obj;
		if (!Arrays.deepEquals(elements, other.elements))
			return false;
		return true;
	}
}
