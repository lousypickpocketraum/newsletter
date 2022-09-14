package com.aril.newsletter.exceptions;

public class NoSuchMailGroupExistsException  extends RuntimeException {

    public NoSuchMailGroupExistsException() {
        super("Böyle bir mail grubu yok");
    }

}
