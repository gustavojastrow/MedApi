package com.ApiMed.Infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorResolving {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors(null);
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorData::new).toList());
    }

private record ErrorData(
    String campo,
    String mensagem){
        public ErrorData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
}
}
