package br.com.curso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{
    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }

    private static final long serialVersionUID = 1L;
}
