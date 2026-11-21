
package com.mariana.media_library.dto;

import java.time.LocalDateTime;

public record WatchlistResponse(Long id, Long titleId, LocalDateTime addedAt) {
}
