package com.mariana.media_library.entities;

import java.util.Objects;

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

	// hashcode & equals
	@Override
	public int hashCode() {
		return Objects.hash(name.toLowerCase(), releaseYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		return Objects.equals(name.toLowerCase(), other.name.toLowerCase()) && releaseYear == other.releaseYear;
	}

	// toString
	@Override
	public String toString() {
		String watchedStatus = (watched) ? "Watched" : "Not Watched";
		return String.format("%s (%d) — %s | Rating: %.1f/10 | Status: %s", name, releaseYear, genre, rating,
				watchedStatus);
	}
}
