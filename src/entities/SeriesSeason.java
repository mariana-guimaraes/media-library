package entities;

import java.util.ArrayList;
import java.util.List;

public class SeriesSeason {

	private int number;
	private List<SeriesEpisode> season = new ArrayList<>();

	// constructor
	public SeriesSeason(int number) {
		this.number = number;
	}

	// gets & sets
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// methods
	public void addEpisode(SeriesEpisode episode) {
		if (episode == null) {
			throw new IllegalArgumentException("Type a valid episode name.");
		}
		season.add(episode);
	}

	public void removeEpisode(SeriesEpisode episode) {
		season.remove(episode);
	}

	public List<SeriesEpisode> getEpisodes() {
		return List.copyOf(season);
	}

	public List<SeriesEpisode> searchEpisode(String name) {
		List<SeriesEpisode> results = new ArrayList<>();
		for (SeriesEpisode episode : season) {
			if (episode.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(episode);
			}
		}
		return results;
	}
}
