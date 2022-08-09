package com.aril.newsletter.exceptions;

public class NoSuchMailGroupExistsException  extends RuntimeException {
    private String message;

    public NoSuchMailGroupExistsException() {
        super("Böyle bir mail grubu yok");
    }

}
