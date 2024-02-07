package com.leminhtien.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
;import javax.persistence.EntityNotFoundException;

/**
 * Handler redirect webpage with exception
 */

@RestControllerAdvice
public class MyResControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class, TypeMismatchDataAccessException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> invalidException(Exception ix){
        ix.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ix.getMessage());
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<String> notfound(EntityNotFoundException ix){
        ix.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ix.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class,NullPointerException.class, DataAccessException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<String> violation(Exception ix){
        ix.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ix.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception ix){
        ix.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ix.getMessage());
    }



}
