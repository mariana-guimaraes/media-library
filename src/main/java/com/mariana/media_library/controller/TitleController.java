
package com.mariana.media_library.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mariana.media_library.dto.TitleRequest;
import com.mariana.media_library.dto.TitleResponse;
import com.mariana.media_library.dto.RatingUpdateRequest;
import com.mariana.media_library.exception.TitleNotFoundException;
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
    	TitleResponse response = titleService.findById(id);
    	return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TitleResponse> create(
    		@Valid @RequestBody 
    		TitleRequest request) {
    	TitleResponse created = titleService.create(request);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(created.id())
    			.toUri();
    	return ResponseEntity.created(location).body(created);
    	}

    @PutMapping("/{id}")
    public ResponseEntity<TitleResponse> update(
    		@PathVariable 
    		Long id,
    		@Valid @RequestBody TitleRequest request) {
    	TitleResponse updated = titleService.update(id, request)
    			.orElseThrow(() -> new TitleNotFoundException(id));
    	return ResponseEntity.ok(updated);
    	}

    @PatchMapping("/{id}/rating")
    public ResponseEntity<TitleResponse> updateRating(
    		@PathVariable 
    		Long id,
    		@Valid @RequestBody 
    		RatingUpdateRequest request) {
    	TitleResponse updated = titleService.updateRating(id, request)
    			.orElseThrow(() -> new TitleNotFoundException(id));
    	return ResponseEntity.ok(updated);
    	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
    		@PathVariable 
    		Long id) {
    	boolean removed = titleService.delete(id);
    	if (!removed) {
    		throw new TitleNotFoundException(id);
        }
    	return ResponseEntity.noContent().build();
    	}
}
