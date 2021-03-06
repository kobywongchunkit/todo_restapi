package com.afs.restapi.entity;

public class Todo {
    private String id;
    private String text;
    private Boolean done;

    public Todo() {
    }

    public Todo(String id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDone(boolean done) {
        this.done = done;
    }
}
