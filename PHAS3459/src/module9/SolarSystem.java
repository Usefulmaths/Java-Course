package module9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolarSystem {
	private final List<Body> bodies;
	private final int secondsPerTick;
	private double elapsedTicks = 0;

	public SolarSystem(final List<Body> bodies, final int secondsPerTick) {
		this.bodies = bodies;
		this.secondsPerTick = secondsPerTick;
	}

	public Vector calculateTotalForce(final Body body) {
		return bodies.stream()
				.filter(test -> test != body)
				.map(body::calculateForce)
				.reduce(
						Vector.VECTOR_ZERO,
						Vector::add
					);
	}

	public void tick() {
		elapsedTicks++;
		final Map<Body, Vector> forcesToApply = new HashMap<>();

		bodies.forEach(body -> {
			final Vector totalForce = calculateTotalForce(body);
			forcesToApply.put(body, totalForce);
		});

		forcesToApply.entrySet().forEach(entrySet -> {
			entrySet.getKey().incrementMovement(entrySet.getValue(), secondsPerTick);
		});
	}

	public List<Body> getBodies() {
		return bodies;
	}

	public int getSecondsPerTick() {
		return secondsPerTick;
	}

	public double getElapsedTicks() {
		return elapsedTicks;
	}
}
