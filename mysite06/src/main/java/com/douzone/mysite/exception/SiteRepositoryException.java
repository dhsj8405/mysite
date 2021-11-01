package com.douzone.mysite.exception;

public class SiteRepositoryException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public SiteRepositoryException() {
		super("SiteRepositoryException Occurs");
	}

	public SiteRepositoryException(String message) {
		super(message);
	}
}
