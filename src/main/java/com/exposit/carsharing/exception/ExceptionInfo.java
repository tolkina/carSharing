package com.exposit.carsharing.exception;

import java.io.Serializable;

public class ExceptionInfo implements Serializable {
    private int status;
    private String message;

    public ExceptionInfo() {
    }

    ExceptionInfo(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
