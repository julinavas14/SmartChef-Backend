package com.safa.appsmartchef.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecetaNoEncontradaException extends RuntimeException {
    public RecetaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}