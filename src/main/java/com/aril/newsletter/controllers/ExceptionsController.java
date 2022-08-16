package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.response.ErrorResponse;
import com.aril.newsletter.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler({NoSuchMailGroupExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse noSuchMailGroupExists(NoSuchMailGroupExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler({NoSuchMailTemplateExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse noSuchMailGroupExists(NoSuchMailTemplateExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler({NoSuchFileExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse noSuchFileExists(NoSuchFileExistsException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}
