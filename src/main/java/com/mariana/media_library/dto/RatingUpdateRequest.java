package com.mariana.media_library.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record RatingUpdateRequest(

        @NotNull(message = "Insert a valid rating.")
        @DecimalMin(value="0.0", message = "The chosen rating must be between 0 and 10.")
        @DecimalMax(value="10.0", message = "The chosen rating must be between 0 and 10.")
        Double rating) {
}