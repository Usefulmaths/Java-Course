package module9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolarSystem {
	private static List<ImagedBody> bodies;
	private static final int secondsPerTick = 24 * 60 * 60;
	private static double elapsedTicks = 0;

	public SolarSystem(final List<ImagedBody> bodies) {
		this.bodies = bodies;
	}

	public Vector calculateTotalForce(final ImagedBody body) {
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
		final Map<ImagedBody, Vector> forcesToApply = new HashMap<>();

		bodies.forEach(body -> {
			final Vector totalForce = calculateTotalForce(body);
			forcesToApply.put(body, totalForce);
		});

		forcesToApply.entrySet().forEach(entrySet -> {
			entrySet.getKey().incrementMovement(entrySet.getValue(), secondsPerTick);
		});
	}

	public static List<ImagedBody> getBodies() {
		return bodies;
	}

	public static int getSecondsPerTick() {
		return secondsPerTick;
	}

	public static double getElapsedTicks() {
		return elapsedTicks;
	}
}
