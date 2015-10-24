package module3;

public class TestExceptions {
	public static void main(String[] args) throws Exception {
		// Testing Complex Exceptions
		Complex c1 = new Complex(1, 7);
		Complex c2 = new Complex(0, 0);

		// Normalisation Exception
		try {
			c2.normalised();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Division Exception
		try {
			Complex.divide(c1, c2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Testing ThreeVector Exceptions
		ThreeVector v1 = new ThreeVector(1, 1, 1);
		ThreeVector v2 = new ThreeVector(0, 0, 0);

		// Unit Vector Exception
		try {
			v2.unitVector();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Angle Exception
		try {
			v1.angle(v2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Testing FallingParticle Exceptions

		// Mass Exception
		try {
			FallingParticle particle1 = new FallingParticle(-1, 2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Drag Coefficient Exception
		try {
			FallingParticle particle2 = new FallingParticle(4.2, -4.6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Height Exception
		FallingParticle particle3 = new FallingParticle(4.2, 3.2);
		try {
			particle3.setZ(-0.3);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Time Increment Exception

		try {
			particle3.doTimeStep(-0.1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			particle3.drop(-0.1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
