package com.mariana.media_library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mariana.media_library.dto.RatingUpdateRequest;
import com.mariana.media_library.dto.TitleRequest;
import com.mariana.media_library.dto.TitleResponse;
import com.mariana.media_library.model.Title;
import com.mariana.media_library.repositories.TitleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TitleService {

	private final TitleRepository repository;

	// constructor
	public TitleService(TitleRepository repository) {
		this.repository = repository;
	}
	
	// findAll
	@Transactional(readOnly = true)
	public List<TitleResponse> findAll(String title) {
		List<Title> titles = (title != null && !title.isBlank()) 
				? repository.findByTitleContainingIgnoreCase(title)
				: repository.findAll();
		return titles.stream().map(TitleResponse::fromDomain).toList();
	}
	
	// findById
	@Transactional(readOnly = true)
	 public Optional<TitleResponse> findById(Long id) {
        return repository.findById(id).map(TitleResponse::fromDomain);
    }

	// create
	@Transactional
	public TitleResponse create(TitleRequest request) {
		Title entity = new Title(null, request.title(), request.director(), request.releaseYear(), request.genre());
		Title saved = repository.save(entity);
		return TitleResponse.fromDomain(saved);
	}
	
	// delete
	 @Transactional
	    public boolean delete(Long id) {
	        if (repository.existsById(id)) {
	            repository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	 
	// updateRating
	@Transactional
    public Optional<TitleResponse> updateRating(Long id, RatingUpdateRequest request) {
        return repository.findById(id).map(title -> {
            title.setRating(request.rating());
            return TitleResponse.fromDomain(title);
        });
    }

}
