package com.mariana.media_library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import com.mariana.media_library.model.Watchlist;
import com.mariana.media_library.services.WatchlistService;
import com.mariana.media_library.dto.WatchlistResponse;

@RestController
@RequestMapping("/api/titles/watchlist")
public class WatchlistController {

	private final WatchlistService watchlistService;

	public WatchlistController(WatchlistService watchlistService) {
		this.watchlistService = watchlistService;
	}

	@GetMapping
	public List<WatchlistResponse> getWatchlist(
			@AuthenticationPrincipal
			Jwt jwt) {
		return watchlistService.findByUserId(jwt.getSubject());
		}

	@PostMapping("/{titleId}")
	public ResponseEntity<Void> addTitle(
			@AuthenticationPrincipal 
			Jwt jwt, 
			@PathVariable 
			Long titleId) {
		watchlistService.add(jwt.getSubject(), titleId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{titleId}")
	public ResponseEntity<Void> removeTitle(
			@AuthenticationPrincipal 
			Jwt jwt, 
			@PathVariable 
			Long titleId) {
		watchlistService.remove(jwt.getSubject(), titleId);
		return ResponseEntity.noContent().build();
	}
}
