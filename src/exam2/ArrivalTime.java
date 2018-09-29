package exam2;

import java.util.List;

/**
 * Represents a method of calculating the arrival time of a pulse.
 */
public interface ArrivalTime {
	// methods in an interface default to public and abstract, no need to
	// specify.
	
	// returns a double given a Pulse object.
	double arrivalTimeCalculation(final Pulse pulse);
}
