package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.response.ErrorResponse;
import com.aril.newsletter.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler({EmptyMailTemplateToException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse emptyMailTemplateTo(EmptyMailTemplateToException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler({EmptyMailTemplateContentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse noSuchFileExists(EmptyMailTemplateContentException ex){
        return new ErrorResponse(HttpStatus.NO_CONTENT.value(), ex.getMessage());
    }

}
