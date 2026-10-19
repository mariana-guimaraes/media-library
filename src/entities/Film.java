package entities;

public class Film {

	private String name;
	private int releaseYear;
	private final Genre genre;
	private boolean watched;
	private double score;

	// constructor
	public Film(String name, int releaseYear, Genre genre) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name is empty.");
		}
		if (releaseYear < 1888) { // year when the first film was made
			throw new IllegalArgumentException("Year not valid.");
		}
		this.name = name;
		this.releaseYear = releaseYear;
		this.genre = genre;
	}

	// gets & sets

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	// methods

	public void setAsWatched() {
		this.watched = true;
	}

	public void evaluate(double score) {
		if (score < 0.0 || score > 10.0) {
			throw new IllegalArgumentException("The chosen score must be between 0 and 10.");
		}
		this.score = score;
	}

}
