package com.example.rest.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserGlobalExceptionHandler {



    @ExceptionHandler
    public ResponseEntity<IncorrectDataAboutUser> handleException(Exception e){
        IncorrectDataAboutUser dataAboutUser = new IncorrectDataAboutUser();
        dataAboutUser.setInfo(e.getMessage());

        return new ResponseEntity<>(dataAboutUser, HttpStatus.BAD_REQUEST);


    }
}
