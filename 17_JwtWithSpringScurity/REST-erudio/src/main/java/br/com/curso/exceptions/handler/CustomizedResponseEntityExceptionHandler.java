package br.com.curso.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.curso.exceptions.ExceptionResponse;
import br.com.curso.exceptions.InvalidJwtAuthenticationException; // Corrigido o nome da classe
import br.com.curso.exceptions.RequiredObjectNullException;
import br.com.curso.exceptions.ResourceNotFoundException;

@ControllerAdvice // Indica que esta classe fornece tratamento global de exceções
@RestController // Indica que esta classe é um controlador REST
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Trata todas as exceções não tratadas
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), 
            ex.getMessage(), 
            request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Retorna erro 500
    }

    // Trata exceções específicas de recurso não encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(ResourceNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            new Date(), 
            ex.getMessage(), 
            request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); // Retorna erro 404
    }

    // Trata exceções quando um objeto necessário está nulo
    @ExceptionHandler(RequiredObjectNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(RequiredObjectNullException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            new Date(), 
            ex.getMessage(), 
            request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST); // Retorna erro 400
    }

    // Trata exceções de autenticação JWT inválida
    @ExceptionHandler(InvalidJwtAuthenticationException.class) // Corrigido o nome da classe
    public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(InvalidJwtAuthenticationException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            new Date(), 
            ex.getMessage(), 
            request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN); // Retorna erro 403
    }
}
