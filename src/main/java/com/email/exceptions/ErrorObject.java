package com.email.exceptions;

public class ErrorObject
{
    private Integer statusCode;

    private String message;

    private Number timeStamp;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Number getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Number timeStamp) {
        this.timeStamp = timeStamp;
    }
}
