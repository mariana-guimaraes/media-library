package com.mariana.media_library.web.error;

import java.time.Instant;

public record ApiError(Instant timestamp, int status, String code, String message, String path) {

}
