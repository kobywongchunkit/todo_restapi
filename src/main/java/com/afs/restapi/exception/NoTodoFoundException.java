package com.afs.restapi.exception;

public class NoTodoFoundException extends RuntimeException{
    public NoTodoFoundException(){
        super("No Company Found");
    }
}
