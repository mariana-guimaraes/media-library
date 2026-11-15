package com.mariana.media_library.dto.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbTitle(
        Long id,
        String title,
        @JsonProperty("release_date") 
        String releaseDate,
        String overview,
        @JsonProperty("vote_average") 
        Double voteAverage) {
	
}