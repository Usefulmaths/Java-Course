package midtermexams;

public class Exoplanet {

	private String name;
	private int yearOfDiscovery;
	private String methodOfDiscovery;
	private double mass;
	private double separation;
	private double distance;

	public Exoplanet(String name, int yearOfDiscovery, String methodOfDiscovery, double mass, double separation,
			double distance) {
		this.name = name;
		this.yearOfDiscovery = yearOfDiscovery;
		this.methodOfDiscovery = methodOfDiscovery;
		this.mass = mass;
		this.separation = separation;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Exoplanet [name=" + name + ", yearOfDiscovery=" + yearOfDiscovery + ", methodOfDiscovery="
				+ methodOfDiscovery + ", mass=" + mass + ", separation=" + separation + ", distance=" + distance + "]";
	}

	public double getDistance() {
		return distance;
	}

	public String getMethodOfDiscovery() {
		return methodOfDiscovery;
	}

	public int getYearOfDiscovery() {
		return yearOfDiscovery;
	}

	public double getMass() {
		return mass;
	}

}
