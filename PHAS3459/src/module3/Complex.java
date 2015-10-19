package module3;

public class Complex {
	private double real;
	private double imag;

	// Complex class versions of One, zero and I.
	public final static Complex ONE = new Complex(1, 0);
	public final static Complex ZERO = new Complex(0, 0);
	public final static Complex I = new Complex(0, 1);

	// Constructor to set/store real and imaginary parts of complex number.
	public Complex(final double real, final double imag) {
		this.real = real;
		this.imag = imag;
	}

	// Returns the real component.
	public double getReal() {
		return real;
	}

	// Returns the imaginary component.
	public double getImag() {
		return imag;
	}

	// Modulus of complex number. Square root of sum of real sq and imag sq.
	public double modulus() {
		return Math.sqrt(this.real * this.real + this.imag * this.imag);
	}

	// Returns angle in the complex plane anti-clockwise from pos real-axis
	// measured in radians.
	public double angle() {

		if (this.imag >= 0) {
			// Positive-Positive Quadrant
			if (this.real >= 0) {
				return Math.atan(this.imag / this.real);
			}
			// Negative-Positive Quadrant
			return Math.PI - Math.atan(this.imag / -this.real);
		}
		if (this.real >= 0) {
			// Positive-Negative Quadrant
			return 2 * Math.PI - Math.atan(-this.imag / this.real);
		}
		// Negative-Negative Quadrant
		return Math.PI + Math.atan(-this.imag / -this.real);

	}

	// Creates new Complex object with sign change on imaginary
	public Complex conjugate() {
		return new Complex(this.real, -this.imag);
	}

	// Creates new Complex object with modulus 1, same angle
	public Complex normalised() throws Exception {
		try {
			Complex.isModulusZero(this);
			return new Complex(this.real / this.modulus(), this.imag / this.modulus());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// Tests components of two Complex classes and determines if they're equal
	public boolean equals(final Complex c) {
		if (this.real == c.real && this.imag == c.imag) {
			return true;
		} else {
			return false;
		}
	}

	// Allows object to be printed to nice formatted String
	public String toString() {
		// Deals with "+" or "-" sign depending on imaginary component
		if (this.imag >= 0) {
			return this.real + " + " + this.imag + "i";
		}
		return this.real + " - " + -this.imag + "i";
	}

	// Takes a magnitude and angle and creates complex number x + iy
	public void setFromModulusAngle(final double mag, final double ang) {

		if (ang >= 0 && ang <= Math.PI / 2) {
			// Positive-Positive Quadrant
			this.real = mag * Math.cos(ang);
			this.imag = mag * Math.sin(ang);
		} else if (ang > Math.PI / 2 && ang <= Math.PI) {
			// Negative-Positive Quadrant
			this.real = -mag * Math.sin(ang - Math.PI / 2);
			this.imag = mag * Math.cos(ang - Math.PI / 2);
		} else if (ang > Math.PI && ang <= Math.PI * 3 / 2) {
			// Negative-Negative Quadrant
			this.real = -mag * Math.cos(ang - Math.PI);
			this.imag = -mag * Math.sin(ang - Math.PI);
		} else if (ang > Math.PI * 3 / 2 && ang <= Math.PI * 2) {
			// Positive-Negative Quadrant
			this.real = mag * Math.sin(ang - Math.PI * 3 / 2);
			this.real = -mag * Math.cos(ang - Math.PI * 3 / 2);
		}
	}

	// Complex Addition
	public static Complex add(final Complex c1, final Complex c2) {
		return new Complex(c1.real + c2.real, c1.imag + c2.imag);
	}

	// Complex subtraction
	public static Complex subtract(final Complex c1, final Complex c2) {
		return new Complex(c1.real - c2.real, c1.imag - c2.imag);
	}

	// Complex multiplication
	public static Complex multiply(final Complex c1, final Complex c2) {
		return new Complex(c1.real * c2.real - c1.imag * c2.imag, c1.real * c2.imag + c2.real * c1.imag);
	}

	// Complex division
	public static Complex divide(final Complex c1, final Complex c2) throws Exception {
		// Normalises so denominator is a real value
		try {
			Complex.isModulusZero(c2);
			return new Complex(Complex.multiply(c1, c2.conjugate()).real / (c2.real * c2.real + c2.imag * c2.imag),
					Complex.multiply(c1, c2.conjugate()).imag / (c2.real * c2.real + c2.imag * c2.imag));

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static void isModulusZero(Complex complex) throws Exception {
		if (complex.modulus() == 0) {
			throw new Exception("The modulus of this complex number is zero! " + complex);
		}

	}

}
