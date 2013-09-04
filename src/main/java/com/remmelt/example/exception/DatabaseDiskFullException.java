package com.remmelt.example.exception;

public class DatabaseDiskFullException extends RuntimeException{
	public DatabaseDiskFullException(String msg) {
		super(msg);
	}
}
