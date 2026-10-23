package entities;

public abstract class Title {

	private final Genre genre;
	private String name;
	private int releaseYear;
	private double rating;
	private double ratingSum;
	private int ratingQuantity;
	private boolean watched;

	// constructor
	public Title(String name, int releaseYear, Genre genre) {
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

	public abstract int getDurationInMinutes();

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

	public boolean isWatched() {
		return watched;
	}

	public Genre getGenre() {
		return genre;
	}

	public double getRating() {
		return rating;
	}

	public double getRatingSum() {
		return ratingSum;
	}

	public int getRatingQuantity() {
		return ratingQuantity;
	}

	// methods
	public void evaluate(double rating) {
		if (rating < 0.0 || rating > 10.0) {
			throw new IllegalArgumentException("The chosen rating must be between 0 and 10.");
		}
		this.rating = rating;
	}

	public double ratingAverage() {
		return ratingSum / ratingQuantity;
	}

	public void setAsWatched() {
		this.watched = true;
	}
}
