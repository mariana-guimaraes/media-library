
package com.mariana.media_library.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mariana.media_library.dto.TitleRequest;
import com.mariana.media_library.dto.TitleResponse;
import com.mariana.media_library.dto.RatingUpdateRequest;
import com.mariana.media_library.services.TitleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

	private final TitleService titleService;

	private static final Logger log = LoggerFactory.getLogger(TitleController.class);

	public TitleController(TitleService titleService) {
		this.titleService = titleService;
	}

	@GetMapping
	public ResponseEntity<List<TitleResponse>> list() {
		List<TitleResponse> response = titleService.findAll(null);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public ResponseEntity<List<TitleResponse>> searchByTitle(
			@RequestParam(required = false) 
			String title) {
		List<TitleResponse> response = titleService.findAll(title);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TitleResponse> getById(
			@PathVariable 
			Long id) {
		Optional<TitleResponse> optionalTitle = titleService.findById(id);
		if (optionalTitle.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalTitle.get());
	}

	@PostMapping
	public ResponseEntity<TitleResponse> create(
			@Valid @RequestBody 
			TitleRequest request) {
		TitleResponse created = titleService.create(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.id())
				.toUri();
		return ResponseEntity.created(location).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TitleResponse> update(
			@PathVariable 
			Long id, 
			@Valid @RequestBody 
			TitleRequest request) {
		Optional<TitleResponse> updated = titleService.update(id, request);
		if (updated.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated.get());
	}

	@PatchMapping("/{id}/rating")
	public ResponseEntity<TitleResponse> updateRating(
			@PathVariable 
			Long id,
			@Valid @RequestBody 
			RatingUpdateRequest request) {
		Optional<TitleResponse> optionalTitleResponse = titleService.updateRating(id, request);
		if (optionalTitleResponse.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalTitleResponse.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@PathVariable 
			Long id) {
		boolean removed = titleService.delete(id);
		if (removed) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
