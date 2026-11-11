package com.mariana.media_library.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genre")
	private Genre genre;
	
	@Column(name = "title", nullable = false, length = 120)
	private String title;
	
	@Column(name = "director", nullable = false)
	private String director;
	
	@Column(name = "release_year", nullable = false)
	private int releaseYear;
	
	@Column(name = "rating", nullable = false)
	private double rating;

	// constructor
	public Title() {
	}

	public Title(String title, String director, int releaseYear, Genre genre) {
		if (title == null || title.isBlank()) {
			throw new IllegalArgumentException("title is empty.");
		}
		if (releaseYear < 1888) { // year when the first film was made
			throw new IllegalArgumentException("Year not valid.");
		}
		this.director = director;
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
	}

	// gets & sets

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return title;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Genre getGenre() {
		return genre;
	}

	public double getRating() {
		return rating;
	}

	// hashcode & equals
	@Override
	public int hashCode() {
		return Objects.hash(title.toLowerCase(), releaseYear);
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
		return Objects.equals(title.toLowerCase(), other.title.toLowerCase()) && releaseYear == other.releaseYear;
	}

}
