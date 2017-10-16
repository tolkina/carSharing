package com.exposit.carsharing.exception;

public class PrivilegeException extends Exception {

    public PrivilegeException(String message) {
        super(message);
    }

    public PrivilegeException() {
        super("You don't have enough rights.");
    }
}
