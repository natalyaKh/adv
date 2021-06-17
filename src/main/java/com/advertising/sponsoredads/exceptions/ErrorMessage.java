package com.advertising.sponsoredads.exceptions;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.Date;

public class ErrorMessage {
    private LocalDateTime timestamp;
    private String message;

    public ErrorMessage() {}

    public ErrorMessage(LocalDateTime timestamp, String message)
    {
        this.timestamp = timestamp;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
