package module2;

public class Complex {
	double real;
	double imag;

	static Complex ONE = new Complex(1, 0);
	static Complex ZERO = new Complex(0, 0);
	static Complex I = new Complex(0, 1);

	public Complex(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}

	public double getReal() {
		return real;
	}

	public double getImag() {
		return imag;
	}

	double modulus() {
		return Math.sqrt(this.real * this.real + this.imag * this.imag);
	}

	double angle() {

		if (this.imag > 0) {
			if (this.real >= 0) {
				return Math.atan(this.imag / this.real);
			}
			return Math.atan(this.imag / -this.real);
		}
		if (this.real > 0) {
			return 2 * Math.PI - Math.atan(-this.imag / this.real);
		}
		return Math.PI + Math.atan(-this.imag / -this.real);
	}

	Complex conjugate() {
		return new Complex(this.real, -this.imag);
	}

	Complex normalised() {
		return new Complex(this.real / this.modulus(), this.imag / this.modulus());
	}

	boolean equals(Complex c) {
		if (this.real == c.real && this.imag == c.imag) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return this.real + " + " + this.imag + "i";
	}

	void setFromModulusAngle(double mag, double ang) {

		if (ang >= 0 && ang <= Math.PI / 2) {

			this.real = mag * Math.cos(ang);
			this.imag = mag * Math.sin(ang);

		} else if (ang > Math.PI / 2 && ang <= Math.PI) {
			this.real = -mag * Math.sin(ang - Math.PI / 2);
			this.imag = mag * Math.cos(ang - Math.PI / 2);
		} else if (ang > Math.PI && ang <= Math.PI * 3 / 2) {
			this.real = -mag * Math.cos(ang - Math.PI);
			this.imag = -mag * Math.sin(ang - Math.PI);
		} else if (ang > Math.PI * 3 / 2 && ang <= Math.PI * 2) {
			this.real = mag * Math.sin(ang - Math.PI * 3 / 2);
			this.real = -mag * Math.cos(ang - Math.PI * 3 / 2);
		}
	}

	static Complex add(Complex c1, Complex c2) {
		return new Complex(c1.real + c2.real, c1.imag + c2.imag);
	}

	static Complex subtract(Complex c1, Complex c2) {
		return new Complex(c1.real - c2.real, c1.imag - c2.imag);
	}

	static Complex multiply(Complex c1, Complex c2) {
		return new Complex(c1.real * c2.real - c1.imag * c2.imag, c1.real * c2.imag + c2.real * c1.imag);
	}

	static Complex divide(Complex c1, Complex c2) {
		return new Complex(Complex.multiply(c1, c2.conjugate()).real / (c2.real * c2.real + c2.imag * c2.imag),
				Complex.multiply(c1, c2.conjugate()).imag / (c2.real * c2.real + c2.imag * c2.imag));
	}

}
