package com.revature.exception;

public class AlreadyVotedException extends Exception {
    public AlreadyVotedException(){
        super("User already voted for this dish.");
    }
}
