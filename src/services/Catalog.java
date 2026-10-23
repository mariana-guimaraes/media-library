package services;

import java.util.ArrayList;
import java.util.List;

import entities.Title;

public class Catalog {

	private List<Title> titleList = new ArrayList<>();

	public void add(Title title) {
		if (title == null) {
			throw new IllegalArgumentException("Type a valid title name.");
		}
		titleList.add(title);
	}

	public void remove(Title title) {
		titleList.remove(title);
	}

	public List<Title> getTitles() {
		return List.copyOf(titleList);
	}

	public List<Title> searchTitle(String name) {
		List<Title> results = new ArrayList<>();
		for (Title title : titleList) {
			if (title.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(title);
			}
		}
		return results;
	}

}
