package com.crejo.fun.exception;

public class MultipleReviewsNotAllowedException extends RuntimeException{

    public MultipleReviewsNotAllowedException(String message) {
        super(message);
    }
}
