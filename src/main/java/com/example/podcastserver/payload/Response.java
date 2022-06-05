package com.example.podcastserver.payload;

import java.util.List;

public class Response {
    private Status status;
    private Object data;

    public Response(Status status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Response(Status status) {
        this.status = status;
        this.data = List.of();
    }

    public Response() {

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
