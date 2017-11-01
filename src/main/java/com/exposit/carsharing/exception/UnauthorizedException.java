package com.exposit.carsharing.exception;

public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("Unauthorized");
    }

}
