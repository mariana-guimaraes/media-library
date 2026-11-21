package com.mariana.media_library.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mariana.media_library.exception.TitleNotFoundException;
import com.mariana.media_library.model.Title;
import com.mariana.media_library.model.Watchlist;
import com.mariana.media_library.repositories.TitleRepository;
import com.mariana.media_library.repositories.WatchlistRepository;
import com.mariana.media_library.dto.WatchlistResponse;

@Service
public class WatchlistService {

	private final WatchlistRepository watchlistRepository;
	private final TitleRepository titleRepository;

	public WatchlistService(WatchlistRepository watchlistRepository, TitleRepository titleRepository) {
		this.watchlistRepository = watchlistRepository;
		this.titleRepository = titleRepository;
	}

	@Transactional(readOnly = true)
	public List<WatchlistResponse> findByUserId(String userId) {
		return watchlistRepository.findByUserId(userId)
				.stream()
				.map(item -> new WatchlistResponse(
					item.getId(),
					item.getTitle().getId(),
					item.getAddedAt()
					))
				.toList();
		}

	@Transactional
	public void add(String userId, Long titleId) {
		Title title = titleRepository.findById(titleId).orElseThrow(() -> new TitleNotFoundException(titleId));

		if (watchlistRepository.existsByUserIdAndTitleId(userId, titleId)) {
			return;
		}

		watchlistRepository.save(new Watchlist(userId, title));
	}

	@Transactional
	public void remove(String userId, Long titleId) {
		watchlistRepository.findByUserIdAndTitleId(userId, titleId).ifPresent(watchlistRepository::delete);
	}
}
