package com.aril.newsletter.exceptions;

public class EmptyMailTemplateContentException extends RuntimeException{
    private String message;

    public EmptyMailTemplateContentException() {

    }
    public EmptyMailTemplateContentException(String message) {
        super(message);
        this.message = message;
    }
}
