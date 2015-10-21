package module3;

import java.util.Random;
import java.lang.Character;
import java.lang.Integer;

public class Alphabet {
	// No need to generate a new Random object each time the method is called.
	private static Random rand = new Random();

	public static void main(String[] args) throws Exception {
		StringBuilder strBuilder = new StringBuilder();
		int totalDigits = 0;
		int totalExceptions = 0;

		for (int i = 0; i < 500; i++) {
			final char randomChar = randomCharacter();
			if (!Character.isLetterOrDigit(randomChar)) {
				continue;
			}

			strBuilder.append(randomChar);

			try {
				final int integer = Integer.parseInt(Character.toString(randomChar));
				totalDigits += integer;

			} catch (final NumberFormatException e) {
				totalExceptions++;
			}
		}

		System.out.println("StringBuilder result: " + strBuilder);
		System.out.println("Total of Numbers: " + totalDigits);
		System.out.println("Total Exceptions: " + totalExceptions);
	}

	private static char randomCharacter() {
		int numberFromRandom = rand.nextInt(128);
		char numberToRandomCharacter = (char) numberFromRandom;
		return numberToRandomCharacter;

	}

}
