package module9;

import java.io.BufferedWriter;
import static module9.Constants.AU;
import static module9.Constants.MASS_EARTH;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SolarSystem {
	public static List<Body> bodies;
	public static int secondsPerTick = 24*60*60;

	public SolarSystem(final List<Body> bodies) {
		this.bodies = bodies;
	}

	public static Vector calculateTotalForce(Body body) {
		return bodies.stream().filter(test -> test != body).map(body::calculateForce).reduce(Vector.VECTOR_ZERO,
				Vector::add);
	}

	public static void tick() {
		final Map<Body, Vector> forcesToApply = new HashMap<>();

		bodies.forEach(body -> {
			final Vector totalForce = calculateTotalForce(body);
			forcesToApply.put(body, totalForce);
		});

		forcesToApply.entrySet().forEach(entrySet -> {
			entrySet.getKey().incrementMovement(entrySet.getValue(), secondsPerTick);
		});
	}
}
