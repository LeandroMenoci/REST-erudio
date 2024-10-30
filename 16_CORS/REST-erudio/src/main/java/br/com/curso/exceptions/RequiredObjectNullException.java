package br.com.curso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectNullException extends RuntimeException{
    public RequiredObjectNullException(String ex) {
        super(ex);
    }

    public RequiredObjectNullException() {
        super("It's not allowed to persist a null object!");
    }

    private static final long serialVersionUID = 1L;
}
