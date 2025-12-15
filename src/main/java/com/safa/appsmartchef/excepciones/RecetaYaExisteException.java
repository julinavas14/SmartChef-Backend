package com.safa.appsmartchef.excepciones;

public class RecetaYaExisteException extends RuntimeException {
    public RecetaYaExisteException(String mensaje) {
        super(mensaje);
    }
}