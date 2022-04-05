package com.example.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class UserGlobalExceptionHandler extends RuntimeException {


    @ExceptionHandler
    public ResponseEntity<WhatWouldBeABeautifulJSONException> handleException(ResponseStatusException e) {
        WhatWouldBeABeautifulJSONException dataAboutUser = new WhatWouldBeABeautifulJSONException();
        dataAboutUser.setInfo(e.getMessage());
        if (e.getStatus().value() <= 451) {
            return new ResponseEntity<>(dataAboutUser, HttpStatus.BAD_REQUEST);

        }
        if (e.getStatus().value() > 451) {
            return new ResponseEntity<>(dataAboutUser, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(dataAboutUser, HttpStatus.SEE_OTHER);

    }
}

