package com.framework.exceptions;

public class InvalidPathForExcelException extends FrameworkException{
	public InvalidPathForExcelException(String message) {
		super(message);
	}
	public InvalidPathForExcelException(String message, Throwable cause) {
		super(message, cause);
	}
}
