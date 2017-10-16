package com.exposit.carsharing.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String nameOfEntity, Long id) {
        super(String.format("%s with id %d not found.", nameOfEntity, id));
    }

    public EntityNotFoundException() {
        super("Not found.");
    }
}
