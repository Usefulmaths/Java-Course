package module9;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hello {
	private final Map<Body, List<Vector>> positionsByBody = new HashMap<>();
	
	private final List<Body> bodies;
	
	public Hello(final List<Body> bodies) {
		this.bodies = bodies;
	}

	public void draw() {
		// record the position of every single body to the map
		bodies.forEach(body -> {
			// if the map doesn't already have the body registered
			if (!positionsByBody.containsKey(body)) {

				// make a new list for that body
				positionsByBody.put(body, new ArrayList<>());
			}

			// add the current position to the list of bodies
			positionsByBody.get(body).add(body.position);
		});
	}

	public void writeToFile() {
		positionsByBody.entrySet().stream().forEach(entry -> {
			final Body body = entry.getKey();
			try (BufferedWriter out = new BufferedWriter(new FileWriter(body.name + ".positiondata"))) {

				final List<Vector> positions = entry.getValue();

				final String positionsAsString = positions.stream()
						.map(position -> String.format("%f,%f", position.x, position.y))
						.collect(Collectors.joining("\n"));
			
				out.write(positionsAsString);

			} catch (IOException e) {
				RuntimeException up = new RuntimeException(e);
				throw up;
			}
		});
	}
}
