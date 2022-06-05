package com.example.podcastserver.payload;

import org.springframework.http.HttpStatus;

public class Status {
    private String message;
    private Integer code;

    public Status(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public Status(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public Status(Integer code) {
        this.message = HttpStatus.valueOf(code).getReasonPhrase();
        this.code = code;
    }

    public Status(HttpStatus status) {
        this.message = status.getReasonPhrase();
        this.code = status.value();
    }

    public Status() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
