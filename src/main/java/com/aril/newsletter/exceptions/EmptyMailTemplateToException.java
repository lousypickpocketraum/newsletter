package com.aril.newsletter.exceptions;

public class EmptyMailTemplateToException extends RuntimeException{
    private String message;

    public EmptyMailTemplateToException() {

    }
    public EmptyMailTemplateToException(String message) {
        super(message);
        this.message = message;
    }
}
