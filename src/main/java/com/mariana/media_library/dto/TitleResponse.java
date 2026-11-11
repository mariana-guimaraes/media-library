package com.mariana.media_library.dto;

import com.mariana.media_library.model.Title;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TitleResponse(Long id, String title, String director, Integer releaseYear, Double rating) {

	// Maps object Title to response DTO
	public static TitleResponse fromDomain(Title title) {
		if (title == null) {
			return null;
		}
		return new TitleResponse(title.getId(), title.getTitle(), title.getDirector(), title.getReleaseYear(),
				title.getRating());
	}

}
