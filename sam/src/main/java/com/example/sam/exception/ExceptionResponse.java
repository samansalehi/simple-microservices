package com.example.sam.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timeStamp;
    private String message;
    private String reason;

    public ExceptionResponse(Date timeStamp, String message, String reason) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.reason = reason;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}
