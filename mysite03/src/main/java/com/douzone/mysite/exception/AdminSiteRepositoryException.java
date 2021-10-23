package com.douzone.mysite.exception;

public class AdminSiteRepositoryException extends Exception {
private static final long serialVersionUID = 1L;
	
	public AdminSiteRepositoryException() {
		super("AdminSiteRepositoryException Occurs");
	}

	public AdminSiteRepositoryException(String message) {
		super(message);
	}
}
