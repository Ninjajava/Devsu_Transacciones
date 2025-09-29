package com.devsu.cuenta.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControladorExcepciones {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> clienteNoEncontrado(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InformacionException.class)
    public ResponseEntity<String>saldoInsuficiente(InformacionException ex){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ex.getMessage());
    }

}
