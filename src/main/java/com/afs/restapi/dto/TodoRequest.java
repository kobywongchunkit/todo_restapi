package com.afs.restapi.dto;

public class TodoRequest {
    private String text;
    private Boolean done;

    public TodoRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
