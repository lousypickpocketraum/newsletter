package com.aril.newsletter.exceptions;

public class NoSuchFileExistsException extends RuntimeException {

    public NoSuchFileExistsException() {
        super("Ftp'de böyle bir dosya yok");
    }

}
