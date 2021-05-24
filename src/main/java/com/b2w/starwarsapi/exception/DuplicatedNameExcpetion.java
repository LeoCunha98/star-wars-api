package com.b2w.starwarsapi.exception;

public class DuplicatedNameExcpetion extends RuntimeException {
    public DuplicatedNameExcpetion(String message) {
        super(message);
    }

    public DuplicatedNameExcpetion(String message, Throwable cause){
        super(message, cause);
    }
}
