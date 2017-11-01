package com.exposit.carsharing.exception;

public class UserUnauthorizedException extends Exception {

    public UserUnauthorizedException(String message) {
        super(message);
    }

    public UserUnauthorizedException() {
        super("Unauthorized");
    }

}
