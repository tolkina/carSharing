package com.exposit.carsharing.exception;

import java.io.Serializable;

/**
 * Created by Sergei on 10/12/2017.
 */
public class ExceptionInfo implements Serializable {
    private Integer status;
    private String message;

    public ExceptionInfo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
