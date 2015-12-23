package module9;

public class DimensionsFactory {

	public Dimensions square(final int length) {
		return new Dimensions(length, length);
	}
	
}
