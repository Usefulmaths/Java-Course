package module5;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SquareMatrix {
	private final double[][] elements;

	public SquareMatrix(final double[][] elements) throws Exception {
		if (isSquare(elements)) {
			throw new Exception("Invalid matrix, must be a square matrix.");
		}
		this.elements = elements;
	}

	// Method to test whether matrix is square.
	private static boolean isSquare(final double[][] elements) {
		return Math.pow(elements.length, 2) != countElements(elements);
	}

	// Method to test whether two matrices are of the same dimensions.
	private static boolean areMatricesSameSize(final SquareMatrix sm1, final SquareMatrix sm2) {
		return sm1.getRowCount() != sm2.getRowCount() && sm1.getElementCount() != sm2.getElementCount();
	}

	private static int countElements(final double[][] elements) {
		return Arrays.stream(elements).mapToInt(row -> row.length).sum();
	}

	private int getRowCount() {
		return elements.length;
	}

	private int getElementCount() {
		return countElements(elements);
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

	//toString, pretty prints matrices using functional programming.
	@Override
	public String toString() {
		return Arrays.stream(elements)
			.map(row -> {
				return Arrays.toString(row) + "\n";
			})
			.collect(Collectors.joining())
			.replaceAll(",", "\t")
			.replaceAll("\\[", "[ ")
			.replaceAll("]", " ]");
	}

	// hashCode() and equals(Object) test whether two SquareMatrix objects are
	// equal.
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
