package exam2_2014;

public class SeaLevel {

	private final double actualSeaLevel;
	private final double predictedSeaLevel;

	public SeaLevel(double actualSeaLevel, double predictedSeaLevel) {
		this.actualSeaLevel = actualSeaLevel;
		this.predictedSeaLevel = predictedSeaLevel;
	}

	@Override
	public String toString() {
		return "SeaLevel [actualSeaLevel=" + actualSeaLevel + ", predictedSeaLevel=" + predictedSeaLevel + "]";
	}

	public double getActualSeaLevel() {
		return actualSeaLevel;
	}

	public double getPredictedSeaLevel() {
		return predictedSeaLevel;
	}

}
