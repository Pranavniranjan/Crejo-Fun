package com.crejo.fun.exception;

public class MovieNotReleasedException extends RuntimeException{

    public MovieNotReleasedException(String message) {
        super(message);
    }
}
