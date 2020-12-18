package com.revature.exception;

public class NonUniqueUsernameException extends Exception {
    public NonUniqueUsernameException () {
        super("The requested username is taken.");
    }
}

