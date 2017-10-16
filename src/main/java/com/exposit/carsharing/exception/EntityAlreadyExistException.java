package com.exposit.carsharing.exception;

public class EntityAlreadyExistException extends Exception {

    public EntityAlreadyExistException(String message) {
        super(message);
    }

    public EntityAlreadyExistException(String nameOfEntity, Long id) {
        super(String.format("%s with id %d already exist.", nameOfEntity, id));
    }
    public EntityAlreadyExistException() {
        super("Already exist.");
    }

}
