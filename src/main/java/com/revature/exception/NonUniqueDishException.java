package com.revature.exception;

public class NonUniqueDishException extends Exception {

	public NonUniqueDishException () {
        super("The requested dish is taken.");
    }
}
