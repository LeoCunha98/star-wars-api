package com.b2w.starwarsapi.endpoints.exceptions;

import com.b2w.starwarsapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> noSuchElement(ResourceNotFoundException e){
        StandardError err = new StandardError(
                "Recurso não encontrado",
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                new Date().getTime(),
                e.getClass().getName());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> noSuchElement(MethodArgumentNotValidException e){
        ValidationError err = new ValidationError(
                "Erro na Requisição",
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                new Date().getTime(),
                e.getClass().getName());

        for( FieldError error : e.getBindingResult().getFieldErrors()){
            err.addError(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
