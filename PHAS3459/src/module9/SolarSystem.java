package module9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolarSystem {
	public static List<ImagedBody> bodies;
	public static int secondsPerTick = 24 * 60 * 60;
	public static double timer = 0;

	public SolarSystem(final List<ImagedBody> bodies) {
		this.bodies = bodies;
	}

	public Vector calculateTotalForce(ImagedBody body) {
		return bodies.stream()
				.filter(test -> test != body)
				.map(body::calculateForce)
				.reduce(
						Vector.VECTOR_ZERO,
						Vector::add
					);
	}

	public void tick() {
		timer++;
		final Map<ImagedBody, Vector> forcesToApply = new HashMap<>();

		bodies.forEach(body -> {
			final Vector totalForce = calculateTotalForce(body);
			forcesToApply.put(body, totalForce);
		});

		forcesToApply.entrySet().forEach(entrySet -> {
			entrySet.getKey().incrementMovement(entrySet.getValue(), secondsPerTick);
		});
	}
}
