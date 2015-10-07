package module1;

public class DataTypes {
	public static void main(String[] args) {

		final double doubVar = 10.0d;
		System.out.println("doubVar: " + doubVar);

		final float floatVar = 10.0f;
		System.out.println("floatVar: " + floatVar);

		final int intVar = 10;
		System.out.println("intVar: " + intVar);

		final long longVar = 10;
		System.out.println("longVar: " + longVar);

		final byte byteVar = 10;
		System.out.println("byteVar: " + byteVar);

		System.out.println("doubSq: " + doubVar * doubVar);
		System.out.println("floatSq: " + floatVar * floatVar);
		System.out.println("intSq: " + intVar * intVar);
		System.out.println("longSq: " + longVar * longVar);
		System.out.println("byteSq: " + byteVar * byteVar);

		final char charVar = 'a' + 10;
		System.out.println(charVar); // a + 10 letters = k

		final String stringVar = "a" + 10;
		System.out.println(stringVar); // 5 is concatenates to String = a5

		final String stringBooleanVar = "a" + true;
		System.out.println(stringBooleanVar); // same as above = atrue

		// When the variable i hasn't been initialised, Eclipse prompts to give
		// it an initial value.
		// We are unable to obtain an updated j by adding an integer 1. (i is
		// null)
		/**
		 * int j=1; int i; j = i + 1;
		 **/

		// When we cast a double as an integer, the resulting integer variable
		// drops the digits
		// beyond the decimal point.
		final double doubVariable = 4.99;
		final int castedDoubVariable = (int) doubVariable;
		System.out.println(castedDoubVariable);

	}
}
