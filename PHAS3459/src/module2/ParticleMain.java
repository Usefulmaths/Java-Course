package module2;

public class ParticleMain {
	public static void main(String[] args) {
		FallingParticle particle = new FallingParticle(4.8, 3.2);

		// Particle dropping from height 6m in time steps of 0.5s
		particle.setZ(6);
		particle.drop(0.5);
		System.out.println("Time taken: " + particle.getT() + "\t Velocity: " + particle.getV());

		// Particle dropping from height 6m in time steps of 0.1s
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.1);
		System.out.println("Time taken: " + particle.getT() + "\t Velocity: " + particle.getV());

		// Particle dropping from height 6m in time steps of 0.01s
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.01);
		System.out.println("Time taken: " + particle.getT() + "\t Velocity: " + particle.getV());

		// Particle dropping from height 6m in time steps of 0.001s
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.001);
		System.out.println("Time taken: " + particle.getT() + "\t Velocity: " + particle.getV());

		// Particle dropping from height 6m in time steps of 0.0001s
		particle.setZ(6);
		particle.setT(0);
		particle.setV(0);
		particle.drop(0.0001);
		System.out.println("Time taken: " + particle.getT() + "\t Velocity: " + particle.getV());

		System.out.println(
				"As we decrease the increment in time, we get a more accurate result for the time taken to fall to the ground and the velocity at the time of impact. This is due to z being closer to zero and not overshooting it as much");
	}

}
