package com.mariana.media_library.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mariana.media_library.dto.TitleResponse;
import com.mariana.media_library.exception.TitleNotFoundException;
import com.mariana.media_library.model.Title;
import com.mariana.media_library.repositories.TitleRepository;

@ExtendWith(MockitoExtension.class)
public class TitleServiceTest {

	@Mock
	private TitleRepository repository;

	@InjectMocks
	private TitleService titleService;

	// test w existing title
	@Test
	void findById_whenTitleExists_returnsTitleResponse() {
		Long id = 1L;
		Title title = new Title(null, "Dune", "Denis Villeneuve", 2021, null);

		when(repository.findById(id)).thenReturn(Optional.of(title));

		TitleResponse response = titleService.findById(id);

		assertNotNull(response);
		assertEquals("Dune", response.title());

		verify(repository).findById(id);
	}

	//test w non existing title
	@Test
	void findById_whenTitleDoesNotExist_throwsException() {

		Long id = 999L;

		when(repository.findById(id)).thenReturn(Optional.empty());

		TitleNotFoundException exception = assertThrows(TitleNotFoundException.class,()->titleService.findById(id));

		assertEquals("Title not found: 999", exception.getMessage());

		verify(repository).findById(id);
	}
}