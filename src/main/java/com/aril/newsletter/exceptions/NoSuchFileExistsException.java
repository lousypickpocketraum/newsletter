package com.aril.newsletter.exceptions;

public class NoSuchFileExistsException extends RuntimeException{
    private String message;

    public NoSuchFileExistsException() {
        super("Ftp'de böyle bir dosya yok");
    }



}
