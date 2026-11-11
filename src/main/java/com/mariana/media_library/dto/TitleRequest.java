package com.mariana.media_library.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TitleRequest(

		@NotBlank(message = "Title can not be blank.")
		@Size(max = 120, message = "Title can not exceed 120 characters.")
		String title,

		@NotBlank(message = "Director can not be blank.")
		String director,

		@NotNull(message = "Year can not be blank.")
		@Min(value = 1888, message = "Year must be over 1888")
		Integer releaseYear) {

}
