package services;

import java.util.ArrayList;
import java.util.List;

import entities.Film;

public class Catalog {

	private List<Film> filmList = new ArrayList<>();

	public void add(Film film) {
		if (film == null) {
			throw new IllegalArgumentException("Type a valid film name.");
		}
		filmList.add(film);
	}

	public void remove(Film film) {
		filmList.remove(film);
	}

	public List<Film> getFilms() {
		return filmList;
	}

	public List<Film> searchByName(String name) {
		List<Film> results = new ArrayList<>();
		for (Film film : filmList) {
			if (film.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(film);
			}
		}
		return results;
	}

}
