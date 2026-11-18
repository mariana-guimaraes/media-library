package com.mariana.media_library.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mariana.media_library.client.TmdbClient;
import com.mariana.media_library.dto.TitleCandidateResponse;
import com.mariana.media_library.dto.TitleResponse;
import com.mariana.media_library.dto.tmdb.TmdbSearchResponse;
import com.mariana.media_library.dto.tmdb.TmdbTitle;
import com.mariana.media_library.model.Title;
import com.mariana.media_library.repositories.TitleRepository;

import jakarta.transaction.Transactional;

@Service
public class TmdbService {

	private final TmdbClient tmdbClient;
	private final TitleRepository titleRepository;

	public TmdbService(TmdbClient tmdbClient, TitleRepository titleRepository) {
		this.tmdbClient = tmdbClient;
		this.titleRepository = titleRepository;
	}

	public List<TitleCandidateResponse> searchCandidates(String query) {
		TmdbSearchResponse response = tmdbClient.searchMovie(query);
		if (response == null) {
			throw new IllegalStateException("Invalid response.");
		}
		if (response.results() == null) {
			throw new IllegalStateException("There are no results.");
		}
		return response.results().stream()
				.map(tmdb -> new TitleCandidateResponse(tmdb.id(), tmdb.title(), tmdb.releaseDate())).toList();
	}

	@Transactional
	public TitleResponse importTitle(long tmdbId) {
		TmdbTitle external = tmdbClient.getMovieDetails(tmdbId);
		int year = 2026;
		if (external.releaseDate() != null && !external.releaseDate().isBlank()) {
			year = LocalDate.parse(external.releaseDate()).getYear();
		}
		Title title = new Title();
		title.setTitle(external.title());
		title.setDirector("Unknown (TMDB)");
		title.setReleaseYear(year);
		title.setRating(external.voteAverage() != null ? external.voteAverage() : 0.0);

		Title saved = titleRepository.save(title);

		return new TitleResponse(saved.getId(), saved.getTitle(), saved.getDirector(), saved.getReleaseYear(),
				saved.getRating());
	}
}
