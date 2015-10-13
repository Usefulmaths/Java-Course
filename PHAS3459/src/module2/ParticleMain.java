package module2;

public class ParticleMain {
	public static void main(String[] args){
		FallingParticle particle = new FallingParticle(4.8,3.2);
		
		particle.setZ(6);
		particle.drop(0.5);
		
		
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.1);
		
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.01);
		
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.001);
		
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.0001);
		
		System.out.println("As we decrease the increment in time, we get a more accurate result for the time taken to fall to the ground.");
	}

}
