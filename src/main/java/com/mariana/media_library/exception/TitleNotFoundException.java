package com.mariana.media_library.exception;

public class TitleNotFoundException extends RuntimeException {

	public TitleNotFoundException(Long id) {
		super("Title not found: " + id);
	}

}
