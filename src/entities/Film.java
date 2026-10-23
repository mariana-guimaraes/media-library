package entities;

public class Film extends Title {

	private int durationInMinutes;

	// constructor
	public Film(String name, int releaseYear, Genre genre) {
		super(name, releaseYear, genre);
	}

	// set
	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	// methods
	@Override
	public int getDurationInMinutes() {
		return durationInMinutes;
	}

}
