package module2;

public class FallingParticle {
	double m;
	double d;
	double t = 0;
	double z = 10;
	double v = 0;
	double g = 9.81;
	
	public FallingParticle(double m, double d){
		this.m = m;
		this.d = d;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getV() {
		return v;
	}

	public void setV(double v) {
		this.v = v;
	}

	void doTimeStep(double deltaT){
		double a = d*v*v/m - g;
		
		this.setV(this.getV() + a*deltaT);
		this.setZ(this.getZ() + v*deltaT);
		this.setT(this.getT() + deltaT);
	}
	
	void drop(double deltaT){
		while(this.z > 0){
			this.doTimeStep(deltaT);
		}
		System.out.println(this.getT());
	}
	
}
