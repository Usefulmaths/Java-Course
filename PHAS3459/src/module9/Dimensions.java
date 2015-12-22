package module9;

public class Dimensions {
	public final int width;
	public final int height;

	public static Dimensions square(final int length) {
		return new Dimensions(length, length);
	}

	public Dimensions(final int width, final int height) {
		this.width = width;
		this.height = height;
	}
}
