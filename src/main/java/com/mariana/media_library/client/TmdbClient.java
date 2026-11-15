package com.mariana.media_library.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.mariana.media_library.dto.tmdb.TmdbSearchResponse;
import com.mariana.media_library.dto.tmdb.TmdbTitle;

@Component
public class TmdbClient {
	private final RestClient client;

	public TmdbClient(
			RestClient.Builder builder, 
			@Value("${tmdb.base-url}") 
			String baseUrl,
			@Value("${tmdb.token}") 
			String token) {
		this.client = builder.baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
	}

	public TmdbSearchResponse searchTitle(String query) {
        return client.get()
                     .uri(uriBuilder -> uriBuilder
                            .path("/search/movie")
                            .queryParam("query", query)
                            .build()
                          )
                     .retrieve()
                     .body(TmdbSearchResponse.class);
    }
	
	public TmdbTitle getTitleDetails(Long tmdbId) {
        return client.get()
                .uri("/title/{id}", tmdbId)
                .retrieve()
                .body(TmdbTitle.class);
    }
}
