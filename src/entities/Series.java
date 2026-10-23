package entities;

import java.util.ArrayList;
import java.util.List;

public class Series extends Title {

	List<SeriesSeason> series = new ArrayList<>();

	// constructor
	public Series(String name, int releaseYear, Genre genre) {
		super(name, releaseYear, genre);
	}

	// methods

	public void addSeason(SeriesSeason season) {
		if (season == null) {
			throw new IllegalArgumentException("Type a valid season name.");
		}
		series.add(season);
	}

	public void removeSeason(SeriesSeason season) {
		series.remove(season);
	}

	public List<SeriesSeason> getSeason() {
		return List.copyOf(series);
	}

	@Override
	public int getDurationInMinutes() {
		int totalMinutes = 0;
		for (SeriesSeason season : series) {
			for (SeriesEpisode episode : season.getEpisodes()) {
				totalMinutes += episode.getDurationInMinutes();
			}
		}
		return totalMinutes;
	}

}
