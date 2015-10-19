package module3;

public class TestExceptions {
	public static void main(String[] args) throws Exception{
		Complex c1 = new Complex(1,7);
		Complex c2 = new Complex(0,0);
		
		ThreeVector v1 = new ThreeVector(0,0,0);
		ThreeVector v2 = new ThreeVector(1,2,5);

		FallingParticle particle1 = new FallingParticle(-1,-1);
		FallingParticle particle2 = new FallingParticle(10,-2);
		
		
		Complex normalisedC2 = c2.normalised();
		Complex c1DividedByC2 = Complex.divide(c1, c2);
		
		v1.unitVector();
		v1.angle(v2);
		
		particle1.setZ(-3);
		particle1.doTimeStep(-4);
		particle1.drop(-5);
		
		
	}

}
