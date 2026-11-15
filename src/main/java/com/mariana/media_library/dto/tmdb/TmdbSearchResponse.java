package com.mariana.media_library.dto.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TmdbSearchResponse(List<TmdbTitle> results) {
}