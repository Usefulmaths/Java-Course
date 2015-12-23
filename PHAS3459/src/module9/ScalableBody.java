package module9;

public class ScalableBody {
	private final Body body;
	private final Dimensions scaleFactor;

	public ScalableBody(final Body body, final Dimensions scaleFactor) {
		this.body = body;
		this.scaleFactor = scaleFactor;
	}

	public Body getBody() {
		return body;
	}

	public Dimensions getScaleFactor() {
		return scaleFactor;
	}
}
