package com.mariana.media_library.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mariana.media_library.model.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

	List<Watchlist> findByUserId(String userId);

	Optional<Watchlist> findByUserIdAndTitleId(String userId, Long titleId);

	boolean existsByUserIdAndTitleId(String userId, Long titleId);
}
