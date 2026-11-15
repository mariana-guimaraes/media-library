package com.mariana.media_library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mariana.media_library.dto.TitleCandidateResponse;
import com.mariana.media_library.dto.TitleResponse;
import com.mariana.media_library.services.TmdbService;

@RestController
@RequestMapping("/api/tmdb/titles")
public class TmdbTitleController {

	 private final TmdbService tmdbService;

	    public TmdbTitleController(TmdbService tmdbService) {
	        this.tmdbService = tmdbService;
	    }

	    @GetMapping("/search")
	    public ResponseEntity<List<TitleCandidateResponse>> search(
	    		@RequestParam 
	    		String query) {
	        return ResponseEntity.ok(tmdbService.searchCandidates(query));
	    }

	    @PostMapping("/{tmdbId}/import")
	    public ResponseEntity<TitleResponse> importTitle(
	    		@PathVariable 
	    		long tmdbId) {
	        TitleResponse imported = tmdbService.importTitle(tmdbId);
	        return ResponseEntity.status(HttpStatus.CREATED).body(imported);
	    }
}

