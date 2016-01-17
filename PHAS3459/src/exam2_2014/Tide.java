package exam2_2014;

public class Tide {

	private final String identifier;
	private final Date date;
	private final SeaLevel seaLevel;

	public Tide(String identifier, Date date, SeaLevel seaLevel) {
		this.identifier = identifier;
		this.date = date;
		this.seaLevel = seaLevel;
	}

	@Override
	public String toString() {
		return "Tide [identifier=" + identifier + ", date=" + date + ", seaLevel=" + seaLevel + "]";
	}

	public String getIdentifier() {
		return identifier;
	}

	public Date getDate() {
		return date;
	}

	public SeaLevel getSeaLevel() {
		return seaLevel;
	}
	
	public double getTidalSurge(){
		return seaLevel.getActualSeaLevel() - seaLevel.getPredictedSeaLevel();
	}
	
	
}
