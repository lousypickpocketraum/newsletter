package com.aril.newsletter.exceptions;

import lombok.Getter;

@Getter
public class NoSuchMailTemplateExistsException extends RuntimeException {

    public NoSuchMailTemplateExistsException() {
        super("Böyle bir mail template yok");
    }

}
