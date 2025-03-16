package com.framework.exceptions;

public class InvalidPathForFilesException extends FrameworkException{
	public InvalidPathForFilesException(String message) {
		super(message);
	}
	public InvalidPathForFilesException(String message,Throwable cause) {
		super(message,cause);
	}

}
