package com.mariana.media_library.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mariana.media_library.dto.WatchlistResponse;
import com.mariana.media_library.exception.TitleNotFoundException;
import com.mariana.media_library.model.Title;
import com.mariana.media_library.model.Watchlist;
import com.mariana.media_library.repositories.TitleRepository;
import com.mariana.media_library.repositories.WatchlistRepository;

@ExtendWith(MockitoExtension.class)
class WatchlistServiceTest {

	@Mock
	private WatchlistRepository watchlistRepository;

	@Mock
	private TitleRepository titleRepository;

	@InjectMocks
	private WatchlistService watchlistService;

	private final String userId = "user-123";
	private final Long titleId = 1L;

	@Test
	void shouldReturnUserWatchlist() {
		Watchlist item = mock(Watchlist.class);
		Title title = mock(Title.class);

		when(item.getId()).thenReturn(10L);
		when(item.getTitle()).thenReturn(title);
		when(title.getId()).thenReturn(1L);
		when(item.getAddedAt()).thenReturn(java.time.LocalDateTime.of(2026, 11, 21, 12, 0));

		when(watchlistRepository.findByUserId(userId)).thenReturn(List.of(item));

		List<WatchlistResponse> result = watchlistService.findByUserId(userId);

		assertEquals(1, result.size());
		assertEquals(1L, result.get(0).titleId());
		verify(watchlistRepository).findByUserId(userId);
	}

	@Test
	void shouldAddTitleToWatchlist() {
		Title title = mock(Title.class);

		when(titleRepository.findById(titleId)).thenReturn(Optional.of(title));
		watchlistService.add(userId, titleId);
		verify(watchlistRepository).save(any(Watchlist.class));
	}

	@Test
	void shouldNotAddDuplicateTitle() {
		Title title = mock(Title.class);

		when(titleRepository.findById(titleId)).thenReturn(Optional.of(title));
		when(watchlistRepository.existsByUserIdAndTitleId(userId, titleId)).thenReturn(true);
		watchlistService.add(userId, titleId);
		verify(watchlistRepository, never()).save(any(Watchlist.class));
	}

	@Test
	void shouldThrowWhenTitleDoesNotExist() {
		when(titleRepository.findById(titleId)).thenReturn(Optional.empty());
		assertThrows(TitleNotFoundException.class, () -> watchlistService.add(userId, titleId));
	}

	@Test
	void shouldRemoveTitleFromWatchlist() {
		Watchlist item = mock(Watchlist.class);

		when(watchlistRepository.findByUserIdAndTitleId(userId, titleId)).thenReturn(Optional.of(item));
		watchlistService.remove(userId, titleId);
		verify(watchlistRepository).delete(item);
	}
}
